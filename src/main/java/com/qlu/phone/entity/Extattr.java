
package com.qlu.phone.entity;
import java.util.List;

public class Extattr {
    @Override
    public String toString() {
        return "Extattr{" +
                "attrs=" + attrs +
                '}';
    }

    private List<Attrs> attrs;
    public void setAttrs(List<Attrs> attrs) {
         this.attrs = attrs;
     }
    public List<Attrs> getAttrs() {
         return attrs;
     }

}