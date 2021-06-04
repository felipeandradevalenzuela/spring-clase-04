package com.link_tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseLinkDTO {
    private int id;
    private boolean status;
    private String description;
}
