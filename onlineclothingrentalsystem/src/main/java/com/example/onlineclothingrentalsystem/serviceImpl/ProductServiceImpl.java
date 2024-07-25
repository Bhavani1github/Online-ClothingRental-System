package com.example.onlineclothingrentalsystem.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.onlineclothingrentalsystem.entity.Product;
import com.example.onlineclothingrentalsystem.entity.ProductPaging;
import com.example.onlineclothingrentalsystem.exception.ResourseNotFoundException;
import com.example.onlineclothingrentalsystem.repository.ProductRepository;
import com.example.onlineclothingrentalsystem.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourseNotFoundException("Product", "Id", productId));
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Product", "Id", id));
        existingProduct.setPname(product.getPname());
        existingProduct.setpDescription(product.getpDescription());
        existingProduct.setMrpPrice(product.getMrpPrice());
        // existingProduct.setCategory(product.getCategory());
        existingProduct.setpQuantity(product.getpQuantity());
        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProductById(long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Product", "Id", id));
        productRepository.delete(existingProduct);
    }
    
    @Override
	public ProductPaging getAllPageProduct(int pageNo, int pageSize) {
   	 Pageable paging = PageRequest.of(pageNo, pageSize);
     Page<Product> pageResult = productRepository.findAll(paging);
     ProductPaging pr = new ProductPaging();
     pr.setTotalProduct(pageResult.getTotalElements());
     System.out.println(">>>>>"+ pageResult.getTotalPages());
     if(pageResult.hasContent()) {
         pr.setProduct(pageResult.getContent());
     } else {
          pr.setProduct(getAllProduct());
     }
     return pr;
	
		
	}
}

































//package com.example.onlineclothingrentalsystem.serviceImpl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//import com.example.onlineclothingrentalsystem.entity.ProductCategory;
//import com.example.onlineclothingrentalsystem.entity.Product;
//import com.example.onlineclothingrentalsystem.entity.DressPaging;
//import com.example.onlineclothingrentalsystem.repository.ProductRepository;
//import com.example.onlineclothingrentalsystem.service.ProductService;
//import com.example.onlineclothingrentalsystem.exception.*;
//
//public class ProductServiceImpl implements ProductService{
//	@Autowired
//	private ProductRepository dressRepository;
//
//	@Override
//	public Product addDress(Product dress) {
//		System.out.println("Dress added Succesfully "+dress);
//			dress.setDressname(dress.getDressname());
//			//dress.setQuantity(dress.getQuantity());
//			dress.setMrpPrice(dress.getMrpPrice());
//			//dress.setauthorName(dress.getauthorName());
//			dress.setDescription(dress.getDescription());
//			return dressRepository.save(dress);
//		}
//
//
//	@Override
//	public List<Product> getAllDresses() {			
//			return dressRepository.findAll();
//		}	
//	@Override
//	public Product getDressByDressId(long dressId) {
//		return dressRepository.findById(dressId).orElseThrow(()->new ResourseNotFoundException("dress","Id",dressId));
//	}
//
//	
//
//	@Override
//	public Product updateDress(Product dress, long dressId) {
//		Product existingDress = dressRepository.findById(dressId).orElseThrow(()->new ResourseNotFoundException("dress","dressId",dressId));
//		existingDress.setDressname(dress.getDressname());
//		existingDress.setMrpPrice(dress.getMrpPrice());
//		existingDress.setImage(dress.getImage());
//		existingDress.setDescription(dress.getDescription());
//		//existingBook.setauthorName(dress.getauthorName());
//		//existingDress.setQuantity(dress.getQuantity());
//
//		dressRepository.save(existingDress);	
//		return existingDress;		
//	}
//
//	@Override
//	public void deleteDress(long dressId) {
//		dressRepository.findById(dressId).orElseThrow(()->new ResourseNotFoundException("dress","Id",dressId));
//		dressRepository.deleteById(dressId);	
//		
//	}
//
//	@Override
//	public List<Product> findByCategory(ProductCategory category) {
//		return dressRepository.findByCategory(category);
//	}
//
//	@Override
//	public DressPaging findByCategory(ProductCategory category, Integer pageNo, Integer pageSize) {
//		Pageable paging = PageRequest.of(pageNo, pageSize);
//		Page<Product> pageResult = dressRepository.findByCategory(category, paging);
//		DressPaging pr = new DressPaging();
//		pr.setTotalDress(pageResult.getTotalElements());
//		if(pageResult.hasContent()) {
//            pr.setDress(pageResult.getContent());
//        } else {
//        	 pr.setDress(new ArrayList<Product>());
//        }
//		return pr;
//	}
//
//	@Override
//	public DressPaging getAllDress(Integer pageNo, Integer pageSize) {
//		Pageable paging = PageRequest.of(pageNo, pageSize);
//		Page<Product> pageResult = dressRepository.findAll(paging);
//		DressPaging pr = new DressPaging();
//		pr.setTotalDress(pageResult.getTotalElements());
//		System.out.println(">>>>>"+ pageResult.getTotalPages());
//		if(pageResult.hasContent()) {
//            pr.setDress(pageResult.getContent());
//        } else {
//        	 pr.setDress(new ArrayList<Product>());
//        }
//		return pr;
//	}
//
//	@Override
//	public List<Product> findByMrpPrice(double mrpPrice) {
//		return dressRepository.findByMrpPrice(mrpPrice);
//	}
//	
//	@Override
//	public DressPaging findByDressname(String keyword, Integer pageNo, Integer pageSize) {
//		Pageable paging = PageRequest.of(pageNo, pageSize);
//		Page<Product> pageResult = dressRepository.findByDressnameContains(keyword, paging);
//		DressPaging pr = new DressPaging();
//		pr.setTotalDress(pageResult.getTotalElements());
//		if(pageResult.hasContent()) {
//            pr.setDress(pageResult.getContent());
//        } else {
//        	 pr.setDress(new ArrayList<Product>());
//        }
//		return pr;
//	}
//	
//
//}
