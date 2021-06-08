package com.link_tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponseException {
    private String mensaje;
    private int status;
    private Timestamp time;
}
