package top.madianpeng.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.madianpeng.pojo.BcDecidedzone;
import top.madianpeng.pojo.BcDecidedzoneExample;

public interface BcDecidedzoneMapper {
    int countByExample(BcDecidedzoneExample example);

    int deleteByExample(BcDecidedzoneExample example);

    int deleteByPrimaryKey(String id);

    int insert(BcDecidedzone record);

    int insertSelective(BcDecidedzone record);

    List<BcDecidedzone> selectByExample(BcDecidedzoneExample example);

    BcDecidedzone selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BcDecidedzone record, @Param("example") BcDecidedzoneExample example);

    int updateByExample(@Param("record") BcDecidedzone record, @Param("example") BcDecidedzoneExample example);

    int updateByPrimaryKeySelective(BcDecidedzone record);

    int updateByPrimaryKey(BcDecidedzone record);

	List<BcDecidedzone> queryDecidedzone(BcDecidedzone decidedzone);

	int delDecidezone(String[] ids);
}