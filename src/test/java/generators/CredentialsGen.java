package generators;

import lombok.Data;
import org.graalvm.compiler.nodes.java.NewArrayNode;
import pojos.*;

/**
 * Created by igor on 07.05.2021
 */
@Data
public class CredentialsGen {

    public CredentialsGen credentialsGen = new CredentialsGen();

    public static Credentials getCreds(){
        Credentials creds = new Credentials();
        creds.setGrant_type("client_credentials");
        creds.setScope("guest:default");
        return creds;
    }



}
