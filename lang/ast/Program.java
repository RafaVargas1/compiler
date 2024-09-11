//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import lang.visitor.*;

public class Program extends Node {
    private Node[] prog;

    public Program(Node[] prog){
        this.prog = prog;
    }

    public Node[] getContent() { return this.prog; }

    public void accept(Visitor v){ System.out.println("Program"); v.visit(this);}
}
