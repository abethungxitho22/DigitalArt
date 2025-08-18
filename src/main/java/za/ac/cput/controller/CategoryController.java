package za.ac.cput.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Category;
import za.ac.cput.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;

    }

    @PostMapping("/create")
    public Category create(@RequestBody Category category) {
        return service.create(category);
    }


    @GetMapping("/read/{Id}")
    public Category read(@PathVariable Long Id) {
        return service.read(Id);
    }

    @PutMapping("/update")
    public Category update(@RequestBody Category category){
        return service.update(category);
    }

    @DeleteMapping("/delete/{categoryId}")
    public void delete(@PathVariable Long categoryId) {
        service.delete(categoryId);

    }

    @GetMapping("/getAll")
    public List<Category> getAll() {
        return service.getAll();
    }

    }

