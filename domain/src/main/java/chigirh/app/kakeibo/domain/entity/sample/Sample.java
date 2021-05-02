package chigirh.app.kakeibo.domain.entity.sample;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Sample {
    private String key;
    private String value;
    private int version;
}
