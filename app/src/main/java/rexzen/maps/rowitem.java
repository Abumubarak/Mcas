package rexzen.maps;

/**
 * Created by harishananth on 26/11/16.
 */

public class rowitem {
    private int imageId;
    private String title;

    public rowitem(int imageId, String title) {
        this.imageId = imageId;
        this.title = title;

    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title + "\n";
    }
}

