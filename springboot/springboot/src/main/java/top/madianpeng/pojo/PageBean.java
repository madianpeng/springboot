package top.madianpeng.pojo;

import java.io.Serializable;
import java.util.List;


public class PageBean<T> extends ReturnValue implements Serializable{

	private static final long serialVersionUID = 1L;

    public List<T> data;//分页查询出来的数据
    
    public int count;//总记录数  需要查询出来


	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
    
    }