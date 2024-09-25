package com.aryansingh.instaforms.models.dtos.form;

import com.aryansingh.instaforms.models.entities.form.content.FormFieldType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
@ToString
@NoArgsConstructor
public class FormFieldDTO {

    private String title;

    private List<FieldResourceDTO> resources = new ArrayList<>();

    private FormFieldType type;

    private Boolean required;

    private Integer sequenceNumber;
}
