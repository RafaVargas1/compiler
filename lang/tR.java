package lang;

import java.io.*;
import lang.ast.Program;
import lang.lexer.LangScanner;
import lang.parser.LangParser;
import lang.parser.LangParserAdaptor;
import lang.parser.ParseAdaptor;
import lang.visitor.InterpreterVisitor;
import lang.visitor.SemanticVisitor;

public class tR {

    public static void Interpreter(  Program ast ) {
        InterpreterVisitor visitor = new InterpreterVisitor();
        System.out.println("Start Interpreter");
        ast.accept(visitor);  
        System.out.println("End Interpreter");
    }

    
    public static void main(String[] args) {
        System.out.println(" ------- Debug -------  ");

        try {
            FileReader file = new FileReader("tests/simple/01.lan");
            LangScanner lexicResult = new LangScanner(file);

            LangParser parser = new LangParser();
            Program ast = (Program) parser.parse(lexicResult);

            Interpreter(ast);

           //SemanticVisitor semanticVisitor = new SemanticVisitor();
           //ast.accept(semanticVisitor);

           //if (!semanticVisitor.getSemanticErrors().isEmpty()) {
           //    System.err.println("Erros semânticos encontrados:");
           //    for (String error : semanticVisitor.getSemanticErrors()) {
           //        System.err.println(error);
           //    }
           //}

 
        } catch (Exception e) {
            System.err.println("Unexpected error:" + e);
        }
        


    }
}
