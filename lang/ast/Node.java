//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public abstract class Node extends beaver.Symbol implements SuperNode {  
    public Node(){}
    public int getLine(){ return super.getLine(getStart()); }
    public int getColumn(){ return super.getColumn(getStart());}  
}