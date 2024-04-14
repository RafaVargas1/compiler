import java.io.IOException;

public class Teste {
    public static void main(String args[]) throws IOException {
        Scanner lex = new Scanner(args[0]);
        Token token = lex.nextToken();

        while(token.getToken() != Token.EOF) {
            System.out.println("Token: " + token.getToken() + " lexeme: " + token.getLexeme());
            token = lex.nextToken();
        }
    }
}
