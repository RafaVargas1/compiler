public class Token {
      public TOKEN_TYPE t;
      public String lexeme;

      public Token(TOKEN_TYPE t, String lex) {
            this.t = t;
            lexeme = lex;
      }

      @Override
      public String toString() {
            return (this.t == TOKEN_TYPE.ID || this.t == TOKEN_TYPE.INT ? this.t + ":" : "") + lexeme;
      }
}