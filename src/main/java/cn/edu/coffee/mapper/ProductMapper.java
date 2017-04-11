package cn.edu.coffee.mapper;

import cn.edu.coffee.model.Product;

import java.util.List;

/**
 * Created by HeYong on 2017/3/13.
 */
public interface ProductMapper {

    public List<Product> getProductByType(int productType);

    public Product findProductById(int productId);

    public List<Product> findAllProduct();

    public int insterProduct(Product product);

    public void updateOriginalPrice(Product product);

    //开始促销
    public void updateOnSale(Product product);

    public void updatePhoto(Product product);

    public void updateProduct(Product product);

    //取消促销
    public void cancelOnSale(int productId);

    public void deleteProduct(int productId);


}
