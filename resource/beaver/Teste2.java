import java.io.FileReader;
import java.io.IOException;
import ast.*;
import parsers.*;
import java.util.HashMap; 
import beaver.Symbol;
import beaver.Parser;
public class Teste2{
      public static void main(String[] args){
        HashMap<String,Integer> h = new HashMap<String,Integer>();
        try{
            MiniLangLex input = new MiniLangLex(new FileReader(args[0]));
            MiniLangParser p = new MiniLangParser();
            StmtList result = (StmtList) p.parse(input);
            if(p.isGood()){
                System.out.println("Parsado !");
                System.out.println(result.toString());
                System.out.println("--------- Executando ---------");
                System.out.println(result.interpret(h));
            }else{
                System.out.println( " Erros ocorreram durante o parser.\nAbortando");
            }
        }
        catch (IOException e){
            System.err.println("Failed to read expression: " + e.getMessage());
        }
        catch (beaver.Parser.Exception e){
            System.err.println("Invalid expression: " + e.getMessage());
        }
        catch (Exception e){
            System.err.println("Exceção: " + e.getMessage());
        } 
     }
}
