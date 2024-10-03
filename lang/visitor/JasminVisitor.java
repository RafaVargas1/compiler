package lang.visitor;

import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.ST;

import lang.ast.*;
import lang.visitor.Visitor;

import java.util.List;
import java.util.ArrayList;

public class JasminVisitor extends Visitor {

    private STGroup groupTemplate;
    private List<String> jasminCode;

    public JasminVisitor() {
        groupTemplate = new STGroupFile("./tools/jasmin.stg");  // Carrega o template Jasmin
        jasminCode = new ArrayList<>();
    }

    public void visit(Function e) {
        // Gerar o código Jasmin para funções
        ST funcTemplate = groupTemplate.getInstanceOf("function");
        funcTemplate.add("name", e.getName());
        funcTemplate.add("params", e.getParams());
        funcTemplate.add("returnType", e.getReturnType());

        e.getBody().accept(this);  // Visita o corpo da função

        jasminCode.add(funcTemplate.render());  // Adiciona o código gerado
    }

    public void visit(Call e) {
        // Gerar código Jasmin para chamadas de função
        ST callTemplate = groupTemplate.getInstanceOf("call");
        callTemplate.add("name", e.getName());

        for (Expr arg : e.getArgs()) {
            arg.accept(this);  // Visita os argumentos da chamada
        }

        jasminCode.add(callTemplate.render());
    }

    public void visit(Atribuition e) {
        // Gerar código Jasmin para variáveis
        ST varTemplate = groupTemplate.getInstanceOf("variable");
        varTemplate.add("name", e.getName());
        // varTemplate.add("type", Object);

        jasminCode.add(varTemplate.render());
    }

    public void visit(Addition e) {
        // Gerar código Jasmin para expressões binárias
        e.getA().accept(this);  // Visita o operando esquerdo
        e.getB().accept(this); // Visita o operando direito

        ST binaryTemplate = groupTemplate.getInstanceOf("binaryExpr");
        binaryTemplate.add("op", '+');

        jasminCode.add(binaryTemplate.render());
    }

    public void visit(Return e) {
        // Gerar código Jasmin para instruções de retorno
        ST returnTemplate = groupTemplate.getInstanceOf("return");
        // e.getExpr().accept(this);  // Visita o valor de retorno

        jasminCode.add(returnTemplate.render());
    }

    public void visit(If e) {
        // Gerar código Jasmin para instruções if
        ST ifTemplate = groupTemplate.getInstanceOf("ifStmt");
        e.getTeste().accept(this);  // Visita a condição

        ifTemplate.add("thenBlock", e.getThen());
        if (e.getElse() != null) {
            ifTemplate.add("elseBlock", e.getElse());
        }

        jasminCode.add(ifTemplate.render());
    }

    public void visit(Program e) {
        System.out.println("iNicio jasmin");
    }

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

    public List<String> getJasminCode() {
        return jasminCode;
    }



}
