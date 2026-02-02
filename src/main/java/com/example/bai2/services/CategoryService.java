package com.example.bai2.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bai2.models.Category;

@Service
public class CategoryService {

    private List<Category> categories = new ArrayList<>();

    public CategoryService() {
        // Dữ liệu mẫu theo đề
        categories.add(new Category(1, "Điện thoại"));
        categories.add(new Category(2, "Laptop"));
    }

    // Lấy tất cả danh mục (đổ dropdown)
    public List<Category> getAll() {
        return categories;
    }

    // Lấy danh mục theo id
    public Category get(int id) {
        return categories.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
