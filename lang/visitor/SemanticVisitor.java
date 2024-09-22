package lang.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import java.util.Scanner;
import lang.ast.*;


public class SemanticVisitor extends Visitor {

    private Stack<HashMap<String, Object>> globalEnv;
    private HashMap<String, Function> funcs;
    private Stack<Object> operands;
    private HashMap<String, Object> datas;
    private boolean retMode, debug;


    // Estrutura para armazenar erros semânticos
    private List<String> semanticErrors = new ArrayList<>();

    public List<String> getSemanticErrors() {
        return semanticErrors;
    }

    public SemanticVisitor() {
        globalEnv = new Stack<HashMap<String, Object>>();
        globalEnv.push(new HashMap<String, Object>()); // Inicializa ambiente global

        funcs = new HashMap<String, Function>();
        operands = new Stack<Object>();
        datas = new HashMap<String, Object>();

        retMode = false;
        debug = false;
    }

    // Método para visitar o nó do programa principal
    @Override
    public void visit(Program p) {
        
        try {
            Node main = null;
            for (Node n : p.getContent()) {
                if (n instanceof Data) {
                    Data d = (Data) n;
                    datas.put(d.getName(), d.getDecl());

                }

                if (n instanceof Function) {
                    Function function = (Function) n;
                    funcs.put(function.getName(), function);

                    if (function.getName().equals("main")){
                        main = function;
                    }

                }
            }
                    
            if(main == null){
                throw new RuntimeException( "Defina uma função main");
            }

            main.accept(this);
        } catch (Exception x) {
            // x.printStackTrace();
            throw new RuntimeException(x);
            //throw new RuntimeException(" Prog Error (" + p.getLine() + ", " + p.getColumn() + ") " + x.getMessage() + "Fim msg prog");
        }
    }

    // Método para visitar funções
    @Override
    public void visit(Function f) {
        // Verificar a declaração de variáveis, escopo, e tipos de retorno
        if (!isValidReturnType(f)) {
            semanticErrors.add("Erro: tipo de retorno inválido na função " + f.getName());
        }

        // Continuar a análise dos parâmetros e corpo da função
        for (Param param : f.getParams()) {
            param.accept(this);
        }
        f.getBody().accept(this);
    }

    // Método para visitar atribuições
    @Override
    public void visit(Atribuition a) {
        // Verificar a validade da atribuição de tipos
        // Expr left = a.getExpr();
        // Expr right = a.getRight();

        // // Supondo que tenha métodos getType() para expressões
        // if (!left.getType().equals(right.getType())) {
        //     semanticErrors.add("Erro: atribuição de tipos incompatíveis em " + a.toString());
        // }

        // Continuar a visitar as expressões
        // left.accept(this);
        // right.accept(this);
    }

    // Método para visitar expressões (exemplo com operação binária)
    @Override
    public void visit(Addition add) {
        // Verifica se os operandos são do tipo esperado
        add.getA().accept(this);
        add.getB().accept(this);

        // if (!add.getA().getType().equals("int") || !add.getRight().getType().equals("int")) {
        //     semanticErrors.add("Erro: adição de tipos incompatíveis em " + add.toString());
        // }
    }

    // Demais métodos para outros nós da AST (como IDs, Operações, Condições, etc.)
    
    // @Override
    // public void visit(ID id) {
    //     // Verifica se a variável foi declarada
    //     if (!isDeclared(id)) {
    //         semanticErrors.add("Erro: variável " + id.getName() + " não declarada.");
    //     }
    // }

    // @Override
    // public void visit(If ifNode) {
    //     // Verifica se a condição do IF é booleana
    //     if (!ifNode.getCondition().getType().equals("bool")) {
    //         semanticErrors.add("Erro: condição do IF deve ser booleana em " + ifNode.toString());
    //     }

    //     // Visita o corpo do IF e do ELSE
    //     ifNode.getCondition().accept(this);
    //     ifNode.getThenBlock().accept(this);
    //     if (ifNode.getElseBlock() != null) {
    //         ifNode.getElseBlock().accept(this);
    //     }
    // }

    // Funções auxiliares para a verificação de tipos e variáveis
    private boolean isValidReturnType(Function f) {
        // Implementação da verificação do tipo de retorno da função
        return true; // Placeholder
    }

    private boolean isDeclared(ID id) {
        // Implementação para verificar se a variável foi declarada
        return true; // Placeholder
    }


    public void visit (Subtraction e) {};
public void visit (Multiplication e) {};
public void visit (Division e) {};
public void visit (Mod e) {};
public void visit (Negative e) {};
public void visit (And e) {};
public void visit (Smaller e) {};
public void visit (Greater e) {};
public void visit (Equal e) {};
public void visit (Different e) {};
public void visit (Not e) {};
public void visit (True e) {};
public void visit (False e) {};
public void visit (Null e) {};
public void visit (Empty e) {};
public void visit (IntegerVar e) {};
public void visit (FloatVar e) {};
public void visit (CharacterVar e) {};
public void visit (ID e) {};
public void visit (Array e) {};
public void visit (Component e) {};
public void visit (If e) {};
public void visit (Iterate e) {};
public void visit (Print e) {};
public void visit (Read e) {};
public void visit (NodeList e) {};
public void visit (Call e) {};
public void visit (Instance e) {};
public void visit (Return e) {};
public void visit (Param e) {};
public void visit (BoolType e) {};
public void visit (CharType e) {};
public void visit (FloatType e) {};
public void visit (IntType e) {};
public void visit (DataType e) {};
public void visit (Vector e) {};
public void visit (Data e) {};
public void visit (Expr e) {};
public void visit (IndexedCall e) {};


}
