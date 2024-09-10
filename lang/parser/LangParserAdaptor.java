//Arthur Vieira da Silva    - 202035013
// Rafael de Oliveira Vargas - 202035022

package lang.parser;

import lang.parser.*;
import lang.lexer.*;

import java.io.FileReader;
import java.io.IOException;

import lang.ast.*;

import beaver.Symbol;
import lang.visitor.*;Interpreter
import lang.visitor.*;


public class LangParserAdaptor implements ParseAdaptor {

    public SuperNode parseFile(String path) {
        try {
            LangScanner input = new LangScanner(new FileReader(path));
            LangParser parser = new LangParser();
            Program result = (Program) parser.parse(input);

            return result;
        } catch (Exception e) {
            e.printStackTrace(); 
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Passe um arquivo como parametro");
            return;
        }

        LangParserAdaptor adaptor = new LangParserAdaptor();
        Program r = (Program) adaptor.parseFile(args[0]);

        Visitor visitor = new InterpreterVisitor();

        if (r != null) {
            r.accept(visitor);
        }
        
        System.out.println(r);
    }
}