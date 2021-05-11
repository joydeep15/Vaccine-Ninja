package org.joydeep.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class Center {

    @Getter @Setter private String center_id;
    @Getter @Setter private String name;
    @Getter @Setter private String state_name;
    @Getter @Setter private String district_name;
    @Getter @Setter private String block_name;
    @Getter @Setter private String pincode;
    @Getter @Setter private String from;
    @Getter @Setter private String to;
    @Getter @Setter private String fee_type;
    @Getter @Setter private List<Session> sessions;

}
