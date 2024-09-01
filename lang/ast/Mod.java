package lang.ast;

import java.util.HashMap; 
import interpreter.visitor.Visitor;

public class Mod extends BinaryOperation {

      public Mod(Expr l, Expr r){
           super(l,r);
      }
      
      public void accept(Visitor v){ v.visit(this);}
            
}
