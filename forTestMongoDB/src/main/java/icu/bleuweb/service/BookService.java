package icu.bleuweb.service;

import icu.bleuweb.bean.BookElasticBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void addBook(List<BookElasticBean> books) {
        elasticsearchRestTemplate.save(books);
    }
}