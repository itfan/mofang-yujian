package net.iaround.android.ui.meetgame;

/**
 * @fileName MeetGame.java
 * @package net.iaround.android.ui.meetgame
 * @description 邂逅游戏实体类
 * @author 任东卫
 * @email 86930007@qq.com
 * @version 1.0
 */
public class MeetGame {
	/** 姓名 **/
	private String name;
	/** 年龄 **/
	private int age;
	/** 性别 **/
	private int sex;
	/** 签名 **/
	private String signature;
	/** 照片 **/
	private String image;
	/** 匹配度 **/
	private int matching;
	/** 合拍度 **/
	private int appearance;
	/** 机缘度 **/
	private int fatematching;
	/** 活跃度 **/
	private int constellation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getMatching() {
		return matching;
	}

	public void setMatching(int matching) {
		this.matching = matching;
	}

	public int getAppearance() {
		return appearance;
	}

	public void setAppearance(int appearance) {
		this.appearance = appearance;
	}

	public int getFatematching() {
		return fatematching;
	}

	public void setFatematching(int fatematching) {
		this.fatematching = fatematching;
	}

	public int getConstellation() {
		return constellation;
	}

	public void setConstellation(int constellation) {
		this.constellation = constellation;
	}

}
