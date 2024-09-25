package com.aryansingh.instaforms.models.dtos.form;


import lombok.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
@Builder
@ToString
@NoArgsConstructor
public class SectionDTO {

    private String sectionName;

    private String description;

    private List<SectionResourceDTO> resources = new ArrayList<>();

    private Set<FormFieldDTO> formFields = new LinkedHashSet<>();

    private Integer sequenceNo;

}
