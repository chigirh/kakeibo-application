package chigirh.app.kakeibo.infra.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import chigirh.app.kakeibo.infra.config.DatasourceConstant.READER;

import javax.sql.DataSource;

// Read用Datasource
@Configuration
@MapperScan(
        basePackages = {"chigirh.app.kakeibo.infra.mapper.reader"},
        sqlSessionFactoryRef = READER.SESSION_FACTORY
)
public class ReaderEndpointDatasourceConfig {
    @ConfigurationProperties("spring.datasource.reader")
    @Bean(name = {READER.PROPERTIES})
    public DataSourceProperties datasourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = {READER.DATASOURCE})
    public DataSource datasource2(
            @Qualifier(READER.PROPERTIES) DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Bean(name = {READER.TX_MANAGER})
    public PlatformTransactionManager txManager(
            @Qualifier(READER.DATASOURCE) DataSource dataSource
    ) {
        JdbcTransactionManager transactionManager = new JdbcTransactionManager(dataSource);
        // Readerは読み取り専用に設定する
        transactionManager.setEnforceReadOnly(true);
        return transactionManager;
    }

    @Bean(name = {READER.SESSION_FACTORY})
    public SqlSessionFactory sessionFactory(
            @Qualifier(READER.DATASOURCE) DataSource dataSource,
            MybatisProperties mybatisProperties
    ) throws Exception {
        return MySessionFactory.create(dataSource, mybatisProperties);
    }
}
