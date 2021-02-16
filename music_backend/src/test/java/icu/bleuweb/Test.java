package icu.bleuweb;

import icu.bleuweb.dao.DataSource2Dao;
import icu.bleuweb.utils.SpringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    DataSource2Dao dao;

    @org.junit.Test
    public void testDynamicDataSource01() {
        System.out.println(dao);
        Object[] all = dao.getAll();
        for (Object str :
                all) {
            System.out.println(str);
        }
    }

}
