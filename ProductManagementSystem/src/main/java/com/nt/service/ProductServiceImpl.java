package com.nt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nt.entity.Product;
import com.nt.repository.IRepository;

@Service
public class ProductServiceImpl implements IProductService
{
	@Autowired
    private IRepository repo;

	@Override
	public List<Product> getAllProducts() 
	{
		return repo.findAll();
	}

	@Override
	public Product getProduct(int prodid) 
	{
			return repo.findById(prodid).orElse(null);	
	}

	@Override
	public void  addProduct(Product prod) 
	{
		repo.save(prod);
	}
	
	@Override
	public String deleteProduct(int prodid)
	{
		if(repo.existsById(prodid))
		{
			repo.deleteById(prodid);
			return "Deleted Successfully";
		}
		else
		{
			return null; 
		}
	}

	@Override
	public void updateProduct(Product prod) throws Exception
	{
		if(repo.existsById(prod.getProdid()))
		{
			repo.save(prod);
		}
		else
		{
			throw new IllegalArgumentException("Invalid Product ");
		}
	}
}

