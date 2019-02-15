package com.geekbrains.entities;

public class Item {
    private String Id;
    private String Title;
    private float Cost;

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public float getCost() {
        return Cost;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setCost(float cost) {
        this.Cost = cost;
    }


}
