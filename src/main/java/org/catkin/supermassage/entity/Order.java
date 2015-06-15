package org.catkin.supermassage.entity;

import org.catkin.supermassage.entity.model.QueryParam;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Catkin_nice
 *
 */
@JsonInclude(Include.NON_NULL)
public class Order extends QueryParam {
	private String id;				//订单ID
	private Long storeId;			//商铺ID
	private Long userId;			//用户ID
	private Packages packages;		//商品对象
	
	private String payId;			//支付订单号（支付宝号）
	private Integer payType;		//支付类型（0：到店支付，1：在线支付） {@link ConstantsStatus}
	private Integer useStatus;		//使用状态（0：未支付，1：未使用，2：已预约，3：预约过期，4：已使用） {@link ConstantsStatus}
	private Integer deleted;		//删除状态（0：正常，1：用户删除） {@link ConstantsStatus}
	
	private String storeName;		//店铺名称
	private Consume consume;		//消费详情
	
	public Order() {
	}

	public Order(String code, Long storeId) {
		this.id = code;
		this.storeId = storeId;
	}
	
	public Order(String id, Integer deleted) {
		this.id = id;
		this.deleted = deleted;
	}

	public Order(String id, String payId, Integer payType, Integer useStatus) {
		this.id = id;
		this.payId = payId;
		this.payType = payType;
		this.useStatus = useStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Packages getPackages() {
		return packages;
	}

	public void setPackages(Packages packages) {
		this.packages = packages;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Consume getConsume() {
		return consume;
	}

	public void setConsume(Consume consume) {
		this.consume = consume;
	}
	
}
