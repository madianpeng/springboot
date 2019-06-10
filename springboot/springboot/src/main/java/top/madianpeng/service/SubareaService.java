package top.madianpeng.service;

import java.util.List;

import top.madianpeng.pojo.Area;
import top.madianpeng.pojo.BcSubarea;
import top.madianpeng.pojo.PageBean;
import top.madianpeng.pojo.ReturnValue;

public interface SubareaService {
	
	/**
	 * 分页查询
	 * @param subarea
	 * @return
	 */
	PageBean<BcSubarea> querySubarea(BcSubarea subarea);
	/**
	 * 添加分区
	 * @param subarea
	 * @return
	 */
	ReturnValue addSubarea(BcSubarea subarea);
	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	BcSubarea findById(String id);
	/**
	 * 保存修改
	 * @param subarea
	 * @return
	 */
	ReturnValue update(BcSubarea subarea);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	ReturnValue delSubarea(List<String> id);
	/**
	 * 省市区树形结构查询
	 * @return
	 */
	List<Area> queryCity();
	/**
	 * 为定区查询
	 * @return
	 */
	List<BcSubarea> queryForDecide(String id);
}
