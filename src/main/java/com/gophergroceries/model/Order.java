package com.gophergroceries.model;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.gophergroceries.model.entities.OrderLinesEntity;
import com.gophergroceries.model.entities.OrdersEntity;
import com.gophergroceries.model.entities.ProductEntity;
import com.gophergroceries.model.repository.OrdersRepository;
import com.gophergroceries.model.repository.ProductsRepository;

/**
 * This class represents an Order on the website. As such it contains an
 * OrderEntity, which is the database access object for the order. It is
 * intended that this class would have wrapper methods around the entity to
 * augment the processing of an order on the site.
 * 
 * @author camroe
 *
 */
@Service
@Configurable
@Scope("prototype")
public class Order {
	private static final Logger logger = LoggerFactory.getLogger(Order.class);

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private OrdersRepository ordersRepository;

	private OrdersEntity orderEntity;

	public Order() {
		// Default
	}

	public Order(OrdersEntity oe) {
		this.orderEntity = oe;
	}

	public OrdersEntity getOrderEntity() {
		return orderEntity;
	}

	public void setOrderEntity(OrdersEntity orderEntity) {
		this.orderEntity = orderEntity;
	}

	public boolean add(AddToCartForm atcf) {
		ProductEntity productEntity = productsRepository.findOne(new Integer(atcf.getId()));
		Set<OrderLinesEntity> orderlines = orderEntity.getOrderlines();
		boolean found = false;
		for (OrderLinesEntity ole : orderlines) {
			if (productEntity.equals(ole.getProduct())) {
				// add cart quantity to existing
				found = true;
				Integer quantity = ole.getQuantity();
				quantity = quantity + new Integer(atcf.getQuantity());
				ole.setQuantity(quantity);
				ordersRepository.save(orderEntity);
				break;
			}
		}
		if (found) {
			// product matched existing one in order so we are done
			return true;
		}
		else {
			// product new to order
			OrderLinesEntity ole = new OrderLinesEntity();
			ole.setOrder(this.orderEntity);
			ole.setPrice(productEntity.getPrice());
			ole.setProduct(productEntity);
			ole.setQuantity(new Integer(atcf.getQuantity()));
			Set<OrderLinesEntity> setOfOrderlines = orderEntity.getOrderlines();
			if (setOfOrderlines.add(ole)) {
				logger.trace("New OrderlinesEntry " + ole.getId() + "  added to OrderEntity " + orderEntity.getId());
				this.orderEntity = ordersRepository.save(orderEntity);
				return true;
			}
			else {
				logger.warn("OrderlinesEntry failed to add to OrderEntity - NOT saving OrdersEntity ");
				return false;
			}
		}
	}

}
