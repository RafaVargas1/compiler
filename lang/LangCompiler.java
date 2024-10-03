//Arthur Vieira da Silva    - 202035013
//Rafael de Oliveira Vargas - 202035022

package lang;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import lang.parser.*;
import lang.visitor.SemanticVisitor;
import lang.ast.*;
import lang.lexer.LangScanner;
import lang.ast.Program;
import lang.parser.LangParser;
import lang.parser.LangParserAdaptor;
import lang.parser.ParseAdaptor;
import lang.visitor.InterpreterVisitor;
import lang.visitor.JasminVisitor;

public class LangCompiler {
    // Recupera o nome base (sem extensão) de um arquivo.
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Lang compiler - 2024");
            System.out.println("Arthur Vieira & Rafael Vargas");
            System.out.println("Use make run option=<ação> file=<caminho do arquivo/diretório>");
            System.out.println("Ações: ");

            System.out.println("run_parser <file_path>: Executa um teste sintático unitário");
            System.out.println("run_semantic <file_path>: Executa um teste sintático e semântico unitário");
            System.out.println("run <file_path>: Executa a análise sintática e semântica, e depois interpreta");

            System.out.println("runall_parser : Executa uma bateria de testes sintáticos");
            System.out.println("runall_semantic : Executa uma bateria de testes semânticos");
            System.out.println("runall : Executa uma bateria de testes no interpretador");
        }
        try {
            ParseAdaptor langParser = new LangParserAdaptor();
            if (args.length <= 0)
                return;

            if (args[0].equals("run_parser")) {
                System.out.println("Executando teste sintático:");
                TestParser tp = new TestParser(langParser);
                tp.runSingleTest(args[1]);
                return;
            }
            if (args[0].equals("run_semantic")) {
                FileReader file = new FileReader(args[1]);

                if (file != null) {
                    System.out.println("\nProcessing file: " + args[1]);
                    LangScanner lexicResult = new LangScanner(file);
                    LangParser parser = new LangParser();
                    Program ast = (Program) parser.parse(lexicResult);

                    // Verificação semântica
                    SemanticVisitor semanticVisitor = new SemanticVisitor();
                    ast.accept(semanticVisitor);

                    if (!semanticVisitor.getSemanticErrors().isEmpty()) {
                        System.err.println("Erros semânticos encontrados no arquivo: " + args[1]);
                        for (String error : semanticVisitor.getSemanticErrors()) {
                            System.err.println(error);
                        }
                    } else {
                        System.out.println("Arquivo correto sem erros semânticos: " + args[1]);
                    }
                } else {
                    System.out.println("Nenhum arquivo encontrado.");
                }
                return;
            }
            if (args[0].equals("run")) {
                FileReader file = new FileReader(args[1]);

                if (file != null) {
                    System.out.println("\nProcessing file: " + args[1]);
                    LangScanner lexicResult = new LangScanner(file);
                    LangParser parser = new LangParser();
                    Program ast = (Program) parser.parse(lexicResult);

                    // Verificação semântica
                    SemanticVisitor semanticVisitor = new SemanticVisitor();
                    ast.accept(semanticVisitor);

                    if (!semanticVisitor.getSemanticErrors().isEmpty()) {
                        System.err.println("Erros semânticos encontrados no arquivo: " + args[1]);
                        for (String error : semanticVisitor.getSemanticErrors()) {
                            System.err.println(error);
                        }
                    } else {                        
                        InterpreterVisitor interpreterVisitor = new InterpreterVisitor();
                        ast.accept(interpreterVisitor);
                    }
                } else {
                    System.out.println("Nenhum arquivo encontrado.");
                }
                return;
            }
            if (args[0].equals("runall_parser")) {
                System.out.println("Executando bateria de testes sintáticos:");
                TestParser tp = new TestParser(langParser);
                tp.runOkTests(args[1]);
                return;
            }
            if (args[0].equals("runall_semantic")) {
                System.out.println("Executando bateria de testes semânticos:");
                File directory = new File(args[1]);
                File[] files = directory.listFiles((dir, name) -> name.endsWith(".lan"));

                if (files != null && files.length > 0) {
                    List<String> correctFiles = new ArrayList<>();
                    List<String> errorFiles = new ArrayList<>();

                    for (File file : files) {
                        System.out.println("\nProcessing file: " + file.getName());
                        try (FileReader fileReader = new FileReader(file)) {
                            LangScanner lexicResult = new LangScanner(fileReader);
                            LangParser parser = new LangParser();
                            Program ast = (Program) parser.parse(lexicResult);

                            // Verificação semântica
                            SemanticVisitor semanticVisitor = new SemanticVisitor();
                            ast.accept(semanticVisitor);

                            if (!semanticVisitor.getSemanticErrors().isEmpty()) {
                                errorFiles.add(file.getName());
                                System.err.println("Erros semânticos encontrados no arquivo: " + file.getName());
                                for (String error : semanticVisitor.getSemanticErrors()) {
                                    System.err.println(error);
                                }
                            } else {
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
                    System.out.println("Nenhum arquivo .lan encontrado na pasta " + directory.getName() + ".");
                }
                return;
            }

            if (args[0].equals("runall")) {
                System.out.println("Executando bateria de testes no interpretador:");
                File directory = new File(args[1]);
                File[] files = directory.listFiles((dir, name) -> name.endsWith(".lan"));

                if (files != null && files.length > 0) {
                    for (File file : files) {
                        System.out.println("\nProcessing file: " + file.getName());
                        try (FileReader fileReader = new FileReader(file)) {
                            LangScanner lexicResult = new LangScanner(fileReader);
                            LangParser parser = new LangParser();
                            Program ast = (Program) parser.parse(lexicResult);

                            // Verificação semântica
                            SemanticVisitor semanticVisitor = new SemanticVisitor();
                            ast.accept(semanticVisitor);

                            if (!semanticVisitor.getSemanticErrors().isEmpty()) {
                                System.err.println("Erros semânticos encontrados no arquivo: " + file.getName());
                                for (String error : semanticVisitor.getSemanticErrors()) {
                                    System.err.println(error);
                                }
                            } else {
                                InterpreterVisitor interpreterVisitor = new InterpreterVisitor();
                                ast.accept(interpreterVisitor);
                            }
                        } catch (Exception e) {
                            System.err.println("Erro inesperado ao processar o arquivo " + file.getName() + ": " + e);
                        }
                    }
                } else {
                    System.out.println("Nenhum arquivo .lan encontrado na pasta " + directory.getName() + ".");
                }
                return;
            }

            if (args[0].equals("s")) {
                System.out.println("Executando bateria de testes no interpretador:");
                File directory = new File(args[1]);
                File[] files = directory.listFiles((dir, name) -> name.endsWith(".lan"));
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        System.out.println("\nProcessing file: " + file.getName());
                        try (FileReader fileReader = new FileReader(file)) {
                            LangScanner lexicResult = new LangScanner(fileReader);
                            LangParser parser = new LangParser();
                            Program ast = (Program) parser.parse(lexicResult);
                            // Verificação semântica
                            SemanticVisitor semanticVisitor = new SemanticVisitor();
                            ast.accept(semanticVisitor);

                            if (!semanticVisitor.getSemanticErrors().isEmpty()) {
                                System.err.println("Erros semânticos encontrados no arquivo: " + file.getName());
                                for (String error : semanticVisitor.getSemanticErrors()) {
                                    System.err.println(error);
                                }
                            } else {
                                // InterpreterVisitor interpreterVisitor = new InterpreterVisitor();
                                // ast.accept(interpreterVisitor);
                                JasminVisitor jasminVisitor = new JasminVisitor();
                                ast.accept(jasminVisitor);
                            }
                        } catch (Exception e) {
                            System.err.println("Erro inesperado ao processar o arquivo " + file.getName() + ": " + e);
                        }
                    }
                } else {
                    System.out.println("Nenhum arquivo .lan encontrado na pasta " + directory.getName() + ".");
                }
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
