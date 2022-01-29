package com.classificado.app.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.classificado.app.utils.CategoryAdverts;
import com.classificado.app.utils.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Class of persistence AdvertsmentModel
 * 
 * @author Boaz
 * @since 26/01/2022
 * @version 1.0
 */
@Entity
@Table(name = "tb_adverts")
public class AdvertsmentModel {

    // System generated
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idAdvertsment;
    private Long days = 0L;
    private @Enumerated(EnumType.STRING) Status status = Status.ACTIVE;
    private @JsonFormat(pattern = "dd/MM/yyyy") LocalDate initialDate = LocalDate.now();

    // User generated
    private String city;
    private @JsonFormat(pattern = "dd/MM/yyyy") LocalDate finalDate;
    private @Enumerated(EnumType.STRING) CategoryAdverts category;

    // System relations
    @ManyToOne
    @JoinColumn(name = "fk_creator")
    @JsonIgnoreProperties({"adverts"})
    private UserModel creator;

    @OneToMany(mappedBy = "advertsment", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"advertsment"})
    private List<ServiceModel> services = new ArrayList<>();

    // Getters and Setters
    public List<ServiceModel> getServices() {
        return this.services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

    public UserModel getCreator() {
        return this.creator;
    }

    public void setCreator(UserModel creator) {
        this.creator = creator;
    }

    public Long getIdAdvertsment() {
        return this.idAdvertsment;
    }

    public void setIdAdvertsment(Long idAdvertsment) {
        this.idAdvertsment = idAdvertsment;
    }

    public Long getDays() {
        this.days = ChronoUnit.DAYS.between(this.initialDate, this.finalDate);
        return this.days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public Status getStatus() {
        LocalDate now = LocalDate.now();
        if (this.finalDate.isBefore(now)) {
            this.status = Status.INACTIVE;
        }
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getInitialDate() {
        return this.initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinalDate() {
        return this.finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public CategoryAdverts getCategory() {
        return this.category;
    }

    public void setCategory(CategoryAdverts category) {
        this.category = category;
    }

}
