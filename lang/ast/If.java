package lang.ast;

public class If extends Node {
    private Expr c;
    private Node t;
    private Node e;
    
    public If(Expr c, Node t, Node e){
         this.c = c;
         this.t = t;
         this.e = e;
    }    
          
    public If(Expr c, Node t){
         this.c = c;
         this.t = t;
         this.e = null;
    }

     public Expr getTeste(){ return c;}
     public Node getThen(){ return t;}
     public Node getElse(){ return e;}
}