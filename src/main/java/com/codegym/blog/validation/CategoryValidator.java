package com.codegym.blog.validation;

import com.codegym.blog.model.Category;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CategoryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Category.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Category category = (Category) target;

        String name = category.getName();
        if(name.trim().length() == 0) {
            errors.rejectValue("name", "name.empty");
        }

    }
}
