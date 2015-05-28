package org.catkin.supermassage.entity;

/**
 * 
 * @author Catkin_nice
 *
 */
public class Order {
	private Long id;			//订单ID
	private Long storeId;		//商铺ID
	private Long userId;		//用户ID
	
	private float totalAppPrice;	//APP订单总价
	private float totalStorePrice;	//商铺订单总价
	private String payId;			//支付订单号（支付宝号）
	
	private boolean payType;		//支付类型（0：到店支付，1：在线支付）
	private boolean payStatus;		//支付状态（0：未支付，1：已支付）
	
	public Order() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public float getTotalAppPrice() {
		return totalAppPrice;
	}

	public void setTotalAppPrice(float totalAppPrice) {
		this.totalAppPrice = totalAppPrice;
	}

	public float getTotalStorePrice() {
		return totalStorePrice;
	}

	public void setTotalStorePrice(float totalStorePrice) {
		this.totalStorePrice = totalStorePrice;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public boolean isPayType() {
		return payType;
	}

	public void setPayType(boolean payType) {
		this.payType = payType;
	}

	public boolean isPayStatus() {
		return payStatus;
	}

	public void setPayStatus(boolean payStatus) {
		this.payStatus = payStatus;
	}
	
}
