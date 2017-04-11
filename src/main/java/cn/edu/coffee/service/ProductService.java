package cn.edu.coffee.service;

import cn.edu.coffee.model.Product;

import java.util.List;

/**
 * Created by HeYong on 2017/3/14.
 */
public interface ProductService {
	
	public Product finProductById(int productId);

    public List<Product> findProduct();

    public List<Product> findProductByType(int productType);

    public int addProduct(Product product);

    public void editProductName(int productId,String newName);

    //修改原价
    public void editOriginalPrice(int productId,int newPrice);

    //开启促销
    public void onSale(int productId,int originalPrice,int discountPrice);

    //结束促销
    public void cancelSale(int productId);

    //修改图片
    public void editProductPhoto(int productId,String photoPath);
}
