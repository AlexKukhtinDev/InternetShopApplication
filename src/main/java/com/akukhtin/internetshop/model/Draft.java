package com.akukhtin.internetshop.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "draft")
public class Draft {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String nameProduct;
    @Column
    private String ean;
    @Column
    private double priceProduct;
    @Column
    private double totalAmount;

    public Draft() {
    }
    public Draft( String nameProduct,String ean, double priceProduct, double totalAmount) {
        this.nameProduct = nameProduct;
        this.ean = ean;
        this.priceProduct = priceProduct;
        this.totalAmount = totalAmount;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Draft{" +
                "id=" + id +
                ", nameProduct='" + nameProduct + '\'' +
                ", ean='" + ean + '\'' +
                ", priceProduct=" + priceProduct +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
