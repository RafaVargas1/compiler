package lang.ast;

import interpreter.visitor.Visitor;

public class Program extends Node {
    private Node[] prog;

    public Program(Node[] prog){
        this.prog = prog;
    }

    public void accept(Visitor v){ v.visit(this);}
}
