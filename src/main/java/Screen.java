public class Screen {
    int width;
    int height;
    Window[] windows;
    private boolean dirty = false;

    public void _update(){
        windows[0]._update();
        for (Window window : windows) if (window != null && window.isDirty()){
            dirty = true;
            break;
        }
    }

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        windows = new Window[3];
        windows[0] = new Logs(width/2, height);
    }

    public String getScreen(){
        String screen = "";
        String[] logsContents = windows[0].getContents();

        for (String str : logsContents){
            screen += str + "\n";
        }

        return screen.strip();
    }

    public void setHeight(int height) {
        this.height = height;
        windows[0].setHeight(height);
    }

    public void setWidth(int width) {
        this.width = width;
        windows[0].setWidth(width/2);
    }

    public boolean isDirty() {
        return dirty;
    }

    public void makeClean(){
        dirty = false;
        for (Window window : windows) if (window != null) window.makeClean();
    }
}
