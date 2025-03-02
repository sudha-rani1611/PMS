package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Product;
import com.nt.service.IProductService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController 
{
	@Autowired
    private IProductService service;
	
	@GetMapping("/products")
	 public ResponseEntity<List<Product>> getProducts() 
	{
		return new ResponseEntity<List<Product>>(service.getAllProducts(),HttpStatus.OK);
	}
	 
	 @GetMapping("/products/{prodid}") 
	  public ResponseEntity<Product>getProduct(@PathVariable int prodid)
	  {
		  Product product =service.getProduct(prodid);
		  if(product!= null) {
			  return new ResponseEntity<Product>(product,HttpStatus.OK);
			}
		  else {
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
			  }
		  }

	@PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestBody Product prod)
    {
		service.addProduct(prod);
		return new ResponseEntity<>(HttpStatus.OK);
    }
	
	  @PutMapping("/products")
	  public ResponseEntity<?> updateProduct(@RequestBody Product prod)
	  {
		  try 
		  {
		   service.updateProduct(prod);
		   return new ResponseEntity<>(HttpStatus.OK); 
		  }
		  catch(Exception e)
		  {
			e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	  }
   
	 @DeleteMapping("/products/{prodid}") 
	  public ResponseEntity<?>deleteProduct(@PathVariable int prodid)
	 {
		 String str=service.deleteProduct(prodid);
		 if(str!=null)
		 {
			 return new ResponseEntity<>(HttpStatus.OK); 
		}
		 else 
		 {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	 }
	 
}

