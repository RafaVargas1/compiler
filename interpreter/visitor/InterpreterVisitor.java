package interpreter.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import lang.ast.*;
public class InterpreterVisitor extends Visitor {

    private Stack<HashMap<String,Object>> env;     
    private HashMap<String,Function> funcs;     
    private Stack<Object> operands;
    private HashMap<String, Object> datas;
    private boolean retMode, debug;

    public InterpreterVisitor() {
        env = new Stack<HashMap<String,Object>>();
        env.push(new HashMap<String,Object>());
        funcs = new  HashMap<String,Function>();
        operands = new Stack<Object>();
        datas = new HashMap<String, Object>();

        retMode = false;
        debug = false; 
    }

    public InterpreterVisitor(boolean debug) {
        this();
        this.debug = debug; 
    }

    // Verificar qual seria o Tipo equivalente a Program
    public void visit(SuperNode e) {
        Node main = null;
        for(Function f : p.getFuncs()){
            funcs.put(f.getID(),f);
            if(f.getID().equals("main")){
                main = f;
            }
        }
        if(main == null){
            throw new RuntimeException( "Não há uma função chamada inicio ! abortando ! ");
        }

        main.accept(this);
    }

    // public void visit( p) {
    //     Node main = null;
    //      for(Function f : p.getFuncs()){
    //          funcs.put(f.getID(),f);
    //          if(f.getID().equals("main")){
    //              main = f;
    //          }
    //      }
    //      if(main == null){
    //         throw new RuntimeException( "Não há uma função chamada inicio ! abortando ! ");
    //      }

    //      main.accept(this);
    // }

    // Operadores Matemáticos
    public void visit(Addition e){
        try{
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() +  dir.intValue() ) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Subtraction e){
        try{
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() -  dir.intValue() ) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Multiplication e){
        try{ 
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() *  dir.intValue() ) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Division e){
        try{ 
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Float(esq.intValue() /  dir.intValue() ) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }
    
    public  void visit(Mod e){
        try{ 
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq,dir;
            dir = (Number)operands.pop();
            esq = (Number)operands.pop();
            operands.push( new Integer(esq.intValue() %  dir.intValue() ) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Negative e){
        try{ 
            e.getN().accept(this);
            // Redefinir o valor para negativo mas para expressao
            // operands.pop();
            // operands.push( new Integer(e.getN() * -1) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    // Operadores Lógicos 
    public  void visit(And e){
        try{  
            e.getA().accept(this);
            e.getB().accept(this);
            Object esq,dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push( new Boolean( (Boolean)esq &&  (Boolean)dir ) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Smaller e){
        try{   
            e.getA().accept(this);
            e.getB().accept(this);
            Object esq,dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push( new Boolean( (Float)esq <  (Float)dir ) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Greater e){
        try{   
            e.getA().accept(this);
            e.getB().accept(this);
            Object esq,dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push( new Boolean( (Float)esq >  (Float)dir ) ); 
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Equal e){
        try{   
            e.getA().accept(this);
            e.getB().accept(this);
            operands.push( new Boolean( operands.pop().equals(operands.pop()) ) );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Different e){
        try{   
            e.getA().accept(this);
            e.getB().accept(this);
            operands.push( new Boolean( !operands.pop().equals(operands.pop()) ) );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }


    public  void visit(Not e){
        try{   
            e.getN().accept(this);
            operands.push (new Boolean( ! (Boolean)operands.pop() ) );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(True e){ 
        try{
            operands.push(  new Boolean(true));
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(False e){
        try{
            operands.push(  new Boolean(false));
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    // public  void visit(Array e){
    //     try{
    //         operands.push(  new Array(e));
    //     }catch(Exception x){
    //         throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
    //     }
    // }

    public  void visit(Null e){
        try{
            operands.push(  null );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Empty e){
        try{
            operands.push(  null );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }
    
    public void visit(IntegerVar e){ 
        try{   
            operands.push( new Integer(e.getValue()) );
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(FloatVar e){ 
        try{   
            operands.push( new Float(e.getValue() ));
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public void visit(CharacterVar e){
        try{   
            operands.push( new String(e.getValue() ));
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Call e){
        try{
            Function f = funcs.get(e.getName());
            if(f != null){
                for(Node node : e.getArgs()){
                node.accept(this);
            }

            f.accept(this);
            } else {
                throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") Função não definida " +  e.getName());
            }

        } catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    // Comandos 

    public  void visit(Atribuition e){
        try{   
            Var v = e.getId();
            e.getExpr().accept(this);
            Object val = operands.pop();
            
            if(v.getIdx() != null && v.getIdx().length > 0 ){
                ArrayList arr = (ArrayList)env.peek().get(v.getName());
                for(int k = 0; k < v.getIdx().length-1; k++ ){
                v.getIdx()[k].accept(this);
                arr = (ArrayList)arr.get( (Integer)operands.pop());
                }
                v.getIdx()[v.getIdx().length-1 ].accept(this);
                arr.set( (Integer)operands.pop(), val);
            }
            else{ env.peek().put(e.getExpr().getName(), val);}
            
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(If e){
        try{
            e.getTeste().accept(this);
            if((Boolean)operands.pop()){
                e.getThen().accept(this);
            }else if(e.getElse() != null){
                e.getElse().accept(this);
            }
        }catch(Exception x){
        throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Iterate e){
        try{
            e.getTeste().accept(this);

            while( (Boolean)operands.pop()){
                e.getBody().accept(this);
                e.getTeste().accept(this);
            }
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Print e){
        try{
            e.getExpr().accept(this);
            System.out.println(operands.pop().toString());
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(NodeList e){
        
        try{            
            if ( e.getCmd1() instanceof Data ) {
                datas.put(e.getCmd1().getID(), e.getCmd1().getParams());
            } else if ( e.getCmd1() instanceof Function ) {
                funcs.put( e.getCmd1().getID(), e.getCmd1().getBody());
            }

            if ( e.getCmd2() instanceof Data ) {
                datas.put(e.getCmd2().getID(), e.getCmd2().getParams());
            } else if ( e.getCmd2() instanceof Function ) {
                funcs.put( e.getCmd2().getID(), e.getCmd2().getBody());
            } else if ( e.getCmd2() instanceof NodeList ) {
                this.visit(e.getCmd2());
            }
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }  
    }

    public  void visit(Node e){
               
        try{
            e.accept(this);
           
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }  
    }

    public void visit(Function f){
        HashMap<String,Object> localEnv = new HashMap<String,Object>();
         for(int  i = f.getParams().length-1; i >= 0; i--){
            localEnv.put(f.getParams()[i].getParamId(),operands.pop());
         } 

        env.push(localEnv);
        f.getBody().accept(this);
        if(debug && f.getID().equals("main") ){
            
            Object[] x = env.peek().keySet().toArray(); 
            System.out.println("-------------- Memoria ----------------");
            for(int i =0; i < x.length; i++){
                System.out.println( ((String)x[i]) + " : " +  env.peek().get(x[i]).toString() );
            }
        }
        env.pop();
        retMode= false;      
    }

    public void visit(Instance e){
        try{   
            ID v = e.getId();
            e.getSize().accept(this);
            Integer size = (Integer)operands.pop();
            ArrayList val = new ArrayList(size);

            for(int i = 0; i< size; i++){ val.add(null);    }
            
            if( env.peek().get(v.getName()) == null ){
                env.peek().put(v.getName(), val);
            }else if(  v.getIdx() != null && v.getIdx().length > 0 ){
                ArrayList arr = (ArrayList)env.peek().get(v.getName());
                for(int k = 0; k < v.getIdx().length-1; k++ ){
                    v.getIdx()[k].accept(this);
                    arr = (ArrayList)arr.get( (Integer)operands.pop());
                }
                v.getIdx()[v.getIdx().length-1 ].accept(this);
                arr.set( (Integer)operands.pop(), val);
            }
            else{
                env.peek().put(e.getID().getName(), val);
            }
        
        }catch(Exception x){
            throw new RuntimeException( " (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage() );
        }
    }

    public  void visit(Return e){
        e.getExpr().accept(this);
        retMode = true;   
    }
     
    public  void visit(Param e){ }
     
    public  void visit(ParamList e){ }

    public void visit(BoolType e ){ 
        // e.getType().accept(this);
    }
    
    public void visit(CharType e ){ 
        // e.getType().accept(this);
    }

    public void visit(FloatType e ){
        // e.getType().accept(this);
    }

    public void visit(IntType e ){ 
       // e.getType().accept(this);
    }

    public void visit(Literal e ){ 
       // e.getType().accept(this);
    }

    public void vist(Data e){

        e.accept(this);
        


        if(debug && e.getId().equals("main") ){            
            Object[] x = env.peek().keySet().toArray(); 
            System.out.println("-------------- Memoria ----------------");
            for(int i =0; i < x.length; i++){
                System.out.println( ((String)x[i]) + " : " +  env.peek().get(x[i]).toString() );
            }
        }

        env.pop();
        retMode= false;     
    }

}
