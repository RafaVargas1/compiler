package lang.ast;

public abstract class Component extends Node {
    private String id;
    private String parent;
    
    public Component(String parent, String id){
        this.id = id;
        this.parent = parent;
    }
}
