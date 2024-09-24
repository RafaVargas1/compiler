package lang.parser;

import java.io.*;

// Adaptador para classe de parser. a Função parseFile deve retornar null caso o parser resulte em erro. 

public class TestParser {
    private ParseAdaptor adp;
    private String okSrcs = "tests/sintaxe/certo/";
    private File f;

    public TestParser(ParseAdaptor adp) {
        this.adp = adp;
        f = new File(okSrcs);
        // runOkTests();
    }

    private String filler(int n) {
        String s = "";
        for (int i = 0; i < n; i++) {
            s += " ";
        }
        return s;
    }

    public void runSingleTest(String path) {
        if(adp.parseFile(path) != null){
            System.out.println("[  OK  ]");
        } else {
            System.out.println("[FALHOU]");
        };
    }

    public void runOkTests(String path) {
        File inst[];
        int flips, flops;
        flips = 0;
        flops = 0;
        this.f = new File(path);
        try {
            if (f.isDirectory()) {
                String pth;
                inst = f.listFiles();
                for (File s : inst) {
                    pth = s.getPath();
                    System.out.print("Testando " + pth + filler(50 - pth.length()) + "[");
                    if (adp.parseFile(pth) != null) {
                        System.out.println("  OK  ]");
                        flips++;
                    } else {
                        System.out.println("FALHOU]");
                        flops++;
                    }
                }
                System.out.println("Total de acertos: " + flips);
                System.out.println("Total de erros: " + flops);
            } else {
                System.out.println("O caminho " + f.getPath() + " não é um diretório ou não existe.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
