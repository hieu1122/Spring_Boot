package com.example.WEB.Entity;

import lombok.Data;

import java.io.Serializable;
@Data
public class ProductSupplierId implements Serializable {
    private int productId;
    private int supplierId;
}
