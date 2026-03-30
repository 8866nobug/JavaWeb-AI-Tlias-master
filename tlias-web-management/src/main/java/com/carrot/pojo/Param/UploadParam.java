package com.carrot.pojo.Param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UploadParam {
    private String name;
    private Integer age;
    private MultipartFile file;
}
