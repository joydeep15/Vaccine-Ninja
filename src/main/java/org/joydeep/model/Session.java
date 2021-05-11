package org.joydeep.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class Session {
    @Getter @Setter private String session_id;
    @Getter @Setter private String date;
    @Getter @Setter private Integer available_capacity;
    @Getter @Setter private Integer min_age_limit;
    @Getter @Setter private String vaccine;
    @Getter @Setter private List<String> slots;
}
