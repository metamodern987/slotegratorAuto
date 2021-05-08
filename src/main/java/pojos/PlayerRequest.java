package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by igor on 06.05.2021
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class PlayerRequest {

    private String username;
    private String password_change ;
    private String password_repeat;
    private String email ;
    private String name;
    private String surname ;
    private String currency_code ;
}
