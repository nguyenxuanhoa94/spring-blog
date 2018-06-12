package com.codegym.blog.controller;

import com.codegym.blog.model.Category;
import com.codegym.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public ModelAndView showAllCategory() {
        ModelAndView modelAndView;

        Iterable<Category> categories = categoryService.findAll();
        if (categories != null) {
            modelAndView = new ModelAndView("/category/list");
            modelAndView.addObject("categories", categories);
        } else {
            modelAndView = new ModelAndView("/404");
        }
        return modelAndView;
    }

    @GetMapping("/create-category")
    public ModelAndView showFormCreateCategory() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("/create-category")
    public ModelAndView createCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        modelAndView.addObject("message", "Create category successful");
        return modelAndView;
    }

    @GetMapping("/update-category/{id}")
    public ModelAndView showFormUpdateCategory(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id);
        ModelAndView modelAndView;

        if(category != null) {
            modelAndView  = new ModelAndView("/category/update");
            modelAndView.addObject("category", category);
        } else {
            modelAndView = new ModelAndView("/404");
        }

        return modelAndView;
    }

    @PostMapping("/update-category")
    public ModelAndView updateCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        ModelAndView modelAndView = new ModelAndView("/category/update");
        modelAndView.addObject("message", "Update category successfull");
        return modelAndView;
    }

    @GetMapping("/delete-category/{id}")
    public ModelAndView showFormDeleteCategory(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id);
        ModelAndView modelAndView;

        if(category != null) {
            modelAndView  = new ModelAndView("/category/delete");
            modelAndView.addObject("category", category);
        } else {
            modelAndView = new ModelAndView("/404");
        }

        return modelAndView;
    }

    @PostMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        Category category = categoryService.findById(id);
        if(category != null) {
            categoryService.remove(id);
            return "redirect:/categories";
        } else {
            return  "redirect:/404";
        }
    }

}
