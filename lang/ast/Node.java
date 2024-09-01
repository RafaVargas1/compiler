package lang.ast;

public abstract class Node extends beaver.Symbol implements SuperNode {  
    public Node(){}
    public int getLine(){ return super.getLine(getStart()); }
    public int getColumn(){ return super.getColumn(getStart());}  
}