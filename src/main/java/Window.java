public class Window {
    private int width;
    private int height;
    private String contents;
    private String prevContents;
    private boolean dirty;


    public Window(int width, int height){
        this.width = width;
        this.height = height;
        this.contents = "";
        this.prevContents = "";
        this.dirty = false;
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

    public boolean isDirty() {
        return dirty;
    }

    public void makeClean(){
        dirty = false;
    }
}
