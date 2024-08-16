package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.OrderItemRepository;
import com.example.WEB.Repository.OrdersRepository;
import com.example.WEB.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
//    public void updateOrderItem(Product product, List<OrderItem> orderItems){
//        Map<Integer,OrderItem> result=product.getOrderItems().stream()
//                .collect(Collectors.toMap(OrderItem::getOrderItemId, Function.identity()));
//        List<OrderItem> newOrderItem=orderItems.stream()
//                .map(orderItem -> result.merge(orderItem.getOrderItemId(),orderItem,(existing,update)->{
//                   existing.setQuantity(update.getQuantity());
//                   existing.setPrice(update.getPrice());
//                   return existing;
//                })).collect(Collectors.toList());
//        product.setOrderItems(newOrderItem);
//    }

    public ResponseEntity<OrderItem> addOrderItem(@PathVariable int order_id, @PathVariable int product_id, @RequestBody OrderItem theOrderItem) {
        Optional<Orders> ordersId=ordersRepository.findById(order_id);
        Optional<Product> productId=productRepository.findById(product_id);
        if(ordersId.isPresent() && productId.isPresent()){
            Orders orders=ordersId.get();
            Product product=productId.get();

            OrderItem orderItem=new OrderItem();
            orderItem.setOrders(orders);
            orderItem.setProduct(product);
            orderItem.setPrice(theOrderItem.getPrice());
            orderItem.setQuantity(theOrderItem.getQuantity());

            OrderItem addOrderItem=orderItemRepository.save(orderItem);
            return ResponseEntity.ok(addOrderItem);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<OrderItem> updateOrderItem(int orderId, OrderItem theOrderItem) {
        OrderItem updateOrderItem=orderItemRepository.findById(orderId)
                .map(orderItem -> {
                    orderItem.setQuantity(theOrderItem.getQuantity());
                    orderItem.setPrice(theOrderItem.getPrice());
                    return orderItemRepository.save(orderItem);
                }).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"OderItemId not found"));
        return ResponseEntity.ok(updateOrderItem);
    }
}
