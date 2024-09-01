//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

import token.*;
import lang.lexer.*;

import java.io.FileReader;
import java.io.IOException;

// import parsers.MiniLangParser;
// import interpreter.visitor.*;

import beaver.Symbol;

public class Main {
    public static void main(String args[]) throws IOException {
        // LangScanner scanner = new LangScanner(new FileReader(args[0]));
        // Symbol token;
        // token = scanner.nextToken();

        // while (token != null) {
        //     System.out.println(token.toString());
        //     token = scanner.nextToken();
        // }

   
        // System.out.println(new MiniLangParser().parse(scanner));

        // System.out.println(" ======= Inicio Interpretador ====== ");
        // InterpreterVisitor iv = new InterpreterVisitor();
        // result.accept(iv);

        //Scanner atualizado e parser atualizado
        // passar o novo lang scanner para o parser
        // retorno passa pro interpretador

        /* 
         * 
         * public class LangParserAdaptor implements ParseAdaptor {
    public SuperNode parseFile(String path) {
        try {
            LangScanner input = new LangScanner(new FileReader(path));
            LangParser parser = new LangParser();
            NodeList result = (NodeList)parser.parse(input);

            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
         */

        try {
            // Certifique-se de que o caminho do arquivo está correto
            LangScanner scanner = new LangScanner(new FileReader(args[0]));
            Symbol token;
            while ((token = scanner.nextToken()) != null) {
                System.out.println("Token: " + token);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace(); // Captura qualquer outra exceção lançada por nextToken()
        }
    }
}