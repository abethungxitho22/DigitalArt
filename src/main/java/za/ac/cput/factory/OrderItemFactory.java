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

    public static OrderItem createOrderItem(Long orderID, Product product, int quantity, double unitPrice) {
        if (!Helper.isValidOrderID(orderID) || !Helper.isValidProductAssociation(product) ||
                !Helper.isValidQuantity(quantity) || !Helper.isValidUnitPrice(unitPrice)) {
            throw new IllegalArgumentException("Invalid parameters for creating OrderItem");
        }

        return new OrderItem.Builder()
                .setProduct(product)
                .setQuantity(quantity)
                .setUnitPrice(unitPrice)
                .calculateSubTotal()
                .build();
    }

}

