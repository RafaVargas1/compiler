.PHONY: build

compile: parser lexer build

run:
	java -cp ./build:./lang/tools/beaver-rt-0.9.11.jar lang.LangCompiler -bs
	
lexer:
	java -jar ./lang/tools/jflex-full-1.8.2.jar ./lang/lexer/lang.flex 
	
parser:
	java -jar ./lang/tools/beaver-cc-0.9.11.jar -T ./lang/parser/lang.grammar

build:
	javac -d build -cp .:./lang/tools/beaver-rt-0.9.11.jar lang/*.java
	
clear:
	rm -R lang/parser/LangParser.java lang/parser/Terminals.java lang/lexer/LangScanner.java lang/lexer/LangScanner.java~ build/

i: 
	javac -cp .:./build:./lang/tools/beaver-rt-0.9.11.jar -d build src/main/LangParserAdaptor.java
	java -cp ./build:./lang/tools/beaver-rt-0.9.11.jar LangParserAdaptor testes/semantica/certo/teste4.lan


