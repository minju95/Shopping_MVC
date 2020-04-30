package admin.goods.action;

public class ActionForward {
	private boolean isRedirect=false; //redirect 여부
	private String path=null; //포워딩 될 경로
	
	public boolean isRedirect(){
		return isRedirect;
	}
	
	public String getPath(){
		return path;
	}
	
	public void setRedirect(boolean b){
		isRedirect=b;
	}
	
	public void setPath(String string){
		path=string;
	}
}