package io.samin005.springbootthymeleaf.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Pokemon {
    @Min(1)
    private int dexNo;
    @NotEmpty
    private String name;
    @NotEmpty
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
