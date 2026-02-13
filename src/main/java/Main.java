import org.jline.terminal.Size;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Terminal terminal = TerminalBuilder.builder().build();
        terminal.puts(InfoCmp.Capability.cursor_invisible);
        terminal.flush();
        PrintWriter writer = terminal.writer();
        Screen screen = new Screen(terminal.getWidth(), terminal.getHeight());
        Size prevSize = new Size();
        while (true) {
            screen._update();
            if (terminal.getSize() != prevSize || screen.isDirty()) {
                refresh(screen, terminal, writer);
            }
            prevSize = terminal.getSize();
            Thread.sleep(67);
        }
    }

    public static void refresh(Screen screen, Terminal terminal, PrintWriter writer){
        terminal.puts(InfoCmp.Capability.clear_screen);
        terminal.puts(InfoCmp.Capability.cursor_home);
        terminal.flush();
        screen.setWidth(terminal.getWidth());
        screen.setHeight(terminal.getHeight());
        writer.print(screen.getScreen());
        writer.flush();
        screen.makeClean();
    }

}
