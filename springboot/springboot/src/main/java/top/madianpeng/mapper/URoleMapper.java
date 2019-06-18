package top.madianpeng.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.madianpeng.pojo.URole;
import top.madianpeng.pojo.URoleExample;

public interface URoleMapper {
    int countByExample(URoleExample example);

    int deleteByExample(URoleExample example);

    int deleteByPrimaryKey(String id);

    int insert(URole record);

    int insertSelective(URole record);

    List<URole> selectByExample(URoleExample example);

    URole selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") URole record, @Param("example") URoleExample example);

    int updateByExample(@Param("record") URole record, @Param("example") URoleExample example);

    int updateByPrimaryKeySelective(URole record);

    int updateByPrimaryKey(URole record);

	List<URole> findRoleByUid(String uid);
}