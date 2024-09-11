package lang;

import java.io.*;
import lang.ast.Program;
import lang.lexer.LangScanner;
import lang.parser.LangParser;
import lang.parser.LangParserAdaptor;
import lang.parser.ParseAdaptor;
import lang.visitor.InterpreterVisitor;

public class tR {
    public static void main(String[] args) {
        System.out.println(" ------- Debug -------  ");

        try {
            FileReader file = new FileReader("tests/simple/01.lan");
            LangScanner lexicResult = new LangScanner(file);

            LangParser parser = new LangParser();
            Program ast = (Program) parser.parse(lexicResult);
            
            InterpreterVisitor visitor = new InterpreterVisitor();
            System.out.println("start debug visitor");
            ast.accept(visitor);  
            System.out.println("end debug visitor");
        } catch (Exception e) {
            System.err.println("Unexpected error:" + e);
        }
        


    }
}
