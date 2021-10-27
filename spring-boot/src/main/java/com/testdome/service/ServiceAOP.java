//package com.testdome.service;
//
//import org.aspectj.lang.*;
//import org.aspectj.lang.annotation.*;
//import org.springframework.context.annotation.*;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
///**
// * Add the appropriate annotation to the serviceAOP.serviceAdvice method to intercept all methods in the com.testdome.service package and invoke the ServiceCallback bean before the method is executed.
// *
// * Add the appropriate annotation to the ServiceAOP.searchServiceAdvice to intercept the bookExists mehtod of BookSearchService and invoke SearchCallback bean. The bookName argument should be passed to the callback.
// *
// * Add the appropriate annotation to the ServiceAOP.repositoryAdvice to intercept the getBooks method of the BookRepository class and invoke the RepositoryCallback bean.
// */
//
//interface ServiceCallback {
//    public void callback(String methodName);
//}
//
//interface SearchCallback {
//    public void callback(String searchedBook);
//}
//
//interface RepositoryCallback {
//    public void callback();
//}
//
//@Aspect
//public class ServiceAOP {
//    @Autowired
//    ServiceCallback serviceCallback;
//    @Autowired
//    SearchCallback searchCallback;
//    @Autowired
//    RepositoryCallback repositoryCallback;
//
//    @Before("execution(* com.testdome.service.*.*(..))")
//    public void serviceAdvice(JoinPoint jp) {
//        serviceCallback.callback(jp.getSignature().getName());
//    }
//
//    @Before("execution(* com.testdome.service.BookRepository.getBooks(..))")
//    public void repositoryAdvice(JoinPoint jp) {
//        repositoryCallback.callback();
//    }
//
//    @Before("execution(* com.testdome.service.BookSearchService.bookExists(..))")
//    public void searchServiceAdvice(JoinPoint joinPoint) throws Throwable {
//        searchCallback.callback((String) joinPoint.getArgs()[0]);
//    }
//
////    public static void main(String[] args) {
////        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
////        config.register(TestConfig3.class);
////        config.refresh();
////
////        BookSearchService service = config.getBean(BookSearchService.class);
////        System.out.println(service.bookExists("Book2"));
////    }
//}
//
//@Configuration
//@EnableAspectJAutoProxy
//@Import(ServiceAOP.class)
//@ComponentScan(basePackages = "com.testdome.service")
//class TestConfig3 {
//    @Bean
//    public ServiceCallback serviceCallback() {
//        return (methodName) -> System.out.println(methodName);
//    }
//
//    @Bean
//    public SearchCallback searchCallback() {
//        return (book) -> System.out.println(book);
//    }
//
//    @Bean
//    public RepositoryCallback repositoryCallback() {
//        return () -> System.out.println("Repository called!");
//    }
//}
