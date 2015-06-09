package org.catkin.supermassage.controller;

import org.catkin.supermassage.entity.Order;
import org.catkin.supermassage.service.OrderService;
import org.catkin.supermassage.utils.ConstantsStatus;
import org.catkin.supermassage.utils.Json;
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
	public Order addOrder(@RequestBody String json) throws Exception {
		Order order = Json.parse(json, Order.class);
		os.addOrder(order);
		return order;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Order getOrderById(@PathVariable String id) throws Exception {
		return os.getOrderById(id);
	}
	
	@RequestMapping(value = "/pay", method = RequestMethod.PUT)
	public String payOrder(@RequestBody String json) throws Exception {
		Order order = Json.parse(json, Order.class);
		os.payOrder(order);
		return ConstantsStatus.SUCCESS;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delOrderById(@PathVariable String id) throws Exception {
		os.delOrderById(id);
		return ConstantsStatus.SUCCESS;
	}
	
}
