package com.nt.service;

import java.util.List;

import com.nt.entity.Product;

public interface IProductService 
{
	public List<Product> getAllProducts(); 
	
	public Product getProduct(int prodid);
	
	public void   addProduct(Product prod);
	
	public void updateProduct(Product prod) throws Exception;
	
	public String deleteProduct(int prodid);
}
