package com.example.study.repository;

import com.example.study.Repository.CategoryRepository;
import com.example.study.StudyApplicationTests;
import com.example.study.model.Entity.Category;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends StudyApplicationTests {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create(){
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);
        category.setTitle(title);
        category.setType(type);

        Category newCategory = categoryRepository.save(category);
        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(),type);
        Assert.assertEquals(newCategory.getTitle(),title);
    }
    @Test
    public void read(){

        Optional<Category> optionalCategory = categoryRepository.findByType("COMPUTER");

        //select * from category where type = 'COMPUTER'
        // Repository 에 override

        optionalCategory.ifPresent(c ->{

            Assert.assertEquals(c.getType() , "COMPUTER");
            System.out.println(c.getId());
            System.out.println(c.getTitle());
            System.out.println(c.getType());
        });
    }
}
