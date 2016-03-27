package com.example.user.cantoneselearningplatform;

import android.graphics.Bitmap;

/**
 * Created by user on 27/3/16.
 */
public class ImageItem {
    private Bitmap image;

    public ImageItem(Bitmap image) {
        super();
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
