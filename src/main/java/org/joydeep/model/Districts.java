package org.joydeep.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
public class Districts {

    @Getter @Setter
    private List<District> districts;

}
