package me.lozm.api.queryParameter;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class QueryParameterModel {

    @NotEmpty
    private String name;
    @NotEmpty
    private String age;
    @NotEmpty
    private String interest;

}
