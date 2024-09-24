package lang;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import lang.ast.Program;
import lang.lexer.LangScanner;
import lang.parser.LangParser;
import lang.parser.LangParserAdaptor;
import lang.parser.ParseAdaptor;
import lang.visitor.InterpreterVisitor;
import lang.visitor.SemanticVisitor;

public class tR {

    public static void Interpreter(Program ast) {
        InterpreterVisitor visitor = new InterpreterVisitor();
        System.out.println("Start Interpreter");
        ast.accept(visitor);
        System.out.println("End Interpreter");
    }

    public static void main(String[] args) {
        try {
            // Diretório contendo os arquivos de teste
            File directory = new File("tests/semantica/errado");
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".lan"));

            if (files != null && files.length > 0) {
                List<String> correctFiles = new ArrayList<>();
                List<String> errorFiles = new ArrayList<>();

                for (File file : files) {
                    System.out.println("Processing file: " + file.getName());
                    try (FileReader fileReader = new FileReader(file)) {
                        LangScanner lexicResult = new LangScanner(fileReader);
                        LangParser parser = new LangParser();
                        Program ast = (Program) parser.parse(lexicResult);

                        // Verificação semântica
                        SemanticVisitor semanticVisitor = new SemanticVisitor();
                        ast.accept(semanticVisitor);

                        //SemanticVisitor semanticVisitor = new SemanticVisitor();
                        //ast.accept(semanticVisitor);

                        if (!semanticVisitor.getSemanticErrors().isEmpty()) {
                            errorFiles.add(file.getName());
                            System.err.println("Erros semânticos encontrados no arquivo: " + file.getName());
                            for (String error : semanticVisitor.getSemanticErrors()) {
                                System.err.println(error);
                            }
                        } else {
                            // Interpreter(ast);
                            correctFiles.add(file.getName());
                            System.out.println("Arquivo correto sem erros semânticos: " + file.getName());
                        }
                    } catch (Exception e) {
                        System.err.println("Erro inesperado ao processar o arquivo " + file.getName() + ": " + e);
                    }
                }

                // Exibindo os resultados acumulados
                System.out.println("\nArquivos sem erros semânticos:");
                for (String correctFile : correctFiles) {
                    System.out.println(correctFile);
                }

                System.out.println("Total: " + correctFiles.size());


                System.out.println("\nArquivos com erros semânticos:");
                for (String errorFile : errorFiles) {
                    System.out.println(errorFile);

                }
                System.out.println("Total: " + errorFiles.size());

            } else {
                System.out.println("Nenhum arquivo .lan encontrado na pasta tests/semantica.");
            }
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e);
        }
    }
}
