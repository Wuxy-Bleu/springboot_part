package icu.bleuweb.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@Document(indexName = "crm", type = "person")
public class BookElasticBean {

    @Id
    private Long id;

    private String name; // zs

    private Integer age;

    private String intro; //zs is disaosi

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String all; //所有要做模块查询的字段值通过“ ”隔开 zs  zs is disaosi
}
