package org.catkin.supermassage.controller;

import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.entity.model.PageResult;
import org.catkin.supermassage.service.OrderService;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.catkin.supermassage.utils.RESTurl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author Catkin_nice
 *
 */
@RestController
@RequestMapping(RESTurl.order)
public class OrderController {
	
	@Autowired
	private OrderService os;
	
	@RequestMapping(method = RequestMethod.PUT)
	public Order addOrder(@RequestBody Order order) throws Exception {
		os.addOrder(order);
		return order;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public PageResult getOrder(@RequestBody Order order) throws Exception {
		return os.getOrder(order);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Order getOrderById(@PathVariable String id) throws Exception {
		return os.getOrderById(id);
	}
	
	@RequestMapping(value = "/pay", method = RequestMethod.PUT)
	public String payOrder(@RequestBody Order order) throws Exception {
		os.payOrder(order);
		return ConstantsStatus.SUCCESS;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delOrderById(@PathVariable String id) throws Exception {
		os.delOrderById(id);
		return ConstantsStatus.SUCCESS;
	}
	
}
