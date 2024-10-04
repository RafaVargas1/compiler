package lang.visitor;

import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.ST;


import lang.ast.*;
import lang.visitor.Visitor;

import java.util.List;
import java.util.Stack;
import java.beans.Expression;
import java.util.ArrayList;
import java.util.HashMap;

public class JasminVisitor extends Visitor {
    private Stack<HashMap<String, Object>> globalEnv;
    private HashMap<String, Function> funcs;
    private Stack<Object> operands;
    private HashMap<String, Object> datas;

    private boolean retMode;

    private STGroup groupTemplate;
    private StringBuilder jasminCode;

    public JasminVisitor() {
        groupTemplate = new STGroupFile("./lang/tools/template/jasmin.stg");  // Carrega o template Jasmin
        jasminCode = new StringBuilder();

        globalEnv = new Stack<HashMap<String, Object>>();
        globalEnv.push(new HashMap<String, Object>());

        funcs = new HashMap<String, Function>();
        operands = new Stack<Object>();
        datas = new HashMap<String, Object>();

        retMode = false;
    }

    public void visit(Program program) {
        // Usar o template correto para gerar a definição da classe
        String classTemplate = templates.getInstanceOf("program")
                                .add("name", program.getClassName())
                                .add("funcs", ""); // Placeholder para as funções
        jasminCode.append(classTemplate);
        
        // Visitar todas as declarações internas
        for (Node statement : program.getStatements()) {
            statement.accept(this);
        }
    }


    public void visit(Function function) {
        // Gerar a assinatura da função usando o template correto
        String functionTemplate = templates.getInstanceOf("func")
                                .add("name", function.getName())
                                .add("params", getParamsSignature(function))
                                .add("return", getReturnType(function))
                                .add("decls", 10) // Exemplo de limite de locais
                                .add("stack", 10) // Exemplo de limite de pilha
                                .add("stmt", ""); // Placeholder para o corpo da função
        jasminCode.append(functionTemplate);
        
        // Visitar o corpo da função
        for (Node statement : function.getBody()) {
            statement.accept(this);
        }
    }



    public void visit(Call call) {
        // Empilhar os argumentos da chamada
        for (Expression arg : call.getArguments()) {
            arg.accept(this);
        }
        
        // Gerar o código para invocar o método usando o template "call"
        jasminCode.append(templates.getInstanceOf("call")
                            .add("class", call.getClassName())
                            .add("name", call.getMethodName())
                            .add("type", getMethodSignature(call))
                            .add("return", getReturnType(call))
                            .add("args", "")) // Placeholder para os argumentos
                            .render();
    }



    public void visit(Atribuition attribuition) {
        // Visitar a expressão do lado direito (o valor a ser atribuído)
        attribuition.getExpr().accept(this)
        Integer num = (Integer) globalEnv.peek().get(attribuition.getName())
        // Gerar o código para armazenar o valor na variável local usando o template "istore"
        jasminCode.append(templates.getInstanceOf("istore")
                            .add("num", num)
                            .add("expr", operands.pop())
                            .render());
    }


    public void visit(Addition addition) {
        // Visitar o operando da esquerda
        addition.getA().accept(this);
        
        // Visitar o operando da direita
        addition.getB().accept(this);
        
        // Gerar a instrução de adição usando o template "iadd"
        jasminCode.append(templates.getInstanceOf("iadd")
                            .add("left_expr", addition.getA().toString())
                            .add("right_expr", addition.getB().toString())
                            .render());
    }



    // public void visit(Return e) {
    //     // Gerar código Jasmin para instruções de retorno
    //     ST returnTemplate = groupTemplate.getInstanceOf("return");
    //     // e.getExpr().accept(this);  // Visita o valor de retorno

    //     jasminCode.add(returnTemplate.render());
    // }

    // public void visit(If e) {
    //     // Gerar código Jasmin para instruções if
    //     ST ifTemplate = groupTemplate.getInstanceOf("ifStmt");
    //     e.getTeste().accept(this);  // Visita a condição

    //     ifTemplate.add("thenBlock", e.getThen());
    //     if (e.getElse() != null) {
    //         ifTemplate.add("elseBlock", e.getElse());
    //     }

    //     jasminCode.add(ifTemplate.render());
    // }

    
    public void visit(Data e){

    } 
    
    
    public void visit(NodeList e){

    } 
    

    
    public void visit(Subtraction e){

    } 
    
    public void visit(Multiplication e){

    } 
    
    public void visit(Division e){

    } 
    
    public void visit(Mod e){

    } 
    
    public void visit(Negative e){

    } 
    
    public void visit(And e){

    } 
    
    public void visit(Smaller e){

    } 
    
    public void visit(Greater e){

    } 
    
    public void visit(Equal e){

    } 
    
    public void visit(Different e){

    } 
    
    public void visit(Not e){

    } 
    
    public void visit(True e){

    } 
    
    public void visit(False e){

    } 
    
    public void visit(Null e){

    } 
    
    public void visit(IntegerVar e){

    } 
    
    public void visit(FloatVar e){

    } 
    
    public void visit(CharacterVar e){

    } 
    
    public void visit(ID e){

    } 
    
    public void visit(Array e){

    } 
    
    public void visit(Component e){

    } 
    
    public void visit(Instance e){

    } 
    
    public void visit(IndexedCall e){

    } 
    

    

    public void visit(Iterate e){

    } 
    
    public void visit(Print e){

    } 
    
    public void visit(Read e){

    } 
    

    

    
    public void visit(Param e){

    } 
    
    public void visit(BoolType e){

    } 
    
    public void visit(CharType e){

    } 
    
    public void visit(FloatType e){

    } 
    
    public void visit(IntType e){

    } 
    
    public void visit(Vector e){

    } 
    
    public void visit(DataType e){

   
   
    }

    // Métodos adicionais para outros nós da AST...

    public StringBuilder getJasminCode() {
        return jasminCode;
    }

    // Funções auxiliares
    public String generateFunctionSignature(String name, Stack<Literal> parameterTypes) {
        StringBuilder signature = new StringBuilder(name);

        signature.append("(");

        for (Literal paramType : parameterTypes) {
            if (paramType != null) {
                signature.append(paramType.getType());
            }
            signature.append(",");
        }

        if (!parameterTypes.isEmpty()) {
            signature.setLength(signature.length() - 1); 
        }

        signature.append(")");

        return signature.toString();
    }

    public String generateFunctionSignature(String name, String[] params) {
        StringBuilder signature = new StringBuilder(name);

        signature.append("(");

        for (String paramType : params) {
            signature.append(paramType);
            signature.append(",");
        }
        
        signature.setLength(signature.length() - 1); 

        signature.append(")");

        return signature.toString();
    }

    public String renameType(String type) {
        switch (type) {
            case "Integer":
                return "INT";
            case "Float":
                return "FLOAT";
            case "String":
                return "CHAR";
            case "Boolean":
                return "BOOL";
            case "Object[]":
                return "VECTOR";
            case "Param[]":
                return "DATA";       
            default:
            throw new RuntimeException("Tipo inválido.");
        }
    }



}
