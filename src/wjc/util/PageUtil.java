package wjc.util;

public class PageUtil {
    public static final int ROWNUM=7;
	private int goPage=1;
	private int count;
	private int pageNum;
	private int maxNum;
	private int minNum;
 
	public int getGoPage() {
		return goPage;
	}
	public int getCount() {
		return count;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getMaxNum() {
		return maxNum;
	}
	public int getMinNum() {
		return minNum;
	}
	public void setGoPage(int goPage) {
		if(goPage==0){
			this.goPage=1;
		}else if(goPage>pageNum){
			this.goPage=pageNum;
		}
		else{
			this.goPage=goPage;
		}
	}

 
	public void setCount(int count) {
		this.count = count;
	}

	public void setPageNum(int count) {
		if(count%ROWNUM==0){
			this.pageNum=count/ROWNUM;
		}else{
			this.pageNum=count/ROWNUM+1;
		}
	}
 
	public void setMaxNum() {
		this.maxNum = this.goPage*ROWNUM;
	}

	

	public void setMinNum() {
		this.minNum=(this.goPage-1)*ROWNUM+1;
	}
	public void setRowPageData(int goPage,int count){
		setGoPage(goPage);
		setCount(count);
		setPageNum(count);
		setMaxNum();
		setMinNum();
	}

}
