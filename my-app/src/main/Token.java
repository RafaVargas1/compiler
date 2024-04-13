
public class Token {
    public static final int VAR = 1; // a,b,c,...
    public static final int INT = 2; // 1,2,3, ...
    public static final int EQ = 3; // =
    public static final int SEMI = 4; // ;
    public static final int PLUS = 5; // +
    public static final int MULT = 6; // *
    public static final int EOF = -1; // fim de arquivo

    private int line, col;
    private String text;
    private int sym;

    public Token(int sym, String text, int line, int col) {
        this.sym = sym;
        this.text = text;
        this.line = line;
        this.col = col;
    }

    public int getLine() { return line; }
    public int getColumn() { return col; }
    public String getLexeme() { return text; } 
    public int getToken() { return sym; }
}
