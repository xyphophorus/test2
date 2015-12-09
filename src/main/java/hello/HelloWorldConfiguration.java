package hello;

import com.google.common.base.Predicate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger1.annotations.EnableSwagger;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//import springfox.petstore.controller.PetController;
//import springfoxdemo.boot.swagger.web.FileUploadController;
//import springfoxdemo.boot.swagger.web.HomeController;

import java.util.ArrayList;

import static com.google.common.base.Predicates.*;
import static com.google.common.collect.Lists.*;
import static springfox.documentation.builders.PathSelectors.*;

@SpringBootApplication
@EnableSwagger2 //Enable swagger 2.0 spec
@ComponentScan(basePackageClasses = {
        HelloWorldController.class
})
public class HelloWorldConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldConfiguration.class, args);
    }
    
    // CORS / "access-control-origin" header stuff, so SwaggerUI website at different domain from this
    //  service will be allowed by modern browers to cross-site load our Swagger info.
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("**/*").allowedOrigins("*");
                //registry.addMapping("/hello-world");
            }
        };
    }

    @Bean
    public Docket salutationApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("salutation-api")
                .select()
                //.paths(isSalutationPath())
                .build()
                .apiInfo(apiInfo());
    }
    
    private Predicate<String> isSalutationPath() {
        return /*or(*/
                regex("/api/*")/*,
                regex("/api/*")
                )*/
        ;
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Hello-World springboot service API")
                .description("A classic, in SpringBoot form, suitable for Swaggerizing(TM).")
                .termsOfServiceUrl("http://nonexistenturl.com/tos.html")
                .contact("nobody")
                .license("Apache License Version 2.0")
                .licenseUrl("http://nonexistenturl.com/license.html")
                .version("2.0")
                .build();
    }
    
}