package lang.ast;

import interpreter.visitor.Visitor;

public class Read extends Node {
    private Node id;
    
    public Read(Node id){
         this.id = id;
    }    

    public void accept(Visitor v){ v.visit(this);}
}