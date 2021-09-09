package com.longfor.it.providertwo.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 */
@Repository
public interface ProviderTwoMapper {
    @Insert("insert into t_provider(id,name, status) values (#{id},#{name}, #{status})")
    void insert(@Param("id") String id, @Param("name") String name, @Param("status") int status);

    @Update("update t_provider set status = #{status} where id = #{id}")
    void updateStatus(@Param("id") String id, @Param("status") int status);

    @Delete("delete from t_provider where id = #{id}")
    void deleteById(@Param("id") String id);
}
