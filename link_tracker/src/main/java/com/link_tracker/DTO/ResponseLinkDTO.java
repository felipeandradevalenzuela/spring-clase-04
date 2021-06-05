package com.link_tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLinkDTO {
    private int id;
    private boolean status;
    private String description;
}
