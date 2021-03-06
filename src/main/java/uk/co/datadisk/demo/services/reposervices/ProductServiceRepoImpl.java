package uk.co.datadisk.demo.services.reposervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uk.co.datadisk.demo.domain.Product;
import uk.co.datadisk.demo.repositories.ProductRepository;
import uk.co.datadisk.demo.services.ProductService;
import uk.co.datadisk.demo.services.SendTextMessageService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa", "jpadao"})
public class ProductServiceRepoImpl implements ProductService {

    private ProductRepository productRepository;
    private SendTextMessageService sendTextMessageService;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setSendTextMessageService(SendTextMessageService sendTextMessageService) {
        this.sendTextMessageService = sendTextMessageService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<?> listAll() {
        System.out.println("Using new JPA Repo - listAll");

        sendTextMessageService.sendTextMessage("Listing Products");

        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add); //fun with Java 8
        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getById(Integer id) {
        sendTextMessageService.sendTextMessage("Requested Product ID: " + id);

        return productRepository.findOne(id);
    }

    @Override
    @Cacheable("product")
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    public void delete(Integer id) {
        productRepository.delete(id);
    }

}
