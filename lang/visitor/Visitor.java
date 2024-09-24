package lang.visitor;

import lang.ast.*;

public abstract class Visitor {
    public abstract void visit(Program e);
    public abstract void visit(Data e);
    public abstract void visit(Function e);

    public abstract void visit(NodeList e);
    
    public abstract void visit(Addition e);
    public abstract void visit(Subtraction e);
    public abstract void visit(Multiplication e);
    public abstract void visit(Division e);
    public abstract void visit(Mod e);
    public abstract void visit(Negative e);
    
    public abstract void visit(And e);
    public abstract void visit(Smaller e);
    public abstract void visit(Greater e);
    public abstract void visit(Equal e);
    public abstract void visit(Different e);
    public abstract void visit(Not e);

    public abstract void visit(True e);
    public abstract void visit(False e);
    public abstract void visit(Null e);

    public abstract void visit(IntegerVar e);
    public abstract void visit(FloatVar e);
    public abstract void visit(CharacterVar e);
    public abstract void visit(ID e); 
    public abstract void visit(Array e);
    public abstract void visit(Component e);

    public abstract void visit(Instance e);
    public abstract void visit(IndexedCall e);
    
    public abstract void visit(Atribuition e);
    public abstract void visit(If e);
    public abstract void visit(Iterate e);
    public abstract void visit(Print e);
    public abstract void visit(Read e);
    public abstract void visit(Call e);
    public abstract void visit(Return e);

    public abstract void visit(Param e);
    public abstract void visit(BoolType e);
    public abstract void visit(CharType e);
    public abstract void visit(FloatType e);
    public abstract void visit(IntType e);
    public abstract void visit(Vector e); 
    public abstract void visit(DataType e);
}
























