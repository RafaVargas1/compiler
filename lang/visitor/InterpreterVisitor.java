package lang.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import java.util.Scanner;

import lang.ast.*;

public class InterpreterVisitor extends Visitor {

    private Stack<HashMap<String, Object>> globalEnv;
    private HashMap<String, Function> funcs;
    private Stack<Object> operands;
    private HashMap<String, Object> datas;

    private boolean retMode, debug;
    private HashMap<String, Literal> variablestype;

    public InterpreterVisitor() {
        globalEnv = new Stack<HashMap<String, Object>>();
        globalEnv.push(new HashMap<String, Object>()); // Inicializa ambiente global

        funcs = new HashMap<String, Function>();
        operands = new Stack<Object>();
        datas = new HashMap<String, Object>();

        retMode = false;
        debug = false;
    }

    public InterpreterVisitor(boolean debug) {
        this();
        this.debug = debug;
    }

    public void visit(Expr e) {
        // Abstrato
    }

    public void visit(Program p) {

        try {
            Node main = null;
            for (Node n : p.getContent()) {
                if (n instanceof Data) {
                    Data d = (Data) n;
                    datas.put(d.getName(), d.getDecl());

                }

                if (n instanceof Function) {
                    Function function = (Function) n;
                    funcs.put(function.getName(), function);

                    if (function.getName().equals("main")) {
                        main = function;
                    }

                }
            }

            if (main == null) {
                throw new RuntimeException("Defina uma função main");
            }

            main.accept(this);
        } catch (Exception x) {
            // x.printStackTrace();
            throw new RuntimeException(x);
            // throw new RuntimeException(" Prog Error (" + p.getLine() + ", " +
            // p.getColumn() + ") " + x.getMessage() + "Fim msg prog");
        }
    }

    // Operadores Matemáticos
    public void visit(Addition e) {

        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();

            if (esq instanceof Integer && dir instanceof Integer) {
                operands.push(new Integer(esq.intValue() + dir.intValue()));
            } else if (esq instanceof Float && dir instanceof Float) {
                operands.push(new Float(esq.floatValue() + dir.floatValue()));
            } else {
                throw new Exception("Na operação de soma os tipos das partes devem ser iguais");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Subtraction e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();

            if (esq instanceof Integer && dir instanceof Integer) {
                operands.push(new Integer(esq.intValue() - dir.intValue()));
            } else if (esq instanceof Float && dir instanceof Float) {
                operands.push(new Float(esq.floatValue() - dir.floatValue()));
            } else {
                throw new Exception("Na operação de subtração os tipos das partes devem ser iguais");
            }
        } catch (Exception x) {
            throw new RuntimeException(" Sub (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Multiplication e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();

            if (esq instanceof Integer && dir instanceof Integer) {
                operands.push(new Integer(esq.intValue() * dir.intValue()));
            } else if (esq instanceof Float && dir instanceof Float) {
                operands.push(new Float(esq.floatValue() * dir.floatValue()));
            } else {
                throw new Exception("Na operação de soma os tipos das partes devem ser iguais");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Division e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(new Float(esq.intValue() / dir.intValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Mod e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq, dir;

            dir = (Number) operands.pop();
            esq = (Number) operands.pop();

            if (!(esq instanceof Integer) || !(dir instanceof Integer)) {
                throw new Exception("Os numeros na operacao de resto devem ser inteiros");
            }
            operands.push(new Integer(esq.intValue() % dir.intValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Negative e) {
        try {
            e.getN().accept(this);
            Number num = (Number) operands.pop(); // O tipo Number é usado para armazenar tanto Float quanto Integer

            if (num instanceof Float) {
                operands.push(new Float(((Float) num) * -1));
            } else if (num instanceof Integer) {
                operands.push(new Integer(((Integer) num) * -1));
            } else {
                throw new Exception("Apenas numeros podem ser negativos");

            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    // Operadores Lógicos
    public void visit(And e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();

            if (!(dir instanceof Boolean) || !(esq instanceof Boolean)) {
                throw new Exception("Operacao AND deve ser entre booleanos");
            }

            operands.push(new Boolean((Boolean) esq && (Boolean) dir));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Smaller e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();

            if (dir instanceof Integer && esq instanceof Integer) {
                operands.push(new Boolean((Integer) esq < (Integer) dir));
            } else if ((dir instanceof Float && esq instanceof Float)) {
                operands.push(new Boolean((Float) esq < (Float) dir));
            } else if ((dir instanceof Float && esq instanceof Integer)
                    || (dir instanceof Integer && esq instanceof Float)) {
                throw new Exception("Os numeros comparados devem ser do mesmo tipo");
            } else {
                throw new Exception("Os valores " + dir + " e " + esq + " devem ser números de um mesmo tipo");
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Greater e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();

            if (dir instanceof Integer && esq instanceof Integer) {
                operands.push(new Boolean((Integer) esq < (Integer) dir));
            } else if ((dir instanceof Float && esq instanceof Float)) {
                operands.push(new Boolean((Float) esq < (Float) dir));
            } else if ((dir instanceof Float && esq instanceof Integer)
                    || (dir instanceof Integer && esq instanceof Float)) {
                throw new Exception("Os numeros comparados devem ser do mesmo tipo");
            } else {
                throw new Exception("Os valores " + dir + " e " + esq + " devem ser números de um mesmo tipo");
            }

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Equal e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            operands.push(new Boolean(operands.pop().equals(operands.pop())));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Different e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            operands.push(new Boolean(!operands.pop().equals(operands.pop())));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Not e) {
        try {
            e.getN().accept(this);
            operands.push(new Boolean(!(Boolean) operands.pop()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    // Variáveis
    public void visit(True e) {
        try {
            operands.push(new Boolean(true));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(False e) {
        try {
            operands.push(new Boolean(false));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Null e) {
        try {
            operands.push(null);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Empty e) {
        try {
            operands.push(null);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(IntegerVar e) {
        try {
            operands.push(new Integer(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(FloatVar e) {
        try {
            operands.push(new Float(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(CharacterVar e) {
        try {
            operands.push(new String(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(ID e) {
        try {
            Object v = globalEnv.peek().get(e.getName());

            if (v != null) {
                operands.push(v);
            } else {
                throw new RuntimeException("Variável ' " + e.getName() + " ' não declarada na linha " + e.getLine()
                        + " coluna " + e.getColumn());
            }

        } catch (Exception x) {
            throw new RuntimeException(
                    " Error Variable -> (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Array e) {
        try {
            if (e.getName() instanceof Array) {
                String arr_name = ((ID) ((Array) e.getName()).getName()).getName();
                ((Array) e.getName()).getIndex().accept(this);
                int inner_index = (Integer) operands.pop();
                e.getIndex().accept(this);
                int outer_index = (Integer) operands.pop();
                Object[][] arr_content = (Object[][]) globalEnv.peek().get(arr_name);
                operands.push(arr_content[inner_index][outer_index]);
            } else {
                String arr_name = ((ID) e.getName()).getName();
                e.getIndex().accept(this);
                int index = (Integer) operands.pop();
                Object[] arr_content = (Object[]) globalEnv.peek().get(arr_name);
                operands.push(arr_content[index]);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    // Tem que empilhar o valor no operands do acesso e
    public void visit(Component e) {
        Node parent = e.getParent();
        ID idParent = null;
        Param[] params = null;

        if (parent instanceof Array) {
            Array arrayparent = (Array) parent;
            idParent = ((ID) (arrayparent).getName());

            Object[] arraycontent = (Object[]) globalEnv.peek().get(idParent.getName());

            arrayparent.getIndex().accept(this);
            int idx = (Integer) operands.pop();

            params = (Param[]) arraycontent[idx];

        } else {
            idParent = (ID) parent;

            params = (Param[]) globalEnv.peek().get(idParent.getName());
        }

        for (Param p : params) {

            if (p.getParamId().equals(e.getName())) {

                String name = idParent.getName() + "." + e.getName();
                operands.push(globalEnv.peek().get(name));

            }
        }

    }

    // Comandos
    public void visit(Call e) {
        try {
            Function f = funcs.get(e.getName());
            if (f != null) {
                for (Node node : e.getArgs()) {
                    node.accept(this);
                }
                f.accept(this);

                NodeList returns = e.getReturns();
                Node[] returnVariables = new Node[10];
                int i = 0;
                returnVariables[i] = returns.getCmd1();

                while (returns.getCmd2() != null) {
                    i++;
                    returns = returns.getCmd2();
                    returnVariables[i] = returns.getCmd1();
                }
        
                for(int j = 0; j <= i; j++) {
                    String var_name = ((ID) returnVariables[j]).getName();
                    Object var_value = operands.pop();
        
                    globalEnv.peek().put(var_name, var_value);
                }
            } else {
                throw new RuntimeException(" Function error -> (" + e.getLine() + ", " + e.getColumn()
                        + ") Função não definida " + e.getName());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Atribuition e) {
        try {
            Node v = e.getName();

            e.getExpr().accept(this);

            if (v instanceof ID) {
                ID v2 = (ID) v;
                globalEnv.peek().put(v2.getName(), operands.pop()); // Nome da variavel
            } else if (v instanceof Component) {
                Component v2 = (Component) v;
                String parent_name = "";

                if (v2.getParent() instanceof Array) {
                    parent_name = ((ID) ((Array) v2.getParent()).getName()).getName();
                } else {
                    parent_name = ((ID) v2.getParent()).getName();
                }

                String name =  parent_name + "." + v2.getName();
                globalEnv.peek().put(name, operands.pop());
            } else if (v instanceof Array) {
                Array v2 = (Array) v;

                String an;
                if (v2.getName() instanceof Array) {
                    an = ((ID) ((Array) v2.getName()).getName()).getName();
                    ((Array) v2.getName()).getIndex().accept(this);
                } else {
                    an = ((ID) v2.getName()).getName();
                }

                v2.getIndex().accept(this);

                int ot_index = -1;
                if (v2.getName() instanceof Array) {
                    ot_index = (Integer) operands.pop();
                }
                int index = (Integer) operands.pop();
                Object content = operands.pop();

                Object ac = globalEnv.peek().get(an);

                if (ac == null) {
                    globalEnv.peek().put(an, content);
                } else {
                    if (ac instanceof Object[][]) {
                        Object[][] uac = (Object[][]) ac;
                        if (ot_index > -1) {
                            uac[index][ot_index] = content;
                        } else {
                            uac[index] = (Object[]) content;
                        }
                        globalEnv.peek().put(an, uac);
                    } else if (ac instanceof Object[]) {
                        Object[] uac = (Object[]) ac;
                        uac[index] = content;
                        globalEnv.peek().put(an, uac);
                    }
                }
            }

        } catch (Exception x) {
            x.printStackTrace();
            throw new RuntimeException("Atribuiton -> (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(If e) {
        try {
            e.getTeste().accept(this);
            if ((Boolean) operands.pop()) {
                e.getThen().accept(this);
            } else if (e.getElse() != null) {
                e.getElse().accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Iterate e) {
        try {
            e.getTeste().accept(this);

            int counter = 0;
            int stop = (Integer) operands.pop();
            while (counter < stop) {
                e.getBody().accept(this);
                counter++;
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Print e) {
        try {
            e.getExpr().accept(this);
            Object expr = operands.pop();
            
            if (expr instanceof String && ((String) expr).equals("\\n")) {
                System.out.println();
            } else {
                System.out.print(expr); // Print
            }

        } catch (Exception x) {

            throw new RuntimeException(" Print -> (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Read e) {
        try {
            Scanner scanner = new Scanner(System.in);        
            int input = scanner.nextInt();
        
            globalEnv.peek().put(((ID) e.getName()).getName(), input);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(NodeList e) {
        try {
            e.getCmd1().accept(this);

            if (e.getCmd2() != null)
                e.getCmd2().accept(this);

        } catch (Exception x) {
            throw new RuntimeException(
                    "Erro no node list -> (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
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

    public void visit(Instance e) {
        try {
            Literal nameType = (Literal) e.getType();
            if (nameType.getType() == null) {
                throw new RuntimeException("Tipo de Data não é um tipo válido ");

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
                    throw new RuntimeException("O tipo especial de Data " + n.getName() + " nao foi declarada");
                }

                operands.push(datas.get(n.getName()));
            } 

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Return e) {
        Expr[] exprs = e.getExpr();

        for (int i = 0; i < exprs.length; i++) {
            exprs[i].accept(this);
        }
        retMode = true;
    }

    public void visit(Param e) {
        e.getParamType().accept(this);

        //
    }

    public void visit(BoolType e) {
        // e.getType().accept(this);
    }

    public void visit(CharType e) {
        // e.getType().accept(this);
    }

    public void visit(FloatType e) {
        // e.getType().accept(this);
    }

    public void visit(IntType e) {
        // e.getType().accept(this);
    }

    public void visit(Vector e) {
        // e.getType().accept(this);
    }

    public void visit(DataType e) {
        // e.getType().accept(this);
    }

    public void visit(Data e) {
        // for (Param p : e.getParams()) {
        // p.getParamId().accept(this);
        // p.getParamType().accept(this);
        // }
        try {
            datas.put(e.getName(), e.getDecl());
            // datas.put(n.getName(), n.getParams());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(IndexedCall e) {
        try {
            Function f = funcs.get(e.getName());
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

                while (i > 0) {
                    Object aux = operands.pop();
                    i--;

                    if (i == index) {
                        result = aux;
                    }
                }
                if (result == null)
                    throw new RuntimeException("Indice fora do range do retorno da função");
                operands.push(result);

                // Expr return = Expr operands.pop();
                // operands.pop();
            } else {
                throw new RuntimeException(
                        " (" + e.getLine() + ", " + e.getColumn() + ") Função não definida " + e.getName());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
}
