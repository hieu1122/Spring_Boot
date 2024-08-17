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
                .requestMatchers(HttpMethod.PUT, "/api/category/{categoryId}/updateProduct/{productId}/updateReview/{reviewId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/category/{categoryId}/updateProduct/{productId}/updateSupplier/{supplierId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/category/{categoryId}/updateProduct/{productId}/updateOrderItem/{orderId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/category").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/category/{categoryId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/category/{categoryId}/deleteProduct/{productId}").hasRole("ADMIN")





                .requestMatchers(HttpMethod.GET, "/api/customer").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/customer/{customerId}").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/customer/{customerId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/customer/{customerId}/updateOrder/{orderId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/customer/{customerId}/updateOrder/{orderId}/updatePayment/{paymentId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/customer/{customerId}/updateOrder/{orderId}/updateShipment/{shipmentId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/customer/{customerId}/updateOrder/{orderId}/updateOrderItem/{itemId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/customer/{customerId}/updateReview/{reviewId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/customer").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/customer/{customerId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/customer/{customerId}/deleteOrder/{orderId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/customer/{customerId}/deleteReview/{orderId}").hasRole("ADMIN")





                .requestMatchers(HttpMethod.GET, "/api/orderitem").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/orderitem/{orderId}").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/orderitem/{orderId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/orderitem").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/orderitem/{orderId}").hasRole("ADMIN")





                .requestMatchers(HttpMethod.GET, "/api/order").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/order/{orderId}").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/order/getPaymentId/{orderId}").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/order/{orderId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/order/{orderId}/updatePayment/{paymentId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/order/{orderId}/updateShipment/{shipmentId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/order/{orderId}/updateOrderItem/{itemId}").hasRole("MANAGER")


                .requestMatchers(HttpMethod.POST, "/api/order").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/order/addPayment/{orderId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/order/addShipment/{orderId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.POST, "/api/order/addOrderItem/{orderId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/order/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/order/{id}/deleteOrderItem/{orderitemId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/order/{id}/deletePayment/{paymentId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/order/{id}/deleteShipment/{shipmentId}").hasRole("ADMIN")


                .requestMatchers(HttpMethod.GET, "/api/payment").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/payment/{paymentId}").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/payment/{paymentId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/payment").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/payment/{paymentId}").hasRole("ADMIN")


                .requestMatchers(HttpMethod.GET, "/api/product").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/product/{productId}").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/product/getCategoryId/{productId}").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/product").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/product/{productId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/product/{productId}/updateReview/{reviewId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/product/{productId}/updateSupplier/{supplierId}").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/product/{productId}/updateOrderItem/{itemId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/product").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/product/{productId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/product/{productId}/deleteReview/{reviewId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/product/{productId}/deleteSupplier/{supplierId}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/product/{productId}/deleteOrderItem/{orderId}").hasRole("ADMIN")



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
                .requestMatchers(HttpMethod.GET, "/api/shipment/{shipmentId}").hasRole("USER")

                .requestMatchers(HttpMethod.PUT, "/api/shipment/{shipmentId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.POST, "/api/shipment").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/shipment/{shipmentId}").hasRole("ADMIN")





                .requestMatchers(HttpMethod.GET, "/api/supplier").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/supplier/{supplierId}").hasRole("USER")

                .requestMatchers(HttpMethod.POST, "/api/supplier").hasRole("MANAGER")

                .requestMatchers(HttpMethod.PUT, "/api/supplier/{supplierId}").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/api/supplier/{supplierId}").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
