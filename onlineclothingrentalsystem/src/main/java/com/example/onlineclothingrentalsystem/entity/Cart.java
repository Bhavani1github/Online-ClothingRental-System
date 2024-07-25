package com.example.onlineclothingrentalsystem.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart_table")
@SequenceGenerator(name = "generator3", sequenceName = "gen3", initialValue = 1500)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator3")
    @Column(name = "cart_id")
    private long cartId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "mrp_price", nullable = false)
    private double mrpPrice;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product product;

//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "user_id")
//    private User user;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(double mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}



//package com.example.onlineclothingrentalsystem.entity;
//
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
//
//@Entity
//	@Table(name = "cart_table")
//	@SequenceGenerator(name = "generator3", sequenceName = "gen3", initialValue = 1500)
//public class Cart {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator3")
//	@Column(name = "cart_id")
//	private long cartId;
//
//
//	
//	@Column(name = "quantity")
//	private long quantity;
//
//	@Column(name = "mrp_price", nullable = false)
//	private double mrpPrice;
//
//
//	
//	@ManyToOne( cascade=CascadeType.MERGE)
//	@JoinColumn(name="dress_id")
//	//@JsonIgnore
//    private Product dress;
//	
//	@ManyToOne( cascade=CascadeType.MERGE)
//	@JoinColumn(name="user_id")
//    private User user;
//
//	public long getCartId() {
//		return cartId;
//	}
//
//	public void setCartId(long cartId) {
//		this.cartId = cartId;
//	}
//
//	public long getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(long quantity) {
//		this.quantity = quantity;
//	}
//
//	public double getMrpPrice() {
//		return mrpPrice;
//	}
//
//	public void setMrpPrice(double mrpPrice) {
//		this.mrpPrice = mrpPrice;
//	}
//
//	public Product getDress() {
//		return dress;
//	}
//
//	public void setDress(Product dress) {
//		this.dress = dress;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//	
//	
//}
