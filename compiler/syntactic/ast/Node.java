package lang.ast;

public abstract class Node extends beaver.Symbol implements SuperNode {  
    public Node(){}
    public int getLine(){ return super.getLine(getStart()); }
    public int getCol(){ return super.getColumn(getStart());}  
}