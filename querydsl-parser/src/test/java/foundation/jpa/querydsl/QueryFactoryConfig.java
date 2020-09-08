package foundation.jpa.querydsl;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.persistence.EntityManager;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootConfiguration
@EnableAutoConfiguration
@AutoConfigurationPackage
public class QueryFactoryConfig {

    public interface RootEntities extends QuerydslPredicateExecutor<RootEntity> {}

    @Bean
    public QueryContext context(EntityManager entityManager) {
        Map<String, Object> vars = new LinkedHashMap<>();
        vars.put(EnumValue.class.getSimpleName(), EnumValue.class);
        Stream.of(EnumValue.class.getEnumConstants()).forEach(e -> vars.put(e.name(), e));
        return QueryContext.createContext((o, c) -> entityManager.find(c, o), vars);
    }

}
