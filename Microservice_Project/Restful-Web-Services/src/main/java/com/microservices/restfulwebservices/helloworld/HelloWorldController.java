package com.microservices.restfulwebservices.helloworld;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController  // Controller will expose a REST API
// Give Specific URL to this REST API = /hello-world
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    // Map this method to GET Method on URL
    // @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello World Bean");
    }

    /*                              Path Parameters
       /users/{id}/tools/{id}             =>      /users/2/todos/200
       /hello-world-bean/{name}
       /hello-world-bean/{Abhinav}             */
    @GetMapping(path = "/hello-world-bean/{name}")   // Map {name} -> name
    public HelloWorldBean helloWorldBeanPathVariable(@PathVariable String name){
        return new HelloWorldBean("Hello World Bean Path Varible " + name);
    }

    @GetMapping(path = "/hello-world-internationalised")   // Map {name} -> name
    public String helloWorldInternationalised(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);

    }




}
