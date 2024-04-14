%%

%unicode
%line
%column
%class Scanner
%function nextToken
%type Token

%{  
    private Token symbol(TOKEN_TYPE t) {
        return new Token(t, yytext());
    }
%}

%init{
     
%init}
  
  EndOfLine  = \r|\n|\r\n
  Empty     = {EndOfLine} | [ \t\f]
  Integer      = [:digit:] [:digit:]*
  Variable = [:lowercase:] [:lowercase:]* [:uppercase:]*
  LineComment = "--" (.)* {EndOfLine}
  
%state COMMENT

%%

<YYINITIAL>{
    "Int"           { return symbol(TOKEN_TYPE.INTEGER);  }
    {Variable}      { return symbol(TOKEN_TYPE.ID);   }
    {Integer}       { return symbol(TOKEN_TYPE.INT);  }
    "="             { return symbol(TOKEN_TYPE.ATRIBUITION);   }
    "+"             { return symbol(TOKEN_TYPE.PLUS); }
    "-"             { return symbol(TOKEN_TYPE.MINUS); }
    "*"             { return symbol(TOKEN_TYPE.MULTI); }
    "/"             { return symbol(TOKEN_TYPE.DIVIDE); }
    "%"             { return symbol(TOKEN_TYPE.INTDIVIDE); }
    "=="            { return symbol(TOKEN_TYPE.EQUAL); }
    ">"             { return symbol(TOKEN_TYPE.GREATER); }
    "<"             { return symbol(TOKEN_TYPE.SMALLER); }
    ":"             { return symbol(TOKEN_TYPE.DOTDOT); }
    "::"            { return symbol(TOKEN_TYPE.DOUBLEDOTDOT); }    
    ";"             { return symbol(TOKEN_TYPE.SEMI); }
    "("             { return symbol(TOKEN_TYPE.OPENPARENTHESIS); }
    ")"             { return symbol(TOKEN_TYPE.CLOSEPARENTHESIS); }
    "{"             { return symbol(TOKEN_TYPE.OPENKEYS); }
    "}"             { return symbol(TOKEN_TYPE.CLOSEKEYS); }
    "["             { return symbol(TOKEN_TYPE.OPENBRACKETS); }
    "]"             { return symbol(TOKEN_TYPE.CLOSEBRACKETS); }
    ","             { return symbol(TOKEN_TYPE.COLON);  }
    "{-"            { yybegin(COMMENT);               }
    {Empty}         {                       }
    {LineComment}   {                       }
}

<COMMENT>{
   "-}"     { yybegin(YYINITIAL); } 
}

[^]                 { throw new RuntimeException("Illegal character <"+yytext()+">"); }