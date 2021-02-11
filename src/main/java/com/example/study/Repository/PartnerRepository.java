package com.example.study.Repository;

import com.example.study.model.Entity.Category;
import com.example.study.model.Entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {

    List<Partner> findByCategory(Category category);
}
