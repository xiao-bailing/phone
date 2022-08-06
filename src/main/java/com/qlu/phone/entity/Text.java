package com.qlu.phone.entity;

public class Text {

    private String value;
    public void setValue(String value) {
         this.value = value;
     }
     public String getValue() {
         return value;
     }

    @Override
    public String toString() {
        return "Text{" +
                "value='" + value + '\'' +
                '}';
    }
}