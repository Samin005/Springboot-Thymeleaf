package io.samin005.springbootthymeleaf.model;

public class Pokemon {
    private int dexNo;
    private String name;
    private String description;

    public int getDexNo() {
        return dexNo;
    }

    public void setDexNo(int dexNo) {
        this.dexNo = dexNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
