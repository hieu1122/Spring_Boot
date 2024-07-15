package com.example.WEB.Service;

import com.example.WEB.Entity.*;
import com.example.WEB.Repository.Order_ItemRepository;
import com.example.WEB.Repository.OrdersRepository;
import com.example.WEB.Repository.ProductRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private Order_ItemRepository orderItemRepository;
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

    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable int id, @PathVariable int order_id, @PathVariable int product_id, @RequestBody OrderItem theOrderItem) {
        Optional<OrderItem> result=orderItemRepository.findById(id);
        if(result.isPresent()) {
            OrderItem orderItem = result.get();
            Optional<Orders> ordersId = ordersRepository.findById(order_id);
            Optional<Product> productId = productRepository.findById(product_id);
            if (ordersId.isPresent() && productId.isPresent()) {
                Orders orders = ordersId.get();
                Product product = productId.get();

                orderItem.setOrders(orders);
                orderItem.setProduct(product);
                orderItem.setPrice(theOrderItem.getPrice());
                orderItem.setQuantity(theOrderItem.getQuantity());

                OrderItem addOrderItem = orderItemRepository.save(orderItem);
                return ResponseEntity.ok(addOrderItem);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
