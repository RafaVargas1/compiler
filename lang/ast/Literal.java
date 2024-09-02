//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.ast;

public abstract class Literal extends Node {
    private String type;

    public Literal(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
