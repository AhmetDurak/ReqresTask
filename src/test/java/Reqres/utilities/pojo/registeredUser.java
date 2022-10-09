package Reqres.utilities.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"id","token"}, allowSetters = true)
public class registeredUser {

    private int id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    private String username;
    private String password;
    private String token;
}
