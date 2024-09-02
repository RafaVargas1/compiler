//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public class Program extends Node {
    private Node[] prog;

    public Program(Node[] prog){
        this.prog = prog;
    }
}
