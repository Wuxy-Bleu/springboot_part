package icu.bleuweb.repository;

import icu.bleuweb.bean.BookElasticBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookElasticRepo extends ElasticsearchRepository<BookElasticBean, Long> {
}
