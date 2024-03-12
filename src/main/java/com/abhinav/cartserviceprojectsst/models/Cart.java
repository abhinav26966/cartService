package com.abhinav.cartserviceprojectsst.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Cart {
    int id;
    int userId;
    LocalDate date;
    List<Product> products;
}