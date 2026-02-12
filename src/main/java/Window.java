public class Window {
    int width;
    int height;
    String contents;

    public void _update(){

    }

    public String[] getContents(){
        int actualWidth = width - 2;
        int actualHeight = height - 2;
        String[] contentsArray = contents.split("\n");
        int index;
        int usedUpLines = 0;

        for (int i = contentsArray.length - 1; i >= 0; i--){
            String line = contentsArray[i];

        }
    }

}
