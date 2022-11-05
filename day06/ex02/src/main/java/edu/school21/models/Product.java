package edu.school21.models;

public class Product {
    private Long id;
    private String item_name;
    private Long price;

    public Product(Long id, String name, Long price) {
        this.id = id;
        this.item_name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Long getPrice() {
        return price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
