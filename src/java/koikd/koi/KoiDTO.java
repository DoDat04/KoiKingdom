/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.koi;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class KoiDTO implements Serializable {
    private int koiID;
    private String koiName;
    private String koiTypeName;
    private int koiTypeID;
    private int age; // Tuổi có thể là kiểu int
    private double length; // Chiều dài có thể là kiểu double
    private double weight; // Cân nặng có thể là kiểu double
    private double price; // Giá có thể là kiểu double
    private String image; // Đường dẫn đến hình ảnh có thể là kiểu String

    public KoiDTO() {
    }   

    public KoiDTO(int koiID, String koiName, String koiTypeName, int age, double length, double weight, double price, String image) {
        this.koiID = koiID;
        this.koiName = koiName;
        this.koiTypeName = koiTypeName;
        this.age = age;
        this.length = length;
        this.weight = weight;
        this.price = price;
        this.image = image;
    }
    
    public KoiDTO(int koiID, String koiName, int koiTypeID, int age, double length, double weight, double price, String image) {
        this.koiID = koiID;
        this.koiName = koiName;
        this.koiTypeID = koiTypeID;
        this.age = age;
        this.length = length;
        this.weight = weight;
        this.price = price;
        this.image = image;
    }
    
    public KoiDTO(int koiID, String koiName, int age, double length, double weight, double price) {
        this.koiID = koiID;
        this.koiName = koiName;
        this.age = age;
        this.length = length;
        this.weight = weight;
        this.price = price;
    }

    KoiDTO(int koiID, String koiName, String image) {
        this.koiID = koiID;
        this.koiName = koiName;
        this.image = image;
    }

    public KoiDTO(String koiName) {
        this.koiName = koiName;
    }

    public String getKoiTypeName() {
        return koiTypeName;
    }

    public void setKoiTypeName(String koiTypeName) {
        this.koiTypeName = koiTypeName;
    }

    public int getKoiID() {
        return koiID;
    }

    public void setKoiID(int koiID) {
        this.koiID = koiID;
    }

    public String getKoiName() {
        return koiName;
    }

    public void setKoiName(String koiName) {
        this.koiName = koiName;
    }

    public int getKoiTypeID() {
        return koiTypeID;
    }

    public void setKoiTypeID(int koiTypeID) {
        this.koiTypeID = koiTypeID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "KoiDTO{" + "koiID=" + koiID + ", koiName=" + koiName + ", koiTypeID=" + koiTypeID + ", age=" + age + ", length=" + length + ", weight=" + weight + ", price=" + price + ", image=" + image + '}';
    }
    
}
