package com.cl.hannoi;

import java.awt.Component;
import java.awt.Container;


/**
 * 塔的节点
 * @author L
 * @date 2015年10月31日
 */
public class TowerPoint {
	private int x, y;// 塔点坐标
	private boolean haveDisc;// 是否有盘子在该塔点上
	private Disc disc;// 该塔点上的盘子

	/**
	 * 创建一个塔点
	 * @author L
	 * @date 2015年10月31日
	 * @param x 塔点横坐标
	 * @param y 塔点纵坐标
	 */
	public TowerPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isHaveDisc() {
		return haveDisc;
	}

	public void setHaveDisc(boolean haveDisc) {
		this.haveDisc = haveDisc;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
//	public boolean equals(TowerPoint point){
//		if(point.getX()==this.getX()&&point.getY()==this.getY()){
//			return true;
//		}else {
//			return false;
//		}
//	}

	/**
	 * 将盘子显示出来，放在该节点上
	 * @author L
	 * @date 2015年10月31日
	 * @param com
	 * @param con
	 */
	public void putDisc(Component com,Container con){
		disc=(Disc) com;
		con.setLayout(null);
		con.add(disc);
		int w=disc.getBounds().width;
		int h=disc.getBounds().height;
		disc.setBounds(x-w/2, y-h/2, w, h);
		haveDisc=true;
		disc.setTowerPoint(this);
		con.validate();
	}
	
	public Disc getDiscOnPoint(){
		return disc;
	}
	
	public void removeDisc(Component component,Container container){
		if(component!=null){
			container.remove(component);
		}
		container.validate();
	}
}
