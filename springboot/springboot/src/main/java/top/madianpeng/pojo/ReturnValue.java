package top.madianpeng.pojo;

public class ReturnValue {
	public int code=0;
	public String msg="成功";


	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void isFail(String msg) {
		this.msg = msg+"失败";
		this.code = 1;
	}
	public void isSuccess(String msg) {
		this.msg = msg+"成功";
		this.code = 0;
	}
	
}
