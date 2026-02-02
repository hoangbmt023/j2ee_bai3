package com.example.bai2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.bai2.models.Category;
import com.example.bai2.models.Product;
import com.example.bai2.services.CategoryService;
import com.example.bai2.services.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public String Index(Model model) {
        model.addAttribute("listproduct", productService.getAll());
        return "product/products";
    }

    @GetMapping("/create")
    public String Create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "product/create";
    }

    @PostMapping("/create")
    public String Create(@Valid Product newProduct, BindingResult result,
            @RequestParam("category.id") int categoryId,
            @RequestParam("imageProduct") MultipartFile imageProduct, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", newProduct);
            model.addAttribute("categories", categoryService.getAll());
            return "product/create";
        }
        productService.updateImage(newProduct, imageProduct); // Xử lý ảnh
        Category selectedCategory = categoryService.get(categoryId);
        newProduct.setCategory(selectedCategory);
        productService.add(newProduct);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Product product = productService.get(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());
        return "product/edit";
    }

    // EDIT - SUBMIT
    @PostMapping("/edit")
    public String edit(
            @Valid Product product,
            BindingResult result,
            @RequestParam("category.id") int categoryId,
            @RequestParam("imageProduct") MultipartFile imageProduct,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAll());
            return "product/edit";
        }

        Category category = categoryService.get(categoryId);
        product.setCategory(category);

        productService.updateImage(product, imageProduct);
        productService.update(product);

        return "redirect:/products";
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}