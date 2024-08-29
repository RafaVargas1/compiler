package lang.ast;

public abstract class ParamList extends Param {
    private Param p;
    private Param l;

    public ParamList(Param p, Param l){
        this.p = p;
        this.l = l;
    }

    public ParamList(Param p){
        this.p = p;
        this.l = null;
    }
}