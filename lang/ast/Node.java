package lang.ast;

import interpreter.visitor.Visitable;
public abstract class Node extends beaver.Symbol implements SuperNode, Visitable {  
    public Node(){}
    
    public int getLine(){ return super.getLine(getStart()); }
    public int getColumn(){ return super.getColumn(getStart());}  
}