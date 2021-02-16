package icu.bleuweb.dao;

import icu.bleuweb.dataSource.DataSource;
import icu.bleuweb.dataSource.DataSourceNames;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DataSource2Dao {

    @Select("Select * from admin")
    @DataSource(DataSourceNames.TWO)
    public Object[] getAll();
}
