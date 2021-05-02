package chigirh.app.kakeibo.infra.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import chigirh.app.kakeibo.infra.config.DatasourceConstant.CLUSTER;

import javax.sql.DataSource;

// Create,Update,Delete用Datasource
@Configuration
@MapperScan(
        basePackages = {"chigirh.app.kakeibo.infra.mapper.cluster"},
        sqlSessionFactoryRef = CLUSTER.SESSION_FACTORY
)
public class ClusterEndpointDatasourceConfig {
    @Primary
    @ConfigurationProperties("spring.datasource.cluster")
    @Bean(name = {CLUSTER.PROPERTIES})
    public DataSourceProperties datasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = {CLUSTER.DATASOURCE})
    @Primary
    public DataSource datasource(
            @Qualifier(CLUSTER.PROPERTIES) DataSourceProperties properties
    ) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = {CLUSTER.TX_MANAGER})
    public PlatformTransactionManager txManager(
            @Qualifier(CLUSTER.DATASOURCE) DataSource dataSource
    ) {
        return new JdbcTransactionManager(dataSource);
    }

    @Bean(name = {CLUSTER.SESSION_FACTORY})
    @Primary
    public SqlSessionFactory sessionFactory(
            @Qualifier(CLUSTER.DATASOURCE) DataSource dataSource,
            MybatisProperties mybatisProperties
    ) throws Exception {
        return MySessionFactory.create(dataSource, mybatisProperties);
    }
}
