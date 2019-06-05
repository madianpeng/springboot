package top.madianpeng.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import top.madianpeng.pojo.Area;
import top.madianpeng.pojo.BcSubarea;
import top.madianpeng.pojo.BcSubareaExample;

public interface BcSubareaMapper {
    int countByExample(BcSubareaExample example);

    int deleteByExample(BcSubareaExample example);

    int deleteByPrimaryKey(String id);

    int insert(BcSubarea record);

    int insertSelective(BcSubarea record);

    List<BcSubarea> selectByExample(BcSubareaExample example);

    BcSubarea selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BcSubarea record, @Param("example") BcSubareaExample example);

    int updateByExample(@Param("record") BcSubarea record, @Param("example") BcSubareaExample example);

    int updateByPrimaryKeySelective(BcSubarea record);

    int updateByPrimaryKey(BcSubarea record);

	List<BcSubarea> querySubarea(BcSubarea subarea);

	int delSubarea(List<String> id);
	
	List<Area> queryCity();
}	