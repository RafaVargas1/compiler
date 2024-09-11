package lang.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import java.util.Scanner;

import lang.ast.*;

public class InterpreterVisitor extends Visitor {

    private Stack<HashMap<String, Object>> env;
    private HashMap<String, Function> funcs;
    private Stack<Object> operands;
    private HashMap<String, Object> datas;
    private boolean retMode, debug;
    private HashMap<String, Object> variablesValue;
    private HashMap<String, Literal> variablestype;

    public InterpreterVisitor() {
        env = new Stack<HashMap<String, Object>>();
        env.push(new HashMap<String, Object>());
        funcs = new HashMap<String, Function>();
        operands = new Stack<Object>();
        datas = new HashMap<String, Object>();
        variablesValue = new HashMap<String, Object>(); // Nome
        
        retMode = false;
        debug = false;
    }

    public InterpreterVisitor(boolean debug) {
        this();
        this.debug = debug;
    }

    public void visit(Expr e) {
        // Abstrato
    }

    public void visit(Program p) {
        
        try {
            for (Node n : p.getContent()) {
                if (n instanceof Data) {
                    n.accept(this);
                }
                if (n instanceof Function) {
                    n.accept(this);
                    //funcs.put(n.getName(), n);
                }
            }
        } catch (Exception x) {
            throw new RuntimeException(" Prog Error (" + p.getLine() + ", " + p.getColumn() + ") " + x.getMessage() + "Fim msg prog");
        }
    }

    // Operadores Matemáticos
    public void visit(Addition e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(new Integer(esq.intValue() + dir.intValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Subtraction e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(new Integer(esq.intValue() - dir.intValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Multiplication e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(new Integer(esq.intValue() * dir.intValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Division e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(new Float(esq.intValue() / dir.intValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Mod e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Number esq, dir;
            dir = (Number) operands.pop();
            esq = (Number) operands.pop();
            operands.push(new Integer(esq.intValue() % dir.intValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Negative e) {
        try {
            e.getN().accept(this);
            Integer num;
            num = (Integer) operands.pop();
            operands.push(new Integer(num * -1));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    // Operadores Lógicos
    public void visit(And e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push(new Boolean((Boolean) esq && (Boolean) dir));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Smaller e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push(new Boolean((Float) esq < (Float) dir));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Greater e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            Object esq, dir;
            dir = operands.pop();
            esq = operands.pop();
            operands.push(new Boolean((Float) esq > (Float) dir));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Equal e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            operands.push(new Boolean(operands.pop().equals(operands.pop())));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Different e) {
        try {
            e.getA().accept(this);
            e.getB().accept(this);
            operands.push(new Boolean(!operands.pop().equals(operands.pop())));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Not e) {
        try {
            e.getN().accept(this);
            operands.push(new Boolean(!(Boolean) operands.pop()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    // Variáveis
    public void visit(True e) {
        try {
            operands.push(new Boolean(true));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(False e) {
        try {
            operands.push(new Boolean(false));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Null e) {
        try {
            operands.push(null);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Empty e) {
        try {
            operands.push(null);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(IntegerVar e) {
        System.out.println("Numero inteiro ");
        System.out.println(e.getValue());
        try {
            operands.push(new Integer(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" Integer -> (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(FloatVar e) {
        try {
            operands.push(new Float(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(CharacterVar e) {
        try {
            operands.push(new String(e.getValue()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(ID e) {
        System.out.println("ID");
        try {
            // operands.push(new String(e.getName()));
            System.out.println("ID");
            System.out.println(e);
            // variablesValue.get(e.getName()).accept(this);ID
            operands.push(variablesValue.get(e.getName()));
            //variablesValue.get(e.getName());// Name, Expr
        } catch (Exception x) {
            throw new RuntimeException(" Error Variable -> (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Array e) {
        try {
            operands.push(new String(e.getName() + "[" + e.getIndex() + "]"));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Component e) {
        try {
            operands.push(new String(e.getParent() + "." + e.getName()));
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    // Comandos
    public void visit(Call e) {
        try {
            Function f = funcs.get(e.getName());
            if (f != null) {
                for (Node node : e.getArgs()) {
                    node.accept(this);
                }
                f.accept(this);
            } else {
                throw new RuntimeException(" Function error -> (" + e.getLine() + ", " + e.getColumn() + ") Função não definida " + e.getName());
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Atribuition e) {
        System.out.println("Atribuitoon");
        try {
            Node v = e.getName();
 
  
            // variablesValue.put(e.getName(), );
            e.getExpr().accept(this);;
            System.out.println(operands);
            if ( v instanceof ID) {
                ID v2 = (ID) v;
                variablesValue.put(v2.getName(), operands.pop()); // Nome da variavel i
                // v.getName()
            }


            System.out.println("->" + e.getExpr());
            System.out.println(operands);
            // System.out.println(operands);
            // Object val = operands.pop();
        //    x = i + 3
            // System.out.println(e.getName());
            // System.out.println(e.getExpr());
            // if ( arr.size() > 0 ) {

            // } else { 
            //     // env.peek().put(e.getExpr().getName(), val);
            // }
            
            // System.out.println(env);
            //if (arr.size() > 0) {
            //    for (Node n : arr) {
            //        n.accept(this);
            //        arr = (ArrayList<Node>) arr.get((Integer) operands.pop());
            //    }
            //    arr.set((Integer) operands.pop(), (Node) val);
            //} else {
            //    env.peek().put(e.getExpr().getName(), val);
            //}
        } catch (Exception x) {
            throw new RuntimeException("Atribuiton -> (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(If e) {
        try {
            e.getTeste().accept(this);
            if ((Boolean) operands.pop()) {
                e.getThen().accept(this);
            } else if (e.getElse() != null) {
                e.getElse().accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Iterate e) {
        try {
            e.getTeste().accept(this);

            while ((Boolean) operands.pop()) {
                e.getBody().accept(this);
                e.getTeste().accept(this);
            }
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Print e) {
        System.out.println("Print");
        try {
            e.getExpr().accept(this);
            System.out.println(operands.pop().toString());

        } catch (Exception x) {
            throw new RuntimeException(" Print -> (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Read e) {
        try {
            e.getName().accept(this);
            //new Scanner(System in).nextLine().accept(this); 
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }


    public void visit(NodeList e) {
        System.out.println("cmd1 -> " + e.getCmd1());
        System.out.println("cmd -> " + e.getCmd2());
        try {
            e.getCmd1().accept(this);
            e.getCmd2().accept(this);
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Function f) {
        HashMap<String, Object> localEnv = new HashMap<String, Object>();
        for (int i = f.getParams().length - 1; i >= 0; i--) {
            localEnv.put(f.getParams()[i].getParamId(), operands.pop());
        }
        
        env.push(localEnv);
        System.out.println();
        f.getBody().accept(this);
        env.pop();
        retMode = false;
    }

    public void visit(Instance e) {
        try {
            //ID v = e.getName();
            e.getSize().accept(this);
            Integer size = (Integer) operands.pop();
            ArrayList val = new ArrayList(size);

            for (int i = 0; i < size; i++) {
                val.add(null);
            }

            //if (env.peek().get(v.getName()) == null) {
            //    env.peek().put(v.getName(), val);
            //} else if (v.getIdx() != null && v.getIdx().length > 0) {
            //    ArrayList arr = (ArrayList) env.peek().get(v.getName());
            //    for (int k = 0; k < v.getIdx().length - 1; k++) {
            //        v.getIdx()[k].accept(this);
            //        arr = (ArrayList) arr.get((Integer) operands.pop());
            //    }
            //    v.getIdx()[v.getIdx().length - 1].accept(this);
            //    arr.set((Integer) operands.pop(), val);
            //} else {
            //    env.peek().put(e.getName().getName(), val);
            //}

        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(Return e) {
        //e.getExpr().accept(this);
        retMode = true;
    }

    public void visit(Param e) {
        //
    }

    public void visit(BoolType e) {
        // e.getType().accept(this);
    }

    public void visit(CharType e) {
        // e.getType().accept(this);
    }

    public void visit(FloatType e) {
        // e.getType().accept(this);
    }

    public void visit(IntType e) {
        // e.getType().accept(this);
    }

    public void visit(Vector e) {
        // e.getType().accept(this);
    }

    public void visit(DataType e) {
        // e.getType().accept(this);
    }

    public void visit(Data e) {
        //for (Param p : e.getParams()) {
        //    p.getParamId().accept(this);
        //    p.getParamType().accept(this);
        //}
        try {
            //datas.put(n.getName(), n.getParams());
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }

    public void visit(IndexedCall e) {
        try {
            //Function f = funcs.get(e.getName());
            //if (f != null) {
            //    for (Node node : e.getArgs()) {
            //        node.accept(this);
            //    }
            //    f.accept(this);

            //    while(f.getTipo().length() - e.getIndex() + 1 > 0) {
            //        operands.pop();
            //    }
            //    operands.pop().accept(this);
            //} else {
            //    throw new RuntimeException(
            //            " (" + e.getLine() + ", " + e.getColumn() + ") Função não definida " + e.getName());
            //}
        } catch (Exception x) {
            throw new RuntimeException(" (" + e.getLine() + ", " + e.getColumn() + ") " + x.getMessage());
        }
    }
}
