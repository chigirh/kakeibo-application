package chigirh.app.kakeibo.infra.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("chigirh.app.kakeibo.infra.mapper.cluster")
public class ClusterDataSource {
}
