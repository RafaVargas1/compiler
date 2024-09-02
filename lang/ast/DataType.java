package lang.ast;

import interpreter.visitor.Visitor;

public class DataType extends Literal {
    private String id;

    public DataType(String id){
        super("DATA");
        this.id = id;
    }

    
}