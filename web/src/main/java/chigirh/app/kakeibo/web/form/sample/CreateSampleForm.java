package chigirh.app.kakeibo.web.form.sample;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CreateSampleForm {
    @Size(min = 1, max = 10)
    @NotNull
    private String key = "";

    @Size(min = 1, max = 30)
    private String value = "";
}
