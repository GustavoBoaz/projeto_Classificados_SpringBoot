package com.classificado.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.classificado.app.utils.CategoryInformation;

@Entity
@Table(name = "tb_informations")
public class InformationModel {

    // System generated
    private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long idInformation;

    // User generated
    private String title;
    private String description;
    private CategoryInformation category;

    
}
