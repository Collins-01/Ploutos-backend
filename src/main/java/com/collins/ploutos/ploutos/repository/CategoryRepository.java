package com.collins.ploutos.ploutos.repository;

import com.collins.ploutos.ploutos.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {
}
