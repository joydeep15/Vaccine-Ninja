package org.joydeep.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class District {

    @Getter @Setter private Integer district_id;
    @Getter @Setter private String district_name;

}
