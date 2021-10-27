//package com.testdome.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//@Configuration
//public class BookSearch {
//
//    @Bean
//    public BookRepository bookRepository() {
//        return new BookRepository();
//    }
//
//    @Bean
//    public BookSearchService bookSearchService() {
//        return new BookSearchService();
//    }
//
//    @Bean
//    public RecommendationService recommendationService() {
//        return new RecommendationService();
//    }
//
//}
//
//@Component
//class RecommendationService {
//
//    @Autowired
//    public BookRepository repository;
//
//    public String recommendBook() {
//        return repository.getBooks().get(0);
//    }
//
//}
