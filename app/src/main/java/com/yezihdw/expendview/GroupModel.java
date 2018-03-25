package com.yezihdw.expendview;

import java.util.List;

/**
 * Created by yeziwen on 2018/3/25.
 */

public class GroupModel {

    private String name;
    private int value;
    private String textValue;
    private int type;

    private List<ChildModel> childModels;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    public List<ChildModel> getChildModels() {
        return childModels;
    }

    public void setChildModels(List<ChildModel> childModels) {
        this.childModels = childModels;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
