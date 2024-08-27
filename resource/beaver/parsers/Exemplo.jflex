
 /*  Esta seção é copiada antes da declaração da classe do analisador léxico.
  *  É nesta seção que se deve incluir imports e declaração de pacotes.
  *  Neste exemplo não temos nada a incluir nesta seção.
  */
  
package parsers;

import beaver.Symbol;
import beaver.Scanner;
import java.math.BigDecimal;

%%
%public
%class MiniLangLex
%extends Scanner
%function nextToken
%type Symbol
%yylexthrow Scanner.Exception
%eofval{
	return newToken(Terminals.EOF, "end-of-file");
%eofval}
%unicode
%line
%column
%{
	private Symbol newToken(short id)
	{
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength());
	}

	private Symbol newToken(short id, Object value)
	{
		return new Symbol(id, yyline + 1, yycolumn + 1, yylength(), value);
	}
%}
LineTerminator = \r|\n|\r\n
WhiteSpace     = {LineTerminator} | [ \t\f]

  
  
  /* Agora vamos definir algumas macros */
  FimDeLinha  = \r|\n|\r\n
  Brancos     = {FimDeLinha} | [ \t\f]
  numero      = [:digit:] [:digit:]*
  identificador = [:lowercase:] ([:lowercase:] | [:uppercase:] | [:digit:])*
  lineCmt       = "//" .* {FimDeLinha}
  
%state COMMENT

%%

<YYINITIAL>{
    {identificador} { return newToken(Terminals.ID, yytext());   }
    {numero}        { return newToken(Terminals.NUM, Integer.parseInt(yytext()) );  }
    "="             { return newToken(Terminals.EQ);   }
    ";"             { return newToken(Terminals.SEMI); }
    
    ":"             { return newToken(Terminals.COLON);}
    "?"             { return newToken(Terminals.IF);   }
    "("             { return newToken(Terminals.AP);   }
    ")"             { return newToken(Terminals.FP);   }
    "["             { return newToken(Terminals.LB);   }
    "]"             { return newToken(Terminals.RB);   }
    "*"             { return newToken(Terminals.MULT);}
    "+"             { return newToken(Terminals.PLUS); }
    "/*"            { yybegin(COMMENT);               }    
    {Brancos}       { /* Não faz nada  */             }
    {lineCmt}       { /* Não faz nada  */             }

}

<COMMENT>{
   "*/"     { yybegin(YYINITIAL); } 
   [^"*/"]* {                     }
}

[^]                 { throw new RuntimeException("Illegal character <"+yytext()+">"); }



