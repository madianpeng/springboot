package top.madianpeng.pojo;

import java.io.Serializable;
import java.util.List;

public class Area implements Serializable{

	private static final long serialVersionUID = 1L;

	
    public String id;
    
    public String parentid;
    
    public String label;

    /**
    * 下一级地区列表
    */
    public List<Area> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<Area> getChildren() {
		return children;
	}

	public void setChildren(List<Area> children) {
		this.children = children;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
    
    

}