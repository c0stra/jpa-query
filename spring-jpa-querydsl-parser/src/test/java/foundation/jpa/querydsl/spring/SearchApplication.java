package foundation.jpa.querydsl.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

import static foundation.jpa.querydsl.spring.SearchParameterHandlerBuilder.searchParameterHandlerBuilder;
import static java.util.Arrays.asList;

@SpringBootApplication
public class SearchApplication implements WebMvcConfigurer {

    public static void main(String... args) {
        SpringApplication.run(SearchApplication.class);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(searchParameterHandler(null));
    }

    @Bean
    public SearchParameterHandler searchParameterHandler(EntityManager manager) {
        return searchParameterHandlerBuilder(manager).build();
    }

    @Bean
    public boolean data(RootEntityRepository repository) {
        repository.save(new RootEntity().setName("ROOT1").setEnumValue(EnumValue.VALUE1).setSize(15).setManyToOneEntity(new ManyToOneEntity()).setManyToManyEntities(asList(
                new ManyToManyEntity(), new ManyToManyEntity()
        )).setOneToManyEntities(asList(
                new OneToManyEntity().setString("A"), new OneToManyEntity().setString("B"), new OneToManyEntity().setString("C")
        )));
        repository.save(new RootEntity().setName("ROOT2").setEnumValue(EnumValue.VALUE2).setSize(0).setManyToOneEntity(new ManyToOneEntity()).setManyToManyEntities(asList(
                new ManyToManyEntity(), new ManyToManyEntity()
        )).setOneToManyEntities(asList(
                new OneToManyEntity().setString("D"), new OneToManyEntity().setString("A")
        )));
        return true;
    }

}
