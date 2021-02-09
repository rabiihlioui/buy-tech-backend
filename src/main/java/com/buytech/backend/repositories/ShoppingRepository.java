package com.buytech.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buytech.backend.models.Shopping;
import com.buytech.backend.models.ShoppingId;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, ShoppingId>{

}
