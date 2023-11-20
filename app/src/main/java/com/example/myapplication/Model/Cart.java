package com.example.myapplication.Model;

public class Cart  extends Product{
    private Boolean isChecked;
    private int Quantity;

    public Cart(int id, String _id, String name, int price, String imageUrl, int Quantity, Boolean isChecked) {
        super(id, _id, name, price, imageUrl);
        this.isChecked = isChecked;
        this.Quantity = Quantity;
    }


    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
