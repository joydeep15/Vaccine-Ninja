package org.joydeep.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class State {

    @Getter @Setter private Integer state_id;
    @Getter @Setter private String state_name;

}
