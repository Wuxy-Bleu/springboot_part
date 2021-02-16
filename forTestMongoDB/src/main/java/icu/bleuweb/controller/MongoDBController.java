package icu.bleuweb.controller;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MongoDBController {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 创建【集合】
     * 创建一个大小没有限制的集合（默认集合创建方式）
     *
     * @return 创建集合的结果
     */
    @GetMapping("test")
    public String createCollection() {
        // 设置集合名称
        String collectionName = "users1";
        // 创建集合并返回集合信息
        mongoTemplate.createCollection(collectionName);
        // 检测新的集合是否存在，返回创建结果
        return mongoTemplate.collectionExists(collectionName) ? "创建视图成功" : "创建视图失败";
    }

    /**
     * 获取【集合名称】列表
     *
     * @return 集合名称列表
     */
    @GetMapping("getall")
    public Object getCollectionNames() {
        // 执行获取集合名称列表
        return mongoTemplate.getCollectionNames();
    }
}
