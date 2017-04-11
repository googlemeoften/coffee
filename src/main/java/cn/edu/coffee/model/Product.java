package cn.edu.coffee.model;

/**
 * Created by HeYong on 2017/3/13.
 */
public class Product {
    private int productId;
    private String productName;
    private int productOriginalPrice;//原始价格
    private int productDiscountPrice;//折扣价格
    private String productPhoto;
    private int productType;
    private boolean onSale;

    public int getProductId() {
        return productId;
    }

    public Product setProductId(int productId) {
        this.productId = productId;
        return this;
    }

    public String getProductName() {
        return productName;
    }

    public Product setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public int getProductOriginalPrice() {
        return productOriginalPrice;
    }

    public Product setProductOriginalPrice(int productOriginalPrice) {
        this.productOriginalPrice = productOriginalPrice;
        return this;
    }

    public int getProductDiscountPrice() {
        return productDiscountPrice;
    }

    public Product setProductDiscountPrice(int productDiscountPrice) {
        this.productDiscountPrice = productDiscountPrice;
        return this;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public Product setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
        return this;
    }

    public int getProductType() {
        return productType;
    }

    public Product setProductType(int productType) {
        this.productType = productType;
        return this;
    }

    public boolean isOnSale() {
        return onSale;
    }

    public Product setOnSale(boolean onSale) {
        this.onSale = onSale;
        return this;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productOriginalPrice=" + productOriginalPrice +
                ", productDiscountPrice=" + productDiscountPrice +
                ", productPhoto='" + productPhoto + '\'' +
                ", productType=" + productType +
                ", onSale=" + onSale +
                '}';
    }
}
