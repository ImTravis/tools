//package com.tools.elasticsearch;
//
//import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
//import com.tools.Utils.SelfUtils;
//import com.tools.Utils.TimeUtils;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * @author Mr.Xue.
// * @program: tools
// * @create 2019-03-28 17:48
// * @des 描述：
// */
//@RestController
//public class GoodsController {
//    @Autowired
//    private GoodsRepository goodsRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//
//    @Autowired
//    private ElasticsearchTemplate elasticsearchTemplate;
//
//    //http://localhost:8888/save
//    @GetMapping("save")
//    public String save() {
//        List<String> goodName = new ArrayList<>();
//        goodName.add("桃子");
//        goodName.add("水蜜桃");
//        goodName.add("猕猴桃");
//        goodName.add("毛桃");
//        goodName.add("毛桃26");
//        goodName.add("梨子");
//        goodName.add("西瓜");
//        goodName.add("番茄");
//        goodName.add("香蕉");
//        goodName.add("苹果");
//        goodName.add("哈密瓜");
//
//        for (int k = 0; k < 100; k++) {
//            int index = Integer.valueOf(SelfUtils.getRandom(2));
//            int sIndex = index % goodName.size();
//            GoodsInfo goodsInfo = new GoodsInfo(System.currentTimeMillis(),
//                    goodName.get(sIndex), "描述:" + goodName.get(sIndex) + ",一共：" + SelfUtils.getRandom(1) + "斤,出库日期：" + TimeUtils.getCurrentTimeStr());
//            goodsRepository.save(goodsInfo);
//        }
//
//        return "success";
//    }
//
//    //创建索引
//    @GetMapping("createqIndex")
//    public String createqIndex() {
//        elasticsearchTemplate.createIndex(GoodsInfo.class);
//        return "success";
//    }
//
//    //查询
//    @GetMapping("getAll")
//    public ResponseEntity getAll() {
//        Iterable<GoodsInfo> goods = goodsRepository.findAll(Sort.by("name").ascending());
//        return ResponseEntity.ok(goods);
//    }
//
//    //自定义查询
//    @GetMapping("matchQuery")
//    public ResponseEntity matchQuery() {
//        // 构建查询条件
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//
//        //TODO matchQuery：添加基本分词查询(全匹配)  查找的属性 类型不能为 keyword
//        queryBuilder.withQuery(QueryBuilders.matchQuery("description", "桃子"));
//
//        // 分页：
//        int page = 0;
//        int size = 50;
//        queryBuilder.withPageable(PageRequest.of(page, size));
//
//        // 搜索，获取结果
//        Page<GoodsInfo> goods = this.goodsRepository.search(queryBuilder.build());
//        // 总条数
//        long total = goods.getTotalElements();
//        System.out.println("total = " + total);
//
//        return ResponseEntity.ok(goods);
//    }
//
//    //自定义查询
//
//    /**
//     * termQuery:功能更强大，除了匹配字符串以外，还可以匹配
//     * int/long/double/float/....
//     *
//     * @return
//     */
//    @GetMapping("queryPrice")
//    public ResponseEntity queryPrice() {
//        // 构建查询条件
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//
//        //TODO 添加基本分词查询(全匹配)  查找的属性 类型不能为 keyword
//        //TODO termQuery:功能更强大，除了匹配字符串以外，还可以匹配
//        // int/long/double/float/....
//        queryBuilder.withQuery(QueryBuilders.termQuery("description", "26"));
//
//        // 分页：
//        int page = 0;
//        int size = 50;
//        queryBuilder.withPageable(PageRequest.of(page, size));
//
//        // 搜索，获取结果
//        Page<GoodsInfo> goods = this.goodsRepository.search(queryBuilder.build());
//        // 总条数
//        long total = goods.getTotalElements();
//        System.out.println("total = " + total);
//
//        return ResponseEntity.ok(goods);
//    }
//
//    /**
//     * @Description:布尔查询
//     * @Author: https://blog.csdn.net/chen_2890
//     */
//    @GetMapping("queryBoolean")
//    public ResponseEntity queryBoolean() {
//        // 构建查询条件
//        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
//
//        //TODO  name Keyword 类型 ，所以只能通过精确值搜索到，
//        //TODO description text 类型，所以全文搜索；设置text类型以后，字段内容会被分析，在生成倒排索引以前，字符串会被分析器分成一个一个词项。text类型的字段不用于排序，很少用于聚合
//        queryBuilder.withQuery(
//                QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("name", "毛桃"))
//                        .must(QueryBuilders.matchQuery("description", "6"))
//        );
//
//        // 分页：
//        int page = 0;
//        int size = 50;
//        queryBuilder.withPageable(PageRequest.of(page, size));
//
//        // 搜索，获取结果
//        Page<GoodsInfo> goods = this.goodsRepository.search(queryBuilder.build());
//        // 总条数
//        long total = goods.getTotalElements();
//        System.out.println("total = " + total);
//
//        return ResponseEntity.ok(goods);
//    }
//
//
//    @GetMapping("payOrder")
//    public String payOrder() {
//        List<String> goodName = new ArrayList<>();
//        goodName.add("桃子");
//        goodName.add("水蜜桃");
//        goodName.add("猕猴桃");
//        goodName.add("毛桃");
//        goodName.add("毛桃26");
//        goodName.add("梨子");
//        goodName.add("西瓜");
//        goodName.add("番茄");
//        goodName.add("香蕉");
//        goodName.add("苹果");
//        goodName.add("哈密瓜");
//
//        for (int k = 0; k < 100; k++) {
//            int index = Integer.valueOf(SelfUtils.getRandom(2));
//            int sIndex = index % goodName.size();
//            OrderInfo orderInfo = new OrderInfo();
//            orderInfo.setId(System.currentTimeMillis());
//            orderInfo.setDate(new Date());
//            orderInfo.setGood(goodName.get(sIndex));
//            orderInfo.setOrderNo(SelfUtils.getOrderIdByUUId(1));
//            orderInfo.setPrice(SelfUtils.getRandom(1));
//            orderRepository.save(orderInfo);
//        }
//
//        return "success";
//    }
//
//}
