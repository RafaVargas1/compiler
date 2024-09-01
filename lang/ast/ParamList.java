package lang.ast;

public class ParamList extends Node {
    private Param p;
    private ParamList l;

    public ParamList(Param p, ParamList l){
        this.p = p;
        this.l = l;
    }

    public ParamList(Param p){
        this.p = p;
        this.l = null;
    }
}