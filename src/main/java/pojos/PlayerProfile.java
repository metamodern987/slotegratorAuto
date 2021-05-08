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
public class PlayerProfile {

    private long id;
    private long country_id;
    private long timezone_id;
    private String username;
    private String email;
    private String surname;
    private String gender;
    private String phone_number;
    private String birthdate;
    private Boolean bonuses_allowed;
    private Boolean is_verified;

}
