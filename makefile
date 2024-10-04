.PHONY: build

compile: parser lexer build

r: build run 

run:
	java -cp ./build:./lang/tools/beaver-rt-0.9.11.jar:./lang/tools/jasmin.jar:./lang/tools/ST-4.3.1.jar:./lang/tools/antlr-3.5.2-runtime.jar lang.LangCompiler $(option) $(file)
	
lexer:
	java -jar ./lang/tools/jflex-full-1.8.2.jar ./lang/lexer/lang.flex 
	
parser:
	java -jar ./lang/tools/beaver-cc-0.9.11.jar -T ./lang/parser/lang.grammar

build:
	javac -d build -cp .:./lang/tools/beaver-rt-0.9.11.jar:./lang/tools/jasmin.jar:./lang/tools/ST-4.3.1.jar:./lang/tools/antlr-3.5.2-runtime.jar lang/*.java

clear:
	rm -R lang/parser/LangParser.java lang/parser/Terminals.java lang/lexer/LangScanner.java lang/lexer/LangScanner.java~ build/

