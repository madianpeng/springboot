package top.madianpeng.pojo;

import java.io.Serializable;

public class Pageweb implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public int limit;//一页显示条数 固定赋值
    
    public int page;//当前页  前台传递

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
    
    
}
