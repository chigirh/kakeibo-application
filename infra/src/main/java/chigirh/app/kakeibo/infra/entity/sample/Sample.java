package chigirh.app.kakeibo.infra.entity.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sample {
    private String key = "";
    private String value = "";
    private int version = 1;

    public Sample(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
