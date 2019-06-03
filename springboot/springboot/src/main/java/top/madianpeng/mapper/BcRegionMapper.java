package top.madianpeng.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.madianpeng.pojo.BcRegion;
import top.madianpeng.pojo.BcRegionExample;

public interface BcRegionMapper {
    int countByExample(BcRegionExample example);

    int deleteByExample(BcRegionExample example);

    int deleteByPrimaryKey(String id);

    int insert(BcRegion record);

    int insertSelective(BcRegion record);

    List<BcRegion> selectByExample(BcRegionExample example);

    BcRegion selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BcRegion record, @Param("example") BcRegionExample example);

    int updateByExample(@Param("record") BcRegion record, @Param("example") BcRegionExample example);

    int updateByPrimaryKeySelective(BcRegion record);

    int updateByPrimaryKey(BcRegion record);
    
    List<BcRegion> queryRegion(BcRegion region);
    
    int queryCount();

	int importAdd(List<BcRegion> regionlist);
}