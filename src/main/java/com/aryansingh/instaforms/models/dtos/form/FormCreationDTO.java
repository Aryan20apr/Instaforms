package com.aryansingh.instaforms.models.dtos.form;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@Builder
@ToString
@NoArgsConstructor
public class FormCreationDTO {

    private String title;

    private FormSettingsDTO formSettings;

    private String description;

    private Boolean isEnabled;

    private String formToken;

    private List<SectionDTO> sections = new ArrayList<>();

    private Boolean isUpdatable;

    // private String createdBy;

}
