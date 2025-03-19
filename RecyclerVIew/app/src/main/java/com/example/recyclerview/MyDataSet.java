package com.example.recyclerview;

public class MyDataSet {
    public String text, colorText;
    public int image;

    public MyDataSet(String text, int image, String colorText) {
        this.text = text;
        this.image = image;
        this.colorText = colorText;
    }

    // Getter for MyDataSet
    public int getImage() {return image;}
    public String getText() {return text;}
    public String getColorText() {return colorText;}
}
