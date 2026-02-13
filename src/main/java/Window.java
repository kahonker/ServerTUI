public class Window {
    int width;
    int height;
    public String contents = "";

    public Window(int width, int height){
        this.width = width;
        this.height = height;
        this.contents = "[19:02:32] [Server thread/INFO] [Server Utilities]: Deleting 1 old backups\n" +
                "[19:02:32] [Server thread/INFO] [Server Utilities]: Deleted old backup: ./backups/2026-02-08-16-51-32.zip\n" +
                "[19:08:23] [Thread-19/INFO] [Server Utilities]: Backing up 1513 files...\n" +
                "[19:08:23] [Thread-19/INFO] [Server Utilities]: [0 | 0%]: /home/kahonkey/Downloads/GHTN-Server/./World/DIM55/data/villages.dat\n" +
                "[19:08:28] [Thread-19/INFO] [Server Utilities]: [1127 | 74.48%]: /home/kahonkey/Downloads/GHTN-Server/./World/region/r.4.-2.mca\n" +
                "[19:08:31] [Thread-19/INFO] [Server Utilities]: [1512 | 99.93%]: /home/kahonkey/Downloads/GHTN-Server/visualprospecting/server/World_707618ba-8aa5-447f-aa65-c666ffb589a2/DIM0.dat\n" +
                "[19:08:31] [Thread-19/INFO] [Server Utilities]: Backup done in 00:08 seconds (243.9MB)!\n" +
                "[19:08:31] [Thread-19/INFO] [Server Utilities]: Created /home/kahonkey/Downloads/GHTN-Server/./backups/2026-02-12-19-08-23.zip from /home/kahonkey/Downloads/GHTN-Server/./World\n" +
                "[19:08:31] [Server thread/INFO] [Server Utilities]: Deleting 1 old backups\n" +
                "[19:08:31] [Server thread/INFO] [Server Utilities]: Deleted old backup: ./backups/2026-02-08-16-57-32.zip\n";
    }

    public void _update(){

    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String[] getContents() {
        int contentWidth = width - 2;
        int contentHeight = height - 2;
        String[] contentsArray = contents.split("\n");
        String[] actualContents = new String[height];
        int index = 0;
        int lineIndex = 0;
        int lines = 0;

        // find the starting line and index at string for the contents based on height and width
        for (int i = contentsArray.length - 1; i >= 0; i--) {
            String line = contentsArray[i];
            if (lines + Math.ceilDiv(line.length(), contentWidth) > contentHeight) {
                lineIndex = i;
                index = line.length() - (line.length() % contentWidth);
                break;
            }
            lines += Math.ceilDiv(line.length(), contentWidth);
        }

        // Build the top border
        actualContents[0] = "+" + "-".repeat(width - 2) + "+";

        // Fill the content lines
        int currentOutputLine = 1;
        for (int i = lineIndex; i < contentsArray.length && currentOutputLine < height - 1; i++) {
            String line = contentsArray[i];
            int startIndex = (i == lineIndex) ? index : 0;

            // Wrap this line into multiple display lines if needed
            while (startIndex < line.length() && currentOutputLine < height - 1) {
                int endIndex = Math.min(startIndex + contentWidth, line.length());
                String segment = line.substring(startIndex, endIndex);

                // Pad the segment to contentWidth with spaces
                segment = String.format("%-" + contentWidth + "s", segment);

                actualContents[currentOutputLine] = "|" + segment + "|";
                currentOutputLine++;
                startIndex = endIndex;
            }
        }

        // Fill any remaining lines with empty content
        while (currentOutputLine < height - 1) {
            actualContents[currentOutputLine] = "|" + " ".repeat(contentWidth) + "|";
            currentOutputLine++;
        }

        // Build the bottom border
        actualContents[height - 1] = "+" + "-".repeat(width - 2) + "+";

        return actualContents;
    }
}
