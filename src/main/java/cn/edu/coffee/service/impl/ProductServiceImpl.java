package cn.edu.coffee.service.impl;

import cn.edu.coffee.mapper.ProductMapper;
import cn.edu.coffee.model.Product;
import cn.edu.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findProduct() {

        return productMapper.findAllProduct();
    }

    @Override
    public List<Product> findProductByType(int productType) {
        return productMapper.getProductByType(productType);
    }

    @Override
    public int addProduct(Product product) {
        int productId = productMapper.insterProduct(product);
        return productId;
    }

    @Override
    public void editProductName(int productId, String newName) {
        Product product = new Product();
        product.setProductId(productId)
                .setProductName(newName);

        productMapper.updateProduct(product);
    }

    @Override
    public void editOriginalPrice(int productId, int newPrice) {
        Product product = new Product();
        product.setProductId(productId)
                .setProductOriginalPrice(newPrice);

        productMapper.updateOriginalPrice(product);
    }

    @Override
    public void onSale(int productId, int originalPrice, int discountPrice) {
        Product product = new Product();
        product.setProductId(productId)
                .setProductOriginalPrice(originalPrice)
                .setProductDiscountPrice(discountPrice)
                .setOnSale(true);

        productMapper.updateOnSale(product);
    }

    @Override
    public void cancelSale(int productId) {
        productMapper.cancelOnSale(productId);
    }

    @Override
    public void editProductPhoto(int productId, String photoPath) {
        Product product = new Product();
        product.setProductId(productId)
                .setProductPhoto(photoPath);

        productMapper.updatePhoto(product);
    }

	@Override
	public Product finProductById(int productId) {
		
		return productMapper.findProductById(productId);
	}


}
