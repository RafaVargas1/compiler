//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

import beaver.Symbol;
import beaver.Scanner;

%%

%unicode
%line
%column
%public
%class LangScanner
%extends Scanner
%function nextToken
%type Symbol
%yylexthrow Scanner.Exception
%eofval{
    return newToken(Terminals.EOF, "end-of-file");
%eofval}

%{
    private Symbol newToken(short id) {
        return new Symbol(id, yyline+1, yycolumn+1, yylength() ) ;
    }
    private Symbol newToken(short id, Object value) {
        return new Symbol(id, yyline+1, yycolumn+1, yylength() , value) ;
    }
%}

%init{
     
%init}
  
  EndOfLine     = \r|\n|\r\n
  Empty         = {EndOfLine} | [ \t\f]
  Variable      = [:lowercase:] ([:lowercase:] | [:uppercase:] | "_")*
  Type          = [:uppercase:] ([:lowercase:] | [:uppercase:] | "_")*
  Integer       = [:digit:] [:digit:]*
  Float         = ([:digit:] | {Empty}) "." [:digit:]*
  Char          = "'" ([:lowercase:] | [:uppercase:] | [:digit:] | \\n | \\t | \\b | \\r | \\\\ | \\' | \\\\') "'"
  LineComment   = "--" (.)* {EndOfLine}
  
%state COMMENT

%%

<YYINITIAL>{
    "data"          { return newToken(Terminals.DATA);   }
    "if"            { return newToken(Terminals.IF);   }
    "else"          { return newToken(Terminals.ELSE);   }
    "iterate"       { return newToken(Terminals.ITERATE);   }
    "read"          { return newToken(Terminals.READ);   }
    "print"         { return newToken(Terminals.PRINT);   }
    "return"        { return newToken(Terminals.RETURN);   }
    "new"           { return newToken(Terminals.NEW);   }
    "true"          { return newToken(Terminals.TRUE);   }
    "false"         { return newToken(Terminals.FALSE);   }
    "null"          { return newToken(Terminals.NULL);   }

    "Int"           { return newToken(Terminals.INT);  }
    "Float"         { return newToken(Terminals.FLOAT);  }
    "Char"          { return newToken(Terminals.CHAR);  }
    "Bool"          { return newToken(Terminals.BOOL);  }
    
    {Variable}      { return newToken(Terminals.ID);   }
    {Type}          { return newToken(Terminals.TYPE);   }
    {Float}         { return newToken(Terminals.FLOATVAR);  }
    {Integer}       { return newToken(Terminals.INTEGER);  }
    {Char}          { return newToken(Terminals.CHARACTER);  }

    "("             { return newToken(Terminals.OPENPARENTHESIS); }
    ")"             { return newToken(Terminals.CLOSEPARENTHESIS); }
    "{"             { return newToken(Terminals.OPENKEYS); }
    "}"             { return newToken(Terminals.CLOSEKEYS); }
    "["             { return newToken(Terminals.OPENBRACKETS); }
    "]"             { return newToken(Terminals.CLOSEBRACKETS); }
    "=="            { return newToken(Terminals.EQUAL); }
    "="             { return newToken(Terminals.ATRIBUITION);   }
    ">"             { return newToken(Terminals.GREATER); }
    "<"             { return newToken(Terminals.SMALLER); }
    "!="            { return newToken(Terminals.DIFFERENT); }
    "+"             { return newToken(Terminals.PLUS); }
    "-"             { return newToken(Terminals.MINUS); }
    "*"             { return newToken(Terminals.MULTI); }
    "/"             { return newToken(Terminals.DIVIDE); }
    "%"             { return newToken(Terminals.INTDIVIDE); }
    "&&"            { return newToken(Terminals.AND); }
    "."             { return newToken(Terminals.DOT);    }
    "::"            { return newToken(Terminals.DOUBLEDOTDOT); } 
    ":"             { return newToken(Terminals.DOTDOT); }
    ","             { return newToken(Terminals.COMMA);  }  
    ";"             { return newToken(Terminals.SEMI); }
    "!"             { return newToken(Terminals.NEGATION); }

    {Empty}         {                       }

    {LineComment}   {                       }    
    "{-"            { yybegin(COMMENT);     }
}

<COMMENT>{
    "-}"            { yybegin(YYINITIAL); } 
    "}"             {   }
    [^"-}"]*        {   }
}

[^]                 { throw new RuntimeException("Illegal character <"+yytext()+">"); }