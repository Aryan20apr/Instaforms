package com.aryansingh.instaforms.models.entities.form.content.fieldtypes;

import com.aryansingh.instaforms.models.entities.form.content.FormField;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("FILE")
public class FileUpload extends FormField {

    String fileName;

    String url;

    @Type(ListArrayType.class)
    @Column(
            name = "supported_formats",
            columnDefinition = "text[]"
    )
    private List<String> supportedFormats = new ArrayList<>();


}
