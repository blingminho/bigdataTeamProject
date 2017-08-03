package team.project.dto;

public class Admin{
	private int postTotalCount;
	private int visitTotalCount;
	private int puppyTotalCount;
	private int regTotalCount;
	private int postTodayCount;
	private int visitTodayCount;
	private int puppyTodayCount;
	private int regTodayCount;
	@Override
	public String toString() {
		return "Admin [postTotalCount=" + postTotalCount + ", visitTotalCount=" + visitTotalCount + ", puppyTotalCount="
				+ puppyTotalCount + ", regTotalCount=" + regTotalCount + ", postTodayCount=" + postTodayCount
				+ ", visitTodayCount=" + visitTodayCount + ", puppyTodayCount=" + puppyTodayCount + ", regTodayCount="
				+ regTodayCount + "]";
	}
	public int getPostTotalCount() {
		return postTotalCount;
	}
	public void setPostTotalCount(int postTotalCount) {
		this.postTotalCount = postTotalCount;
	}
	public int getVisitTotalCount() {
		return visitTotalCount;
	}
	public void setVisitTotalCount(int visitTotalCount) {
		this.visitTotalCount = visitTotalCount;
	}
	public int getPuppyTotalCount() {
		return puppyTotalCount;
	}
	public void setPuppyTotalCount(int puppyTotalCount) {
		this.puppyTotalCount = puppyTotalCount;
	}
	public int getRegTotalCount() {
		return regTotalCount;
	}
	public void setRegTotalCount(int regTotalCount) {
		this.regTotalCount = regTotalCount;
	}
	public int getPostTodayCount() {
		return postTodayCount;
	}
	public void setPostTodayCount(int postTodayCount) {
		this.postTodayCount = postTodayCount;
	}
	public int getVisitTodayCount() {
		return visitTodayCount;
	}
	public void setVisitTodayCount(int visitTodayCount) {
		this.visitTodayCount = visitTodayCount;
	}
	public int getPuppyTodayCount() {
		return puppyTodayCount;
	}
	public void setPuppyTodayCount(int puppyTodayCount) {
		this.puppyTodayCount = puppyTodayCount;
	}
	public int getRegTodayCount() {
		return regTodayCount;
	}
	public void setRegTodayCount(int regTodayCount) {
		this.regTodayCount = regTodayCount;
	}

}
