package app.controller;


import app.model.Product;
import app.service.ProductService;
import app.util.FunctionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ServiceController {

//    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // @Autowired
    private ProductService productService;

    @Autowired
    public ServiceController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/Products", method = RequestMethod.GET)
    public @ResponseBody List<Product> getProducts() {
        return productService.findAll();
    }

    @RequestMapping(value = "/Product", method = RequestMethod.GET)
    public @ResponseBody Optional<Product> getProductById(@RequestParam(name = "id", defaultValue = "1")Long id) {
        return productService.findById(id);
    }

    @RequestMapping(value = "/Product", method = RequestMethod.POST)
    public @ResponseBody Product storeProduct(@RequestBody Product newProduct) {
        return productService.save(newProduct);
    }

    @RequestMapping(value = "/Product", method = RequestMethod.PATCH)
    public @ResponseBody Product modifyProduct(@RequestBody Product product) {
        Product toModify = productService.findById(product.getId()).orElse(new Product());

        if(toModify.getName() == null) {
            return toModify;
        } else {
            toModify.setName(product.getName());
            return productService.save(toModify);
        }
    }

    @RequestMapping(value = "/Product", method = RequestMethod.DELETE)
    public @ResponseBody String deleteProductById(@RequestParam(name = "id")Long id) {
        productService.deleteById(id);

        return "{\"message\":\"Successfully deleted\"}";
    }

    @RequestMapping(value = "/Product/Biggest", method = RequestMethod.GET)
    public @ResponseBody Product getBiggestProduct() {
        List<Product> products = productService.findAll();

//        long idBiggestProduct = Integer.MIN_VALUE;

//        idBiggestProduct = FunctionRepo.findBiggestProduct.apply(products);
//        int weightBiggestProduct = Integer.MIN_VALUE;
//
//        for(Product product : products) {
//            if( weightBiggestProduct < product.getWeight() ) {
//                weightBiggestProduct= product.getWeight();
//                idBiggestProduct = product.getId();
//            }
//        }

//        return productService.findById(idBiggestProduct).orElse(new Product());
        return productService.findById(FunctionRepo.findBiggestProduct.apply(products)).orElse(new Product());

    }

    @RequestMapping(value = "/Products/TotalWeight", method = RequestMethod.GET)
    public @ResponseBody String getTotalWeightOfProducts() {
        List<Product> products = productService.findAll();
//        int totalWeight = 0;
//
//        for(Product product : products) {
//            totalWeight += product.getWeight();
//        }

//        return "{\"totalWeight\":\"" + totalWeight + "\"}";
        return "{\"totalWeight\":\"" + FunctionRepo.findTotalWeight.apply(products) + "\"}";
    }

    @RequestMapping(value = "/Products/checkAllComplete", method = RequestMethod.GET)
    public @ResponseBody boolean checkProductDescriptionsComplete() {
        List<Product> products = productService.findAll();
        boolean allComplete = true;

        for(Product product : products) {
            allComplete &= product.getId() != 0 && product.getName() != null && product.getWeight() != 0;
        }

        return allComplete;
    }

}
