package lang.parser;

import lang.lexer.*;
import lang.ast.*;

public class LangParser implements ParseAdaptor {
    public SuperNode parseFile(String path) {
        try {
            LangScanner input = new LangScanner(new FileReader(path));
            NodeList result = (NodeList) new LangParser().parse(input);

            return result;
        } catch (Exception e) {
            return null;
        }
    }
}