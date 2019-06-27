//package com.tools.elasticsearch;
//
//import lombok.Data;
//import org.hibernate.exception.DataException;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.util.Date;
//
///**
// * @author Mr.Xue.
// * @program: tools
// * @create 2019-04-02 10:44
// * @des 描述：
// */
//@Document(indexName = "orderinfo", type = "orders")
//@Data
//public class OrderInfo {
//    @Id
//    private Long id;
//
//    @Field(type = FieldType.Keyword)
//    private String orderNo;
//
//    @Field(type = FieldType.Text)
//    private String good;
//
//    @Field(type = FieldType.Date)
//    private Date date;
//
//    @Field(type = FieldType.Keyword)
//    private String price;
//}
