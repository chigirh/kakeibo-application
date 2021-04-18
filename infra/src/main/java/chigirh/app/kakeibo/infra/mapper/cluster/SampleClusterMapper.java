package chigirh.app.kakeibo.infra.mapper.cluster;

import chigirh.app.kakeibo.infra.entity.sample.Sample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SampleClusterMapper {

    int insert(@Param("entities") List<Sample> entities);
}
