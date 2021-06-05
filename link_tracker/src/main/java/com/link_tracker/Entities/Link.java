package com.link_tracker.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Link {
    private int id;
    private String url;
    private boolean status,validation;
}
