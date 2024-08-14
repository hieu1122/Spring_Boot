package com.example.WEB.Security;

import jakarta.servlet.http.HttpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class Security {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT * FROM users WHERE username=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT * FROM authorities WHERE username=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.GET, "/api/category").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/category/{categoryId}").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/category/{categoryId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/category/{categoryId}/updateProduct/{productId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/category/{categoryId}/updateReview/{productId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/category/{categoryId}/updateSupplier/{productId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/category/{categoryId}/updateOrderItem/{productId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/category").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/category/{categoryId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/category/{categoryId}/deleteProduct/{productId}").hasRole("ADMIN")





                .requestMatchers(HttpMethod.GET, "/api/customer").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/customer/**").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/customer/{id}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/customer").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/customer/addCustomer").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/customer/addOrder/{customerId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/customer/addPayment/{customerId}/{orderId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/customer/addShipment/{customerId}/{orderId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/customer/addOrderItem/{customerId}/{orderId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/customer/addReview/{customerId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/customer/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/customer/{id}/deleteOrder/{orderId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/customer/{id}/deleteReview/{orderId}").hasRole("ADMIN")





                .requestMatchers(HttpMethod.GET, "/api/orderitem").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/orderitem/**").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/orderitem/{id}/{order_id}/{product_id}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/orderitem/{order_id}/{product_id}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/orderitem/**").hasRole("ADMIN")


                .requestMatchers(HttpMethod.GET, "/api/order").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/order/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/order/getPaymentId/{id}").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/order/{orderId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/order").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/order").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/order/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/order/addPayment/{orderId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/order/addShipment/{orderId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/order/addOrderItem/{orderId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/order/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/order/{id}/deleteOrderItem/{orderitemId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/order/{id}/deletePayment/{paymentId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/order/{id}/deleteShipment/{shipmentId}").hasRole("ADMIN")


                .requestMatchers(HttpMethod.GET, "/api/payment").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/payment/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/payment/getOrderId/{id}").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/payment/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/payment").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/payment/{paymentId}/{orderId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/payment/{paymentId}").hasRole("ADMIN")


                .requestMatchers(HttpMethod.GET, "/api/product").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/product/{productId}").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/product/getCategoryId/{productId}").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/product").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/product/{productId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/product/updateSupplier/{productId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/product/updateOrderItem/{productId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/product/updateReview/{productId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/product/{productId}/updateReview/{reviewId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/product").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/product/addReview/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/product/addSupplier/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/product/addOrderItem/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/product").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/product/{productId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/product/{productId}/deleteReview/{reviewId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/product/{productId}/deleteSupplier/{supplierId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/product/{productId}/deleteOrderItem/{orderitemId}").hasRole("ADMIN")



                .requestMatchers(HttpMethod.GET, "/api/productsupplier").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/productsupplier/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/productsupplier/{productId}/{supplierId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/productsupplier/{productId}/{supplierId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/productsupplier").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/productsupplier/{productId}/{supplierId}").hasRole("ADMIN")


                .requestMatchers(HttpMethod.GET, "/api/review").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/review/{reviewId}").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/review/{reviewId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/review").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/review/{reviewId}").hasRole("ADMIN")


                .requestMatchers(HttpMethod.GET, "/api/shipment").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/shipment/**").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/shipment/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/shipment").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/shipment/**").hasRole("ADMIN")


                .requestMatchers(HttpMethod.GET, "/api/supplier").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/supplier/**").hasRole("USER")
                .requestMatchers(HttpMethod.PUT, "/api/supplier/{id}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/supplier").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE, "/api/supplier/**").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
