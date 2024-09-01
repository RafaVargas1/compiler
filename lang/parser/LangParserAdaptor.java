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
            NodeList result = (NodeList)parser.parse(input);

            return result;
        } catch (Exception e) {
            return null;
        }
    }
}