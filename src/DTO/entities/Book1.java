/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.entities;

import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class Book1 {
    private String ISBN;
    private String tenSach;
    private int storeNum;
    private String publisher;
    private String author;
    private long Cost;
    private String img;

    public Book1(String ISBN, String tenSach, int storeNum, String publisher, String author, long Cost, String img) {
        this.ISBN = ISBN;
        this.tenSach = tenSach;
        this.storeNum = storeNum;
        this.publisher = publisher;
        this.author = author;
        this.Cost = Cost;
        this.img = img;
    }

    public Book1() {
        
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(int storeNum) {
        this.storeNum = storeNum;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getCost() {
        return Cost;
    }

    public void setCost(long Cost) {
        this.Cost = Cost;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    
    
}
