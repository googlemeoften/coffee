package cn.edu.coffee.controller;

import cn.edu.coffee.model.Product;
import cn.edu.coffee.service.ProductService;
import cn.edu.coffee.utils.DataFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping("/indexProdect")
    public List<List<Product>> indexCoffee() {
        List<List<Product>> products = new ArrayList<List<Product>>();
        products.add(productService.findProductByType(0));
        products.add(productService.findProductByType(1));
        products.add(productService.findProductByType(2));
        return products;
    }

    @ResponseBody
    @RequestMapping("/coffee")
    public List<Product> coffee() {
        System.out.printf("get.....");
        return productService.findProductByType(0);
    }

    @ResponseBody
    @RequestMapping("/drink")
    public List<Product> drink() {
        return productService.findProductByType(1);
    }

    @ResponseBody
    @RequestMapping("/food")
    public List<Product> food() {
        return productService.findProductByType(2);
    }

    public Product findProductById(int productId) {
        return null;
    }

    @RequestMapping("/publishProduct")
    public String publishProduct(Product product,
                               @RequestParam("imageFiles") MultipartFile imageFile,
                               HttpSession session) {

        String filePath = session.getServletContext().getRealPath("image");
        String originalName = imageFile.getOriginalFilename();
        int index = originalName.lastIndexOf(".");
        String fileName = DataFormatUtils.getRandomStr() + originalName.substring(index);
        System.out.println(filePath + "===================================");
        File targetFile = new File(filePath, fileName);

        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        try {
            imageFile.transferTo(targetFile);
        } catch (IOException e) {

            System.out.println("发布失败");
        }
        int originalPrice = product.getProductOriginalPrice();
        int discountPrice = product.getProductDiscountPrice();
        if (originalPrice < discountPrice) {
            System.out.println("liubi....");
        }

        product.setProductPhoto("image/" + fileName);
        //System.out.println(product);
        productService.addProduct(product);
        return "redirect:../admin/publish_Product.html";
    }

    @RequestMapping("/editProduct")
    public void editProduct(Product product) {
        System.out.println(product);
    }

    public void deleteProduct(int productId) {

    }
}
