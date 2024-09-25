package com.aryansingh.instaforms.models.dtos.form;

import com.aryansingh.instaforms.models.entities.form.enums.MediaType;
import lombok.*;

@AllArgsConstructor
@Data
@Builder
@ToString
@NoArgsConstructor
public class FieldResourceDTO {


    private String url;

    private String title;

    private String description;

    private MediaType mediaType;

}
