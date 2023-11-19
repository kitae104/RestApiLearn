package kr.inhatc.spring.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@JsonIgnoreProperties(value = {"filed1", "filed2"})     // static filtering
public class SomeBean {
    private String filed1;

    //@JsonIgnore     // static filtering
    private String filed2;
    private String filed3;
}
