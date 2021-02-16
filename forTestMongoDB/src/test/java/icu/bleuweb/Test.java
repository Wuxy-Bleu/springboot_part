package icu.bleuweb;

import icu.bleuweb.bean.BookElasticBean;
import icu.bleuweb.repository.BookElasticRepo;
import icu.bleuweb.service.BookFileService;
import icu.bleuweb.service.DemoService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    DemoService demoService;

    //测试单独四个
    @org.junit.Test
    public void testDemo1() {
        demoService.demo(1);
    }

    //测试around
    @org.junit.Test
    public void testDemo2() {
        demoService.demo1(1L);
    }


    @Autowired
    BookFileService bookFileService;

    @org.junit.Test
    public void testES() throws IOException {
        bookFileService.writeBookDataToES();
    }
}
