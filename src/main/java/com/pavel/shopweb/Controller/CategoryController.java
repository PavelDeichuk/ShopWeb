package com.pavel.shopweb.Controller;

import com.pavel.shopweb.Dto.CategoryDto;
import com.pavel.shopweb.Entity.CategoryEntity;
import com.pavel.shopweb.Service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    private static final String CATEGORY_ID = "/{category_id}";

    private static final String ADD_PRODUCT_CATEGORY = "/product/{product_id}/category/{category_id}";

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CategoryDto> GetAllCategory(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size){
        return categoryService.GetAllCategories(page, size);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto CreateCategory(@RequestBody CategoryEntity categoryEntity){
        return categoryService.CreateCategory(categoryEntity);
    }

    @RequestMapping(value = ADD_PRODUCT_CATEGORY, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean AddProductToCategory(@PathVariable Long product_id,
                                            @PathVariable Long category_id){
        return categoryService.AddProductToCategory(product_id, category_id);
    }

    @RequestMapping(value = CATEGORY_ID, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto EditCategory(@PathVariable Long category_id,
                                    @RequestBody CategoryEntity categoryEntity){
        return categoryService.EditCategory(category_id, categoryEntity);
    }

    @RequestMapping(value = CATEGORY_ID, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CategoryDto DeleteCategory(@PathVariable Long category_id){
        return categoryService.DeleteCategory(category_id);
    }
}
