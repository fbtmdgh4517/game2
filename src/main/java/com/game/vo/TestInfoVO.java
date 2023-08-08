package com.game.vo;

public class TestInfoVO {

	private int tiNum;
	private String tiName;
	private int tiAge;
	private String tiAddr;
	
	public int getTiNum() {
		return tiNum;
	}
	public void setTiNum(int tiNum) {
		this.tiNum = tiNum;
	}
	public String getTiName() {
		return tiName;
	}
	public void setTiName(String tiName) {
		this.tiName = tiName;
	}
	public int getTiAge() {
		return tiAge;
	}
	public void setTiAge(int tiAge) {
		this.tiAge = tiAge;
	}
	public String getTiAddr() {
		return tiAddr;
	}
	public void setTiAddr(String tiAddr) {
		this.tiAddr = tiAddr;
	}
	
	@Override
	public String toString() {
		return "TestInfoVO [tiNum=" + tiNum + ", tiName=" + tiName + ", tiAge=" + tiAge + ", tiAddr=" + tiAddr + "]";
	}
	
	
}
