package chigirh.app.kakeibo.infra.mapper.reader;

import chigirh.app.kakeibo.infra.dto.sample.SampleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SampleReaderMapper {

    List<SampleDto> findSamples(
            @Param("offset") int offset,
            @Param("limit") int limit,
            @Param("orderByClause") String orderByClause
    );

    int countSample();

}
