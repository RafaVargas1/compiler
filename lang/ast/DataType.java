//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

import interpreter.visitor.Visitor;

public class DataType extends Literal {
    private String id;

    public DataType(String id){
        super("DATA");
        this.id = id;
    }

    public void accept(Visitor v){ v.visit(this);}
}