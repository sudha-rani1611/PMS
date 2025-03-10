package com.nt.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@AllArgsConstructor
@Table(name="products_table")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer prodid;
	
	@NonNull
	private String prodname;
	
	@NonNull
	private String description;
	
	@NonNull
	private Double price;
	
	@NonNull
	private String brand;
		
	@NonNull
	@JsonFormat(shape=Shape.STRING ,pattern="dd-MM-yyyy")
	private LocalDate releasedate;
	
	@NonNull
	private Integer quantity;

	@NonNull
	private Boolean stockavailable;
		 
	public Product()
	{
	   System.out.println("Product 0-Arg Constructor");   
	}
}
