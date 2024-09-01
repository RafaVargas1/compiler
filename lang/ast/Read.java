package lang.ast;

public class Read extends Node {
    private Node id;
    
    public Read(Node id){
         this.id = id;
    }    
}