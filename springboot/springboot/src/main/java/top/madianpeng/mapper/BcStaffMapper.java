package top.madianpeng.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.madianpeng.pojo.BcStaff;
import top.madianpeng.pojo.BcStaffExample;

public interface BcStaffMapper {
    int countByExample(BcStaffExample example);

    int deleteByExample(BcStaffExample example);

    int deleteByPrimaryKey(String id);

    int insert(BcStaff record);

    int insertSelective(BcStaff record);

    List<BcStaff> selectByExample(BcStaffExample example);

    BcStaff selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BcStaff record, @Param("example") BcStaffExample example);

    int updateByExample(@Param("record") BcStaff record, @Param("example") BcStaffExample example);

    int updateByPrimaryKeySelective(BcStaff record);

    int updateByPrimaryKey(BcStaff record);
    
    List<BcStaff> queryStaff(BcStaff bcStaff);

	int queryCount();

	int delStaff(List<String> idsList);
}