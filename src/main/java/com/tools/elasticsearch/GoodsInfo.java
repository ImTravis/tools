//package com.tools.elasticsearch;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.io.Serializable;
//
///**
// * @author Mr.Xue.
// * @program: tools
// * @create 2019-03-28 17:43
// * @des 描述：
// */
//@Document(indexName = "goodsinfo",type = "goods")
//@Data
//public class GoodsInfo implements Serializable {
//    @Id
//    private Long id;
//
//    @Field(type = FieldType.Keyword)
//    private String name;
//
//    @Field(type = FieldType.Text)
//    private String description;
//
//    public GoodsInfo(Long id, String name, String description) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//}
