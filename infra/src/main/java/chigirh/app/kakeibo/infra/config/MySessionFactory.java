package chigirh.app.kakeibo.infra.config;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

public class MySessionFactory {

    public static SqlSessionFactory create(
            DataSource dataSource,
            MybatisProperties mybatisProperties
    ) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

        // beanが上書きされないようにconfigurationを新たに生成
        Configuration configuration = new Configuration();
        configuration.setDefaultExecutorType(
                mybatisProperties.getConfiguration().getDefaultExecutorType()
        );
        configuration.setMapUnderscoreToCamelCase(
                mybatisProperties.getConfiguration().isMapUnderscoreToCamelCase()
        );

        // MapperLocationを設定
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(
                        mybatisProperties.getMapperLocations()[0]
                )
        );

        sqlSessionFactoryBean.setConfiguration(configuration);

        return sqlSessionFactoryBean.getObject();
    }
}
