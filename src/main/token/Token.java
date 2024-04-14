//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package token;

public class Token {
      public TOKEN_TYPE t;
      public String lexeme;

      public Token(TOKEN_TYPE t, String lex) {
            this.t = t;
            lexeme = lex;
      }

      @Override
      public String toString() {
            return (
                  this.t == TOKEN_TYPE.ID ? this.t + ":" :
                  this.t == TOKEN_TYPE.TYPE ? "TYPE:" : 
                  this.t == TOKEN_TYPE.INTEGER ? "INT:" : 
                  this.t == TOKEN_TYPE.FLOATVAR ? "FLOAT:" :
                  this.t == TOKEN_TYPE.CHARACTER ? "CHAR:" :
                  ""
            ) + lexeme;
      }
}