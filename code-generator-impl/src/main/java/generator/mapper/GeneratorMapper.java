package generator.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GeneratorMapper {

    @Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables" +
            "            where table_schema = (select database()) and table_name = #{tableName}")
    Map<String, String> queryTable(@Param("tableName") String tableName);

    @Select("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns" +
            "            where table_schema = (select database()) and table_name = #{tableName} order by ordinal_position")
    List<Map<String, String>> queryColumns(@Param("tableName") String tableName);

}
