package lang.ast;

public abstract class Read extends Node {
    private Node id;
    
    public Read(Node id){
         this.id = id;
    }    
}