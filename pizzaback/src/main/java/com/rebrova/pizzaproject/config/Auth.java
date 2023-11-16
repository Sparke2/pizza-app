//package com.rebrova.pizzaproject.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class Auth {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.
//                authorizeHttpRequests().anyRequest().permitAll();
//
//
////                .requestMatchers("api/provider/").hasRole("Admin")
////                .requestMatchers("api/invoice/invoices").hasRole("Admin")
////                .requestMatchers("api/invoice/newInvoices").hasRole("Stocker")
////                .requestMatchers("api/invoice/newInvoicesGoodsById").hasRole("Stocker")
////                .requestMatchers("/api/product/productDetails").hasRole("Admin")
////                .requestMatchers("/api/product/providerById").hasRole("Admin")
////                .requestMatchers("/api/product/providerById").hasRole("Admin")
////                .requestMatchers("/api/receipt/receipts").hasRole("Admin")
////                .requestMatchers("/api/product/products2").hasRole( "Stocker")
//////                .requestMatchers("/api/product/products2").hasRole( "Stocker")
////                .requestMatchers("/index").hasAnyRole("Admin", "Stocker")
////                .requestMatchers("/api/product/products").hasAnyRole("Admin", "Stocker")
////                .requestMatchers("/api/claim/").hasAnyRole("Admin", "Stocker")
////                .requestMatchers("/api/info/").hasAnyRole("Admin", "Stocker")
////                .requestMatchers("/").permitAll()
////                .requestMatchers("/acceptedPage").permitAll()
//
////                .and().formLogin();
//
//        return http.build();
//    }
//
////                .anyRequest().authenticated()
////                .httpBasic(withDefaults());
//
//
//    //    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.authorizeHttpRequests().requestMatchers("/").hasRole("userS").and().formLogin();
////        return http.build();
////    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails userStocker = User.withDefaultPasswordEncoder()
//                .username("Stocker")
//                .password("123")
//                .roles("Stocker")
//                .build();
//
//        UserDetails userAdmin = User.withDefaultPasswordEncoder()
//                .username("Admin")
//                .password("12345")
//                .roles("Admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(userStocker, userAdmin);
//    }
//
//}
