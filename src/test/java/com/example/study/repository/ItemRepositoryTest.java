package com.example.study.repository;

import com.example.study.Repository.ItemRepository;
import com.example.study.StudyApplicationTests;
import com.example.study.model.Entity.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {
    @Autowired
    private ItemRepository itemrepository;

    @Test
    public void create(){
        Item item = new Item();
        item.setStatus("UNREGISTERED");
        item.setName("삼성 컴퓨터");
        item.setTitle("삼성 노트북 A100");
        item.setContent("2019년형 노트북입니다");
        item.setPrice(BigDecimal.valueOf(900000));
        item.setBrandName("삼성");
        item.setRegisteredAt(LocalDateTime.now());
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01 ");
        //item.setPartnerId(1L);



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
