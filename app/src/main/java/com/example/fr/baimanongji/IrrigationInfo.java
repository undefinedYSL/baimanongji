package com.example.fr.baimanongji;

public class IrrigationInfo {
	private String flag,humi1,humi2,humi3,humi4,temp1,temp2,ec,flow;
	public IrrigationInfo(){

	}

	public IrrigationInfo(String flag, String humi1, String humi2, String humi3, String ec, String flow) {
		this.flag = flag;
		this.humi1 = humi1;
		this.humi2 = humi2;
		this.humi3 = humi3;
		this.ec = ec;
		this.flow = flow;
	}

	public IrrigationInfo(String flag, String humi1, String humi2, String humi3, String humi4, String ec, String flow) {
		this.flag = flag;
		this.humi1 = humi1;
		this.humi2 = humi2;
		this.humi3 = humi3;
		this.humi4 = humi4;
		this.ec = ec;
		this.flow = flow;
	}

	public IrrigationInfo(String flag, String temp1, String temp2, String ec, String flow) {
		this.flag = flag;
		this.temp1 = temp1;
		this.temp2 = temp2;
		this.ec = ec;
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "IrrigationInfo{" +
				"humi1='" + humi1 + '\'' +
				", humi2='" + humi2 + '\'' +
				", humi3='" + humi3 + '\'' +
				", humi4='" + humi4 + '\'' +
				", ec='" + ec + '\'' +
				", flow='" + flow + '\'' +
				", temp1='" + temp1 + '\'' +
				", temp2='" + temp2 + '\'' +
				'}';
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getHumi1() {
		return humi1;
	}

	public void setHumi1(String humi1) {
		this.humi1 = humi1;
	}

	public String getHumi2() {
		return humi2;
	}

	public void setHumi2(String humi2) {
		this.humi2 = humi2;
	}

	public String getHumi3() {
		return humi3;
	}

	public void setHumi3(String humi3) {
		this.humi3 = humi3;
	}

	public String getHumi4() {
		return humi4;
	}

	public void setHumi4(String humi4) {
		this.humi4 = humi4;
	}

	public String getEc() {
		return ec;
	}

	public void setEc(String ec) {
		this.ec = ec;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getTemp1() {
		return temp1;
	}

	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	public String getTemp2() {
		return temp2;
	}

	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}


}
