package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by igor on 07.05.2021
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Token {

    private String token_type;
    private long expires_in;
    private String access_token;
    private String refresh_token;
}
