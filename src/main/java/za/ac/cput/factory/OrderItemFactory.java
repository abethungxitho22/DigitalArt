package za.ac.cput.factory;
/*
OrderItemFactory.java
OrderItem Factory class
Author: Thimna Gogwana 222213973
Date: 25 May 2025
*/

import za.ac.cput.domain.OrderItem;
import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;
import org.springframework.stereotype.Component;

@Component
public class OrderItemFactory {


    public OrderItem create(Long orderID, Product product, int quantity) {
        if (!Helper.isValidOrderID(orderID) ||
                !Helper.isValidProductAssociation(product) ||
                !Helper.isValidQuantity(quantity)) {
            throw new IllegalArgumentException("Invalid parameters for creating OrderItem");
        }

        double unitPrice = product.getPrice(); // take price directly from product

        return new OrderItem.Builder()
                .setProduct(product)
                .setQuantity(quantity)
                .setUnitPrice(unitPrice)
                .calculateSubTotal()
                .build();
    }


    public OrderItem copy(OrderItem orderItem) {
        return new OrderItem.Builder()
                .setOrderID(orderItem.getOrderID())
                .setProduct(orderItem.getProduct())
                .setQuantity(orderItem.getQuantity())
                .setUnitPrice(orderItem.getUnitPrice())
                .calculateSubTotal()
                .build();
    }
}

