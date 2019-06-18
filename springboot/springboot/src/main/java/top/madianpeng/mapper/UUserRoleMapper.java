package top.madianpeng.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.madianpeng.pojo.UUserRole;
import top.madianpeng.pojo.UUserRoleExample;

public interface UUserRoleMapper {
    int countByExample(UUserRoleExample example);

    int deleteByExample(UUserRoleExample example);

    int insert(UUserRole record);

    int insertSelective(UUserRole record);

    List<UUserRole> selectByExample(UUserRoleExample example);

    int updateByExampleSelective(@Param("record") UUserRole record, @Param("example") UUserRoleExample example);

    int updateByExample(@Param("record") UUserRole record, @Param("example") UUserRoleExample example);
}