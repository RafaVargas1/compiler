package lang.ast;

public class Program extends Node {
    private Node[] prog;

    public Program(Node[] prog){
        this.prog = prog;
    }
}
