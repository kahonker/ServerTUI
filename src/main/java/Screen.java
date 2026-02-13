public class Screen {
    int width;
    int height;
    Window logs;
    Window stats;

    public void _update(){
        logs._update();
    }

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        logs = new Window(width/2, height){
            @Override
            public void _update(){
                this.contents = "";
            }
        };
    }

    public String[] getScreen(){
        String [] log = logs.getContents();
        return log;
    }
}
