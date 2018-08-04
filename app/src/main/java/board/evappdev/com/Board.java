package board.evappdev.com;

import android.graphics.drawable.Drawable;

public class Board {

    private String id;
    private String title;
    private String region;
    private int price;
    private String description;
    private Drawable drawable;
    private String pathImage;

    public Board() {

    }
    public Board(String title, String region, int price, String description, String pathImage) {
       this.title = title;
       this.region = region;
       this.price = price;
       this.description = description;
       this.pathImage = pathImage;
    }
    public Board(String title, String region, int price, String description, Drawable drawable) {
        this.title = title;
        this.region = region;
        this.price = price;
        this.description = description;
        this.drawable = drawable;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
