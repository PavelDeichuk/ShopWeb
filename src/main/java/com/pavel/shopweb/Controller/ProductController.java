package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.ProductDto;
import com.pavel.shopweb.Entity.ProductEntity;
import com.pavel.shopweb.Service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    private static final String PRODUCT_ID = "/{product_id}";

    private static final String PRODUCT_IMAGE = "/{product_id}/image";

    private static final String CATEGORY_NAME = "/category";

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> GetAllProduct(@RequestParam(value = "page",defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "10") int size){
        return productService.GetAllProduct(page, size);
    }

    @RequestMapping(value = PRODUCT_ID, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto GetProductById(@PathVariable Long product_id){
        return productService.GetProductById(product_id);
    }

    @RequestMapping(value = CATEGORY_NAME, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductDto> GetCategoryByName(@RequestParam(value = "name", required = true)String category){
        return productService.GetProductByCategoryName(category);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto CreateProduct(@RequestBody ProductEntity productEntity){
        return productService.CreateProduct(productEntity);
    }

    @RequestMapping(value = PRODUCT_IMAGE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto CreateImageProduct(@PathVariable Long product_id,
                                         @RequestParam(value = "file", required = false)MultipartFile multipartFile) throws IOException {
        return productService.CreateImageProduct(product_id,multipartFile);
    }

    @RequestMapping(value = PRODUCT_IMAGE, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto EditImageProduct(@PathVariable Long product_id,
                                       @RequestParam(value = "file", required = false)MultipartFile multipartFile) throws IOException {
        return productService.EditImageProduct(product_id, multipartFile);
    }

    @RequestMapping(value = PRODUCT_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto EditProduct(@PathVariable Long product_id,
                                  @RequestBody ProductEntity productEntity){
        return productService.EditProduct(product_id, productEntity);
    }

    @RequestMapping(value = PRODUCT_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDto DeleteProduct(@PathVariable Long product_id){
        return productService.DeleteProduct(product_id);
    }
}
