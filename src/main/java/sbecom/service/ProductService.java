package sbecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import sbecom.model.Product;
import sbecom.repo.ProductRepo;

public class ProductService {
    
    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id) {
        return productRepo.findById(id).orElse(null); // Return null if product not found, this returns an Optional type which is unwrapped to get the Product object or null; Optional type - A container object which may or may not contain a non-null value
    }

    public void deleteProduct(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> search(String keyword) {
        return productRepo.search(keyword);
    }

    public Product saveOrUpProduct(Product product, MultipartFile file) throws Exception {
        if (file != null && !file.isEmpty()) {
            product.setImageName(file.getOriginalFilename());
            product.setImageType(file.getContentType());
            product.setImageData(file.getBytes());
        }
        return productRepo.save(product);
    }
}
