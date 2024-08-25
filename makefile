compile: genparser genlex
	javac -cp .:beaver-rt-0.9.11.jar Teste.java

genparser: parsers/lang.grammar
	java -jar beaver-cc-0.9.11.jar -T parsers/lang.grammar

genlex: parsers/lang.jflex genparser
	java -jar jflex-full-1.8.2.jar parsers/lang.jflex

run: compile
	java -cp .:beaver-rt-0.9.11.jar Teste $(filter-out $@,$(MAKECMDGOALS))

clean:
	rm -R parsers/MiniLang*.java parsers/Terminals.java
	find . -type f -name "*.class" -delete
	find . -type f -name "*~" -delete
