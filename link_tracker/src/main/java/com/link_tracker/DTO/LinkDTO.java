package com.link_tracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.view.RedirectView;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {
    private RedirectView link;
}
