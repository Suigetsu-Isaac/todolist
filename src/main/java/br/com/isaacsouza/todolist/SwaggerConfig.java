package br.com.isaacsouza.todolist;
/**
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.isaacsouza.todolist")) // Substitua pelo pacote onde estão seus controladores
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("API do Meu Aplicativo")
            .description("Documentação da API do Meu Aplicativo")
            .version("1.0")
            .contact(new Contact("Seu Nome", "", "seu@email.com"))
            .build();
    }
}
 */