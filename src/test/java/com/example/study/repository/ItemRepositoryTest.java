package com.example.study.repository;

import com.example.study.Repository.ItemRepository;
import com.example.study.StudyApplicationTests;
import com.example.study.model.Entity.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {
    @Autowired
    private ItemRepository itemrepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setName("노트북");
        item.setPrice(100000);
        item.setContent("삼성 노트북");

        Item newItem = itemrepository.save(item);
        Assert.assertNotNull(newItem);

    }

    @Test
    public void read(){

        Long id = 1L;
        Optional<Item> item = itemrepository.findById(id);
        Assert.assertTrue(item.isPresent());
    }
}
