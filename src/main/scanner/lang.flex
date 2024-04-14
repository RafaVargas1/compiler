//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package scanner;

import token.*;

%%

%unicode
%line
%column
%class Scanner
%public
%function nextToken
%type Token

%{  
    private Token symbol(TOKEN_TYPE t) {
        return new Token(t, yytext());
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
    "data"          { return symbol(TOKEN_TYPE.DATA);   }
    "if"            { return symbol(TOKEN_TYPE.IF);   }
    "else"          { return symbol(TOKEN_TYPE.ELSE);   }
    "iterate"       { return symbol(TOKEN_TYPE.ITERATE);   }
    "read"          { return symbol(TOKEN_TYPE.READ);   }
    "print"         { return symbol(TOKEN_TYPE.PRINT);   }
    "return"        { return symbol(TOKEN_TYPE.RETURN);   }
    "new"           { return symbol(TOKEN_TYPE.NEW);   }
    "true"          { return symbol(TOKEN_TYPE.TRUE);   }
    "false"         { return symbol(TOKEN_TYPE.FALSE);   }
    "null"          { return symbol(TOKEN_TYPE.NULL);   }

    "Int"           { return symbol(TOKEN_TYPE.INT);  }
    "Float"         { return symbol(TOKEN_TYPE.FLOAT);  }
    "Char"          { return symbol(TOKEN_TYPE.CHAR);  }
    "Bool"          { return symbol(TOKEN_TYPE.BOOL);  }
    
    {Variable}      { return symbol(TOKEN_TYPE.ID);   }
    {Type}          { return symbol(TOKEN_TYPE.TYPE);   }
    {Float}         { return symbol(TOKEN_TYPE.FLOATVAR);  }
    {Integer}       { return symbol(TOKEN_TYPE.INTEGER);  }
    {Char}          { return symbol(TOKEN_TYPE.CHARACTER);  }

    "("             { return symbol(TOKEN_TYPE.OPENPARENTHESIS); }
    ")"             { return symbol(TOKEN_TYPE.CLOSEPARENTHESIS); }
    "{"             { return symbol(TOKEN_TYPE.OPENKEYS); }
    "}"             { return symbol(TOKEN_TYPE.CLOSEKEYS); }
    "["             { return symbol(TOKEN_TYPE.OPENBRACKETS); }
    "]"             { return symbol(TOKEN_TYPE.CLOSEBRACKETS); }
    "=="            { return symbol(TOKEN_TYPE.EQUAL); }
    "="             { return symbol(TOKEN_TYPE.ATRIBUITION);   }
    ">"             { return symbol(TOKEN_TYPE.GREATER); }
    "<"             { return symbol(TOKEN_TYPE.SMALLER); }
    "!="            { return symbol(TOKEN_TYPE.DIFFERENT); }
    "+"             { return symbol(TOKEN_TYPE.PLUS); }
    "-"             { return symbol(TOKEN_TYPE.MINUS); }
    "*"             { return symbol(TOKEN_TYPE.MULTI); }
    "/"             { return symbol(TOKEN_TYPE.DIVIDE); }
    "%"             { return symbol(TOKEN_TYPE.INTDIVIDE); }
    "&&"            { return symbol(TOKEN_TYPE.AND); }
    "."             { return symbol(TOKEN_TYPE.DOT);    }
    "::"            { return symbol(TOKEN_TYPE.DOUBLEDOTDOT); } 
    ":"             { return symbol(TOKEN_TYPE.DOTDOT); }
    ","             { return symbol(TOKEN_TYPE.COMMA);  }  
    ";"             { return symbol(TOKEN_TYPE.SEMI); }
    "!"             { return symbol(TOKEN_TYPE.NEGATION); }

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