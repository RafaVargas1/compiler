//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang.parser;

import java.io.*;
import lang.ast.*;
import lang.lexer.*;
import lang.*;

public class LangParserAdaptor implements ParseAdaptor {
    public SuperNode parseFile(String path) {
        try {
            LangScanner input = new LangScanner(new FileReader(path));
            LangParser parser = new LangParser();
            Program result = (Program)parser.parse(input);

            return result;
        } catch (Exception e) {
            return null;
        }
    }
}