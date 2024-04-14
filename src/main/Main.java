import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException {
        Scanner scanner = new Scanner(new FileReader(args[0]));
        Token token = scanner.nextToken();

        while (token != null) {
            System.out.println(token.toString());
            token = scanner.nextToken();
        }
    }
}