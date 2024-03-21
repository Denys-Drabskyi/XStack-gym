//package org.example;
//
//
////import jakarta.servlet.FilterRegistration;
////import jakarta.servlet.ServletContext;
////import jakarta.servlet.ServletException;
////import jakarta.servlet.ServletRegistration;
//import javax.servlet.FilterRegistration;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.filter.CharacterEncodingFilter;
//import org.springframework.web.servlet.DispatcherServlet;
//
//public class MainWebAppInitializer implements WebApplicationInitializer {
//  @Override
//  public void onStartup(final ServletContext servletContext) throws ServletException {
//
//    //create the root Spring application context
//    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//    rootContext.setConfigLocation("org.example.configuration");
//
//    //add ContextLoaderListner to the ServletContext which will be responsible to load the application context
//    servletContext.addListener(new ContextLoaderListener(rootContext));
//
//    //register and map the dispatcher servlet
//    //note Dispatcher servlet with constructor arguments
//    ServletRegistration.Dynamic dispatcher =
//        servletContext.addServlet("dispatcher",  new DispatcherServlet(rootContext));
//    dispatcher.setLoadOnStartup(1);
//    dispatcher.addMapping("/");
//
//    //add specific encoding (e.g. UTF-8) via CharacterEncodingFilter
//    FilterRegistration.Dynamic encodingFilter =
//        servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
//    encodingFilter.setInitParameter("encoding", "UTF-8");
//    encodingFilter.setInitParameter("forceEncoding", "true");
//    encodingFilter.addMappingForUrlPatterns(null, true, "/*");
//  }
//
//
////  @Override
////  public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
////    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
////    rootContext.setConfigLocation("org.example.configuration");
////
////    //add ContextLoaderListner to the ServletContext which will be responsible to load the application context
////    servletContext.addListener(new ContextLoaderListener(rootContext));
////
////    //register and map the dispatcher servlet
////    //note Dispatcher servlet with constructor arguments
////    ServletRegistration.Dynamic dispatcher =
////        servletContext.addServlet("dispatcher",  new DispatcherServlet(rootContext));
//////        servletContext.addServlet("dispatcher",  new DefaultServlet(rootContext));
////    dispatcher.setLoadOnStartup(1);
////    dispatcher.addMapping("/");
////
////    //add specific encoding (e.g. UTF-8) via CharacterEncodingFilter
////    FilterRegistration.Dynamic encodingFilter =
////        servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
////    encodingFilter.setInitParameter("encoding", "UTF-8");
////    encodingFilter.setInitParameter("forceEncoding", "true");
////    encodingFilter.addMappingForUrlPatterns(null, true, "/*");
////
////  }
//}