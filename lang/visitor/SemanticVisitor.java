package lang.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import java.util.Scanner;
import lang.ast.*;


public class SemanticVisitor extends Visitor {

    private Stack<HashMap<String, Object>> globalEnv;  // Agora checamos tipos
    private Stack<HashMap<String, Object>> globalEnvType;  
    private HashMap<String, Function> funcs;
    private List<String> semanticErrors = new ArrayList<>();
    private Stack<Object> variablestype;
    private Stack<Object> operands;
    private HashMap<String, Object> datas;
    private boolean retMode;



    public SemanticVisitor() {
        globalEnv = new Stack<>();
        globalEnv.push(new HashMap<>());  // Ambiente global inicial
        operands = new Stack<Object>();
        funcs = new HashMap<>();
        datas = new HashMap<String, Object>();
        retMode = false;

    }

    public void visit(Program p) {
        Node main = null;
        for (Node n : p.getContent()) {
            if (n instanceof Data) {
                Data d = (Data) n;
                datas.put(d.getName(), d.getDecl());

            }

            if (n instanceof Function) {
                Function function = (Function) n;
                Stack<Class<?>> paramTypes = new Stack<>();
                for (Param par : function.getParams()) {
                    paramTypes.add(getClassFromTypeName((Literal) par.getParamType()));
                }

                String signature = generateFunctionSignature(function.getName(), paramTypes);
                funcs.put(signature, function);

                if (function.getName().equals("main")) {
                    main = function;
                }
            }
        }
        if (main == null) {
            semanticErrors.add("Função 'main' não encontrada.");
        } else {
            main.accept(this);  // Visita o corpo da função main
        }
    }

    public void visit(Function f) {
        HashMap<String, Object> localEnv = new HashMap<String, Object>();
        for (int i = f.getParams().length - 1; i >= 0; i--) {
            localEnv.put(f.getParams()[i].getParamId(), operands.pop());
        }


        globalEnv.push(localEnv);
        f.getBody().accept(this);
        globalEnv.pop();
        retMode = false;
    }

    public String generateFunctionSignature(String name, Stack<Class<?>> parameterTypes) {
        StringBuilder signature = new StringBuilder(name);
        signature.append("(");
        for (Class<?> paramType : parameterTypes) {
            if (paramType != null) {
                signature.append(paramType.getSimpleName());
            }
            signature.append(",");
        }
        if (!parameterTypes.isEmpty()) {
            signature.setLength(signature.length() - 1); // Remove the last comma
        }
        signature.append(")");
        return signature.toString();
    }


    public void visit(Atribuition e) {
        if (retMode == true)
            return;

        try {
            Node v = e.getName(); 
            e.getExpr().accept(this);  // Processa a expressão a ser atribuída

            // Caso a variável seja um ID
            if (v instanceof ID) {
                ID v2 = (ID) v;
                Object alreadyValue = globalEnv.peek().get(v2.getName());
                Object newValue = operands.pop();

                // Verifica se a variável já possui um valor e compara os tipos
                if (alreadyValue != null) {
                    if (!newValue.getClass().equals(alreadyValue.getClass())) {
                        semanticErrors.add("Tentando atribuir valor de tipo " + newValue.getClass() + " a uma variável de tipo " + alreadyValue.getClass() + " na linha " + e.getLine());
                    }
                } else {
                    // Variável não existente, cria uma nova no ambiente
                    globalEnv.peek().put(v2.getName(), newValue);
                }
                
            } 
            // Caso seja um componente (acesso a um atributo de objeto)
            else if (v instanceof Component) {
                Component v2 = (Component) v;
                String parent_name = ((ID) v2.getParent()).getName() + "." + v2.getName();
                
                Param[] params = (Param[]) globalEnv.peek().get(((ID) v2.getParent()).getName());
                boolean findProperty = false;

                for (Param param : params) {
                    if (param.getParamId().equals(v2.getName())) {
                        findProperty = true;
                        Object value = operands.pop();
                        Class<?> paramType = getClassFromTypeName((Literal) param.getParamType());
                        
                        // Verificação de tipo para o atributo do objeto
                        if (value.getClass().equals(paramType)) {
                            globalEnv.peek().put(parent_name, value);
                        } else {
                            semanticErrors.add("Tipo errado no atributo. Esperava " + paramType + " recebido " + value.getClass());
                        }
                    }
                }

                if (!findProperty) {
                    semanticErrors.add("Acesso a parâmetro inexistente: " + v2.getName());
                }

            } 
            // Caso seja um Array
            else if (v instanceof Array) {
                Array v2 = (Array) v;
                String arrayName = ((ID) v2.getName()).getName();
                
                // Aceitação do índice
                v2.getIndex().accept(this);
                int index = (Integer) operands.pop();
                Object newValue = operands.pop();

                Object arrayContent = globalEnv.peek().get(arrayName);
                if (arrayContent instanceof Object[]) {
                    Object[] array = (Object[]) arrayContent;

                    // Verificação de tipo no array
                    if (array[index] != null && !newValue.getClass().equals(array[index].getClass())) {
                        semanticErrors.add("Tipo incompatível no array " + arrayName + ". Esperado " + array[index].getClass() + " mas recebeu " + newValue.getClass() + " no índice " + index);
                    }

                    // Atribuição no array
                    array[index] = newValue;
                    globalEnv.peek().put(arrayName, array);
                } else {
                    semanticErrors.add("Tentando acessar um objeto não array como array: " + arrayName);
                }
            }

        } catch (Exception x) {
            x.printStackTrace();
            throw new RuntimeException("Atribuição -> (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }


    private Class<?> getClassFromTypeName(Literal typeName) {
        switch (typeName.getType()) {
            case "INT":
                return Integer.class;
            case "FLOAT":
                return Float.class;
            case "BOOL":
                return Boolean.class;
            case "CHAR":
                return Character.class;
            default:
                throw new IllegalArgumentException("Tipo desconhecido: " + typeName);
        }
    }


    // Exemplo para visitar o retorno de uma função
    public void visit(Return e) {
        if (retMode == true)
            return;

        Expr[] exprs = e.getExpr();
     
        for (int i=0; i < exprs.length; i++) {
            exprs[i].accept(this);
        }

        retMode = true;
    }

    public List<String> getSemanticErrors() {
        return semanticErrors;
    }

    public void visit(Addition e) {
        e.getA().accept(this);
        e.getB().accept(this);
        Number esq, dir;
        dir = (Number) operands.pop();
        esq = (Number) operands.pop();

        if ( esq instanceof Integer && dir instanceof Integer){ 
                operands.push(new Integer(esq.intValue() + dir.intValue()));
        } else if ( esq instanceof Float && dir instanceof Float){
            operands.push(new Float(esq.floatValue() + dir.floatValue()));
        } else { 
            semanticErrors.add("Na operação de soma os tipos das partes devem ser iguais. Linha: " +e.getLine() + " Coluna " + e.getColumn());
        }
    }
    public void visit (Subtraction e) {
        e.getA().accept(this);
        e.getB().accept(this);
        Number esq, dir;
        dir = (Number) operands.pop();
        esq = (Number) operands.pop();

        if ( esq instanceof Integer && dir instanceof Integer){ 
            operands.push(new Integer(esq.intValue() - dir.intValue()));
        } else if ( esq instanceof Float && dir instanceof Float){
            operands.push(new Float(esq.floatValue() - dir.floatValue()));
        } else { 
            semanticErrors.add("Na operação de subtração os tipos das partes devem ser iguais");
        }
    };
    public void visit (Multiplication e) {
        e.getA().accept(this);
        e.getB().accept(this);
        Number esq, dir;
        dir = (Number) operands.pop();
        esq = (Number) operands.pop();

        if ( esq instanceof Integer && dir instanceof Integer){ 
            operands.push(new Integer(esq.intValue() * dir.intValue()));
        } else if ( esq instanceof Float && dir instanceof Float){
            operands.push(new Float(esq.floatValue() * dir.floatValue()));
        } else { 
            semanticErrors.add("Na operação de soma os tipos das partes devem ser iguais");
        }
    };
    public void visit (Division e) {
        e.getA().accept(this);
        e.getB().accept(this);
        Number esq, dir;
        dir = (Number) operands.pop();
        esq = (Number) operands.pop();

        if ( esq instanceof Integer && dir instanceof Integer){ 
            operands.push(new Integer(esq.intValue() / dir.intValue()));
        } else if ( esq instanceof Float && dir instanceof Float){
            operands.push(new Float(esq.floatValue() / dir.floatValue()));
        } else { 
            semanticErrors.add("Na operação de divisão os tipos das partes devem ser iguais");
        }
    };
    public void visit (Mod e) {
        e.getA().accept(this);
        e.getB().accept(this);
        Number esq, dir;

        dir = (Number) operands.pop();
        esq = (Number) operands.pop();

        if (!(esq instanceof Integer) || !(dir instanceof Integer)) {
            semanticErrors.add("Os numeros na operacao de resto devem ser inteiros");                
            return;
        }
        operands.push(new Integer(esq.intValue() % dir.intValue()));
    };
    public void visit (Negative e) {
        e.getN().accept(this);
        Number num = (Number) operands.pop(); // O tipo Number é usado para armazenar tanto Float quanto Integer

        if (num instanceof Float) {
            operands.push(new Float(((Float) num) * -1));
        } else if (num instanceof Integer) {
            operands.push(new Integer(((Integer) num) * -1));
        } else {
            semanticErrors.add("Apenas numeros podem ser negativos");             
        }
    };
    public void visit (And e) {
        e.getA().accept(this);
        e.getB().accept(this);
        Object esq, dir;
        dir = operands.pop();
        esq = operands.pop();

        if (!(dir instanceof Boolean) || !(esq instanceof Boolean)){
            semanticErrors.add("Operacao AND deve ser entre booleanos");
            return;           
        }

        operands.push(new Boolean((Boolean) esq && (Boolean) dir));
    };
    public void visit (Smaller e) {
        e.getA().accept(this);
        e.getB().accept(this);
        Object esq, dir;
        dir = operands.pop();
        esq = operands.pop();

        if ( dir instanceof Integer && esq instanceof Integer){
            operands.push(new Boolean((Integer) esq < (Integer) dir));
        } else if ( ( dir instanceof Float && esq instanceof Float) ) {
            operands.push(new Boolean((Float) esq < (Float) dir));
        } else if ( 
            (dir instanceof Float && esq instanceof Integer) 
            || (dir instanceof Integer && esq instanceof Float) ) {
            semanticErrors.add("Os numeros comparados devem ser do mesmo tipo");
            return;
        } else {
            semanticErrors.add("Os valores " + dir + " e " + esq + " devem ser números de um mesmo tipo");
            return;
        }
    };
    public void visit (Greater e) {
        e.getA().accept(this);
        e.getB().accept(this);
        Object esq, dir;
        dir = operands.pop();
        esq = operands.pop();

        if ( dir instanceof Integer && esq instanceof Integer){
            operands.push(new Boolean((Integer) esq < (Integer) dir));
        } else if ( ( dir instanceof Float && esq instanceof Float) ) {
            operands.push(new Boolean((Float) esq < (Float) dir));
        } else if ( 
            (dir instanceof Float && esq instanceof Integer) 
            || (dir instanceof Integer && esq instanceof Float) ) {
            semanticErrors.add("Os numeros comparados devem ser do mesmo tipo");
        } else {
            semanticErrors.add("Os valores " + dir + " e " + esq + " devem ser números de um mesmo tipo");
        }
    };
    public void visit (Equal e) {
        e.getA().accept(this);
        e.getB().accept(this);
        Object esq, dir;
        dir = operands.pop();
        esq = operands.pop();

        if ( 
            ( dir instanceof Integer && esq instanceof Integer) || 
            ( dir instanceof Float && esq instanceof Float) || 
            ( dir instanceof String && esq instanceof String) ||
            ( dir instanceof Character && esq instanceof Character)
        ){
            operands.push(new Boolean(dir.equals(esq)));
        } else {
            semanticErrors.add("Os valores " + dir + " e " + esq + " devem ser números de um mesmo tipo");
        }
    };
    public void visit (Different e) {
        e.getA().accept(this);
        e.getB().accept(this);
        Object esq, dir;
        dir = operands.pop();
        esq = operands.pop();

        if ( 
            ( dir instanceof Integer && esq instanceof Integer) || 
            ( dir instanceof Float && esq instanceof Float) || 
            ( dir instanceof String && esq instanceof String) ||
            ( dir instanceof Character && esq instanceof Character)
        ){
            operands.push(new Boolean(!dir.equals(esq)));
        } else {
            semanticErrors.add("Os valores " + dir + " e " + esq + " devem ser números de um mesmo tipo");
        }
    };
    public void visit (Not e) {
        e.getN().accept(this);
        Object op = operands.pop();

        if ( op instanceof Boolean) {
            operands.push(new Boolean(!(Boolean) op));
        } else {
            semanticErrors.add("Os valores devem ser booleanos para serem negados");

        }

    };
    public void visit (True e) {
        operands.push(new Boolean(true));

    };
    public void visit (False e) {
        operands.push(new Boolean(false));

    };
    public void visit (Null e) {
        operands.push(null);

    };
    public void visit (Empty e) {
        operands.push(null);
    };
    public void visit (IntegerVar e) {
        try {
            operands.push(new Integer(e.getValue()));
        } catch (Exception x) {
            semanticErrors.add("O valor " + e.getValue() + " não pode ser um inteiro");
        }
    };
    public void visit (FloatVar e) {
        try {
            operands.push(new Float(e.getValue()));
        } catch (Exception x) {
            semanticErrors.add("O valor " + e.getValue() + " não pode ser um float");
        }
    };
    public void visit (CharacterVar e) {
        try {
            operands.push(new String(e.getValue()));
        } catch (Exception x) {
            semanticErrors.add("O valor " + e.getValue() + " não pode ser um char");
        }
    };

    public void visit (ID e) {
         Object v = globalEnv.peek().get(e.getName());

        if (v != null) {
            operands.push(v);
        } else {
            semanticErrors.add("Variável ' " + e.getName() + " ' não declarada na linha " + e.getLine() + " coluna " + e.getColumn() );
        }
    };

    public void visit (Array e) {};
    public void visit (Component e) {};

    public void visit (If e) {
        if (retMode == true)
            return;

        e.getTeste().accept(this);
        Object test = operands.pop();
        if (test instanceof Boolean){
            if ((Boolean)test){
                e.getThen().accept(this);
            } else if (e.getElse() != null) {
                e.getElse().accept(this);
            }
        } else {
            semanticErrors.add("A comparacao no if deve ser um booleano");

        }

    };

    public void visit (Iterate e) {
        if (retMode == true)
            return;

        e.getTeste().accept(this);

            int counter = 0;
            int stop = (Integer) operands.pop();
            while (counter < stop) {
                e.getBody().accept(this);
                counter++;
            }
    };
    public void visit (Print e) {
        if (retMode == true)
            return;
    };
    public void visit (Read e) {
        if (retMode == true)
            return;

    };

    public void visit (NodeList e) {
        e.getCmd1().accept(this);
            
        if ( e.getCmd2() != null)
            e.getCmd2().accept(this);

    };

    public void visit(Call e) {
        if (retMode == true)
            return;

        Stack<Class<?>> passedArguments = new Stack<>();

        for (Expr exp: e.getArgs()) {
            exp.accept(this);
            Object v = operands.pop();
            passedArguments.add(v.getClass());
        }

        String signature = generateFunctionSignature(e.getName(), passedArguments);
        Function f = funcs.get(signature);

        if (f == null) {
            semanticErrors.add("Função não encontrada -> " + e.getName());
            return;
        }

        Stack<Class<?>> expectedArguments = new Stack<>();

        for (Param par: f.getParams()){
            expectedArguments.add(getClassFromTypeName((Literal) par.getParamType()));
        }

        if (passedArguments.size() != expectedArguments.size()) {
            semanticErrors.add("Quantidade de parametros não corresponde para a função -> " + e.getName());
            return;
        }

        for (int i = 0; i < expectedArguments.size(); i++) {

            if (expectedArguments.get(i) != null){
                if (!expectedArguments.get(i).equals(passedArguments.get(i)) ) {
                    semanticErrors.add("Tipo de retorno não corresponde na posição " + (i + 1) + " para a função -> " + e.getName());
                }
            }

        
        }


        Stack<Class<?>> expectedReturnType = new Stack<>();
        NodeList expectedReturn = e.getReturn();

        // Obtenção do tipo de retorno esperado a partir da chamada (Call)
        while (expectedReturn != null) {
            ID id = (ID) expectedReturn.getCmd1();
            Object value = globalEnv.peek().get(id.getName());
            if (value != null) {
                expectedReturnType.add(value.getClass());
            } else {
                expectedReturnType.add(null);
            }
            expectedReturn = expectedReturn.getCmd2();
        }

        // Obtenção do tipo de retorno declarado da função
     

        Stack<Class<?>> declaredReturnType = new Stack<>();
        NodeList declaredReturn = f.getReturnType();
        while (declaredReturn != null) {
            Literal type = (Literal) declaredReturn.getCmd1();
            declaredReturnType.add( getClassFromTypeName(type));
            declaredReturn = declaredReturn.getCmd2();
        }

        // Comparação dos tipos de retorno
        if (expectedReturnType.size() != declaredReturnType.size()) {
            semanticErrors.add("Quantidade de tipos de retorno não corresponde para a função -> " + e.getName());
            return;
        }

        for (int i = 0; i < expectedReturnType.size(); i++) {

            if (expectedReturnType.get(i) != null){
                if (!expectedReturnType.get(i).equals(declaredReturnType.get(i)) ) {
                    semanticErrors.add("Tipo de retorno não corresponde na posição " + (i+1) + " para a função -> " + e.getName());
                }
            }

        
        }

        for (Node node : e.getArgs()) {
            node.accept(this);
        }

        f.accept(this);
    }

    public void visit(IndexedCall e) {   
        Stack<Class<?>> passedArguments = new Stack<>();
        for (Node node : e.getParams()) {
            node.accept(this);
            Object v = operands.pop();
            passedArguments.add(v.getClass());
        }

        String signature = generateFunctionSignature(e.getName(), passedArguments);
        Function f = funcs.get(signature);

        if (f != null) {
            for (Node node : e.getParams()) {
                node.accept(this);
            }
            f.accept(this);

            NodeList rt = f.getReturnType();
            
            int totalReturns = 1;
            while (rt.getCmd2() != null) {
                totalReturns++;
                rt = rt.getCmd2();
            }

            e.getIndex().accept(this);
            int index = (int) operands.pop();
            int i = totalReturns;
            Object result = null;
            
            while ( i > 0) {
                Object aux = operands.pop();
                i--;

                if (i == index) {
                    result = aux;
                }
            }
            if ( result == null)
                semanticErrors.add("Indice fora do range do retorno da função");
            
            operands.push(result);

        } else {
            semanticErrors.add("Função não definida com essa assinatura -> " + signature);
        }
   
    }

    public void visit (Instance e) {
         Literal nameType = (Literal) e.getType();
        if (nameType.getType() == null) {
            semanticErrors.add("Tipo de Data não é um tipo válido ");

        } else if (e.getExpr() != null) {
            if (nameType.getType() == "VECTOR") {
                e.getExpr().accept(this);
                Object[][] expr = new Object[(Integer) operands.pop()][];
                operands.push(expr);
            } else {
                e.getExpr().accept(this);
                Object[] expr = new Object[(Integer) operands.pop()];
                operands.push(expr);
            }
        } else if (nameType.getType() == "DATA") {
            DataType n = (DataType) nameType;

            if (datas.get(n.getName()) == null) {
                semanticErrors.add("O tipo especial de Data " + n.getName() + " nao foi declarada");
            }

            operands.push(datas.get(n.getName()));
        } 
    };
    public void visit (Param e) {
        e.getParamType().accept(this);
    };
    public void visit (BoolType e) {};
    public void visit (CharType e) {};
    public void visit (FloatType e) {};
    public void visit (IntType e) {};
    public void visit (DataType e) {};
    public void visit (Vector e) {};

    public void visit (Data e) {
        datas.put( e.getName(), e.getDecl());

    };
    public void visit (Expr e) {};

}
