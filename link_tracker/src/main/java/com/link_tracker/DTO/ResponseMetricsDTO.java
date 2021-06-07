package com.link_tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMetricsDTO {
    private String url;
    private int statistics;

    @Override
    public String toString() {
        return "La url "+url+" Fue redireccionada "+statistics+ " veces";
    }
}
