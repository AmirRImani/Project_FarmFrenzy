package entry;

import java.io.IOException;

public class Option extends EnterProcess {
    private boolean isFullScreen;

    public Option() throws IOException {
    }

    public void volumeChanger(){

    }

    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
    }
    public boolean isFullScreen() { return isFullScreen; }
}
