package com.classificado.app.repositories;

import com.classificado.app.models.AdvertsmentModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * AdvertsmentRepository responsible for CRUD operations on AdvertsmentModel
 * 
 * @author Boaz
 * @since 26/01/2022
 * @version 1.0
 */
@Repository
public interface AdvertsmentRepository extends JpaRepository<AdvertsmentModel, Long> {
    
}
