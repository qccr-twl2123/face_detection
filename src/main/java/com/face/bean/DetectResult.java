
package com.face.bean;

/**
 * @Description 获取图片信息载体
 */

public class DetectResult extends Result{
	/**
	 * 人脸个数
	 */
	public int nface;
	/**
	 * 人脸的坐标list
	 */
	public Rect [] rcface;
	
	public int getNface() {
		return nface;
	}
	
	public void setNface(int nface) {
		this.nface = nface;
	}


	public Rect[] getRcface() {
		return rcface;
	}

	public void setRcface(Rect[] rcface) {
		this.rcface = rcface;
	}
}
