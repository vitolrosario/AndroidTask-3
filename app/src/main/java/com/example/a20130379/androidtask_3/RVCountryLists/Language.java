package com.example.a20130379.androidtask_3.RVCountryLists;

import java.io.Serializable;

public class Language implements Serializable {

    private String name;
    private String nativeName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

}