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
        String contents = "[19:02:32] [Server thread/INFO] [Server Utilities]: Deleting 1 old backups\n" +
                "[19:02:32] [Server thread/INFO] [Server Utilities]: Deleted old backup: ./backups/2026-02-08-16-51-32.zip\n" +
                "[19:08:23] [Thread-19/INFO] [Server Utilities]: Backing up 1513 files...\n" +
                "[19:08:23] [Thread-19/INFO] [Server Utilities]: [0 | 0%]: /home/kahonkey/Downloads/GHTN-Server/./World/DIM55/data/villages.dat\n" +
                "[19:08:28] [Thread-19/INFO] [Server Utilities]: [1127 | 74.48%]: /home/kahonkey/Downloads/GHTN-Server/./World/region/r.4.-2.mca\n" +
                "[19:08:31] [Thread-19/INFO] [Server Utilities]: [1512 | 99.93%]: /home/kahonkey/Downloads/GHTN-Server/visualprospecting/server/World_707618ba-8aa5-447f-aa65-c666ffb589a2/DIM0.dat\n" +
                "[19:08:31] [Thread-19/INFO] [Server Utilities]: Backup done in 00:08 seconds (243.9MB)!\n" +
                "[19:08:31] [Thread-19/INFO] [Server Utilities]: Created /home/kahonkey/Downloads/GHTN-Server/./backups/2026-02-12-19-08-23.zip from /home/kahonkey/Downloads/GHTN-Server/./World\n" +
                "[19:08:31] [Server thread/INFO] [Server Utilities]: Deleting 1 old backups\n" +
                "[19:08:31] [Server thread/INFO] [Server Utilities]: Deleted old backup: ./backups/2026-02-08-16-57-32.zip\n";
        Window window = new Window(terminal.getWidth()/2, terminal.getHeight(), contents);
        while (true) {
            terminal.puts(InfoCmp.Capability.clear_screen);
            terminal.puts(InfoCmp.Capability.cursor_home);
            terminal.flush();
            window.setWidth(terminal.getWidth()/2);
            window.setHeight(terminal.getHeight());
            String[] newContents = window.getContents();
            String printedContents = "";
            for (String line : newContents) {
                printedContents += line + "\n";
            }
            writer.print(printedContents.strip());
            writer.flush();
            Thread.sleep(67);
        }
    }


}
