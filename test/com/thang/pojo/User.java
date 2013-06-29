package com.thang.pojo;

import com.thang.model.mate.Column;
import com.thang.model.mate.Table;

/**
 * @author gandilong
 */
@Table("sys_user_info")
public class User {

	private Integer id;
	
	@Column("user_name")
	private String userName;
	
	@Column("login_name")
	private String loginName;
	
	@Column("login_pass")
	private String loginPass;
	
	private String birth;
	private String image;//头像
	private String sex;//0 girl, 1 boy
	
	private String dept;//部门
	private String opt;//备注
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPass() {
		return loginPass;
	}
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	 
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getOpt() {
		return opt;
	}
	public void setOpt(String opt) {
		this.opt = opt;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", loginName="
				+ loginName + ", loginPass=" + loginPass + ", birth=" + birth
				+ ", image=" + image + ", sex=" + sex + ", dept=" + dept
				+ ", opt=" + opt + "]";
	}
	
	
	
}
