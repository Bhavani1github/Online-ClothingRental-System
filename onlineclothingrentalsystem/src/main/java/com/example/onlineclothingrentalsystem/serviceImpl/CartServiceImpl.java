package com.example.onlineclothingrentalsystem.serviceImpl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.onlineclothingrentalsystem.entity.Cart;
import com.example.onlineclothingrentalsystem.entity.Product;
import com.example.onlineclothingrentalsystem.entity.User;
import com.example.onlineclothingrentalsystem.exception.ResourseNotFoundException;
import com.example.onlineclothingrentalsystem.repository.CartRepository;
import com.example.onlineclothingrentalsystem.service.CartService;
import com.example.onlineclothingrentalsystem.service.ProductService;
import com.example.onlineclothingrentalsystem.service.UserService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Override
    public Cart addCart(Cart cart, long productId, long userId) {
        Product product = productService.getProductById(productId);
        User user = userService.getUserById(userId);
        List<Cart> cartList = this.getAllCarts();
        int flag = 0;
        Cart existingCart = null;
        if (cartList.size() > 0) {
            for (Cart c : cartList) {
                if (c.getUser().getId() == userId && c.getProduct().getPid() == productId) {
                    flag = 1;
                    existingCart = c;
                    break;
                }
            }
        }
        product.setpQuantity(product.getpQuantity() - cart.getQuantity());
        if (flag == 1 && existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            existingCart.setMrpPrice(product.getMrpPrice());
            existingCart.setUser(user);
            return this.updateCart(existingCart, existingCart.getCartId());
        } else {
            cart.setProduct(product);
            cart.setMrpPrice(product.getMrpPrice());
            cart.setUser(user);
            return cartRepository.save(cart);
        }
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourseNotFoundException("Cart", "Id", cartId));
    }

    @Override
    public Cart updateCart(Cart cart, long cartId) {
        Cart existingCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourseNotFoundException("Cart", "Id", cartId));
        existingCart.setQuantity(cart.getQuantity());
        existingCart.setMrpPrice(cart.getMrpPrice());
        existingCart.setCartId(cart.getCartId());
        existingCart.setProduct(cart.getProduct());
        existingCart.setUser(cart.getUser());
        return cartRepository.save(existingCart);
    }

    @Override
    public void deleteCart(long cartId) {
        Cart existingCart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourseNotFoundException("Cart", "Id", cartId));
        Product product = existingCart.getProduct();
        product.setpQuantity(product.getpQuantity() + existingCart.getQuantity()); // Adjust product quantity
        productService.updateProduct(product.getPid(), product); // Update product
        cartRepository.deleteById(cartId);
    }

    @Override
    public void deleteCartByUser(User user) {
        cartRepository.deleteCartByUser(user);
    }
}






//package com.example.onlineclothingrentalsystem.serviceImpl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.onlineclothingrentalsystem.entity.Cart;
//import com.example.onlineclothingrentalsystem.entity.Product;
//import com.example.onlineclothingrentalsystem.entity.User;
//import com.example.onlineclothingrentalsystem.exception.ResourseNotFoundException;
//import com.example.onlineclothingrentalsystem.repository.CartRepository;
//import com.example.onlineclothingrentalsystem.service.CartService;
//import com.example.onlineclothingrentalsystem.service.ProductService;
//import com.example.onlineclothingrentalsystem.service.UserService;
//
//
//
//
//
//
//@Service
//public class CartServiceImpl implements CartService {
//	
//	@Autowired
//	public CartRepository cartRepository;
//	
////	@Autowired
////	public ProductRepository productRepository;
//
//	@Autowired
//	public ProductService dressService;
//	
//	@Autowired
//	public UserService userService;
//	
//public CartServiceImpl(CartRepository cartRepository) {
//		super();
//		this.cartRepository = cartRepository;
//	}
//
//@Override
//public Cart addCart(Cart cart, long dressId, long userId) {
//	Product dress =dressService.getDressByDressId(dressId) ;
//	User user =userService.getUserById(userId) ;
//	 List<Cart> crl = this.getAllCarts();
//	 int flag = 0;
//	 Cart existingCart = null;
//	 if (crl.size() > 0) {
//		 for (int i=0;i< crl.size();i++) {
//			 Cart c = this.getCartById(crl.get(i).getCartId());
//			 if (c.getUser().getUserId() == userId && c.getDress().getPid() == dressId) {
//				 flag = 1;
//				 existingCart = c;
//			 }
//		 }
//	 }
//	// dress.setQuantity(dress.getQuantity()-cart.getQuantity());
//	 if (flag ==1 && existingCart != null) {
//		 existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
//		 existingCart.setMrpPrice(dress.getMrpPrice());
//		existingCart.setUser(user);
//		System.out.println("111111111111111111111111111111111");
//		return this.updateCart(existingCart, existingCart.getCartId());
//	 } else {
//		// cart.setBook(Dress);
//		//cart.setMrpPrice(Dress.getMrpPrice());
//		cart.setUser(user);
//		System.out.println("2222222222222222222222222222222222222222");
//		return cartRepository.save(cart);
//	 }
//}
//
//@Override
//public List<Cart> getAllCarts() {
//	return cartRepository.findAll();
//}
//
//@Override
//public Cart getCartById(long cartId) {
//	return cartRepository.findById(cartId).orElseThrow(()->new ResourseNotFoundException("Cart","Id",cartId));
//}
//
//@Override
//public Cart updateCart(Cart cart, long cartId) {
//	Cart existingCart=cartRepository.findById(cartId).orElseThrow(()->new ResourseNotFoundException("Cart","Id",cartId));
//	existingCart.setQuantity(cart.getQuantity());
//	//existingCart.setPrice(cart.getPrice());
//	existingCart.setMrpPrice(cart.getMrpPrice());
//	//existingCart.setImage(cart.getImage());
//	existingCart.setCartId(cart.getCartId());
//	existingCart.setDress(cart.getDress());
//	//existingCart.setCustomerId(cart.getCustomerId());
//	existingCart.setUser(cart.getUser());
//    cartRepository.save(existingCart);
//    
//	return existingCart;
//}
//
//@Override
//public void deleteCart(long cartId) {
//	Cart existingCart=cartRepository.findById(cartId).orElseThrow(()->new ResourseNotFoundException("Cart","Id",cartId));
//	Product dress =dressService.getDressByDressId(existingCart.getDress().getPid());
//	//dress.setQuantity(dress.getQuantity());
//	dressService.updateDress(dress, dress.getPid());
//	// cartRepository.findById(cartId).orElseThrow(()->new ResourceNotFoundException("Cart","Id",cartId));
//	cartRepository.deleteById(cartId);
//	
//	
//}
//
//@Override
//public void deleteCartByUser(User u) {
//	cartRepository.deleteCartByUser(u);
//	
//}
//
//}
