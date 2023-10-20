package com.stock;
import java.time.LocalDate;
public class Products {
    private Integer id;
    private String name;
    private Double price;
    private Integer qty;
    private LocalDate timeDate;
    public Products(){}



    public void setId(Integer id){this.id = id;}
    public Integer getId(){return id;}
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public void setPrice(Double price){this.price = price;}
    public Double getPrice(){return price;}
    public void setQty(Integer qty){this.qty = qty;}
    public Integer getQty(){return qty;}
    public void setTimeDate(LocalDate timeDate){this.timeDate = timeDate;}
    public LocalDate getTimeDate(){return timeDate;}
    public Products(Integer id, String name, Double price, Integer qty, LocalDate timeDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.timeDate = timeDate;
    }

}
