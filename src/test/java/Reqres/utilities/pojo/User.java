package Reqres.utilities.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(value = {"id","createdAt, updatedAt"}, allowSetters = true)

public class User {

    private int id;
    private String name;
    private String job;
    private String createdAt;
    private String updatedAt;
}
