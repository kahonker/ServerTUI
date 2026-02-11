import org.jline.reader.*;
import org.jline.terminal.*;

import java.io.IOException;
import java.io.Reader;

public class HelloJLine {
    public static void main(String[] args) throws IOException {
        Terminal terminal = TerminalBuilder.builder().build();

        // Get the terminal reader
        Reader reader = terminal.reader();

        terminal.writer().println("Press any key (or 'q' to quit):");
//        terminal.writer().flush();

        // Read characters
        int c;
        while ((c = reader.read()) != 'q') {
            terminal.writer().printf("You pressed: %c (ASCII: %d)%n", (char) c, c);
            terminal.writer().flush();
        }

        terminal.close();
    }
}