package com.classificado.app.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.classificado.app.utils.CategoryServices;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class of persistence ServiceModel
 * 
 * @author Boaz
 * @since 26/01/2022
 * @version 1.0
 */
@Entity
@Table(name = "tb_services")
public class ServiceModel {
    
    // System generated
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idService;
    
    // User generated
    private String title;
    private String description;
    private String phone;
    private String email;
    private @Enumerated(EnumType.STRING) CategoryServices category;

    // System relations
    @ManyToOne
    @JoinColumn(name = "fk_advertsment")
    @JsonIgnoreProperties({"services"})
    private AdvertsmentModel advertsment;

    // Getters and Setters
    public Long getIdService() {
        return this.idService;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CategoryServices getCategory() {
        return this.category;
    }

    public void setCategory(CategoryServices category) {
        this.category = category;
    }

    public AdvertsmentModel getAdvertsment() {
        return this.advertsment;
    }

    public void setAdvertsment(AdvertsmentModel advertsment) {
        this.advertsment = advertsment;
    }

}
