package icu.bleuweb.service;

import com.alibaba.fastjson.JSON;
import icu.bleuweb.bean.BookElasticBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookFileService {
    @Autowired
    BookService bookService;

    /**
     * 将去重后的数据写入 ES
     */
    public void writeBookDataToES() {
        String filePath = System.getProperty("user.dir") + File.separator + "jd_book2.txt";

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            List<BookElasticBean> books = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                books.add(JSON.parseObject(line, BookElasticBean.class));
                if (books.size() >= 500) {
                    // 添加数据到 ES
                    bookService.addBook(books);
                    books.clear();
                }
            }
            bookService.addBook(books);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("ahjdsfkgnksejdbtnvigjeak");
        }
    }
}