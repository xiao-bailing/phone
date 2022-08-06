package com.qlu.phone.entity;


public class Attrs {

    private int type;
    private String name;
    private Text text;
    public void setType(int type) {
         this.type = type;
     }
     public int getType() {
         return type;
     }

    @Override
    public String toString() {
        return "Attrs{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", text=" + text +
                '}';
    }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setText(Text text) {
         this.text = text;
     }
     public Text getText() {
         return text;
     }

}