package com.cl.hannoi;

import java.awt.Color;

import javax.swing.JButton;

/**
 * 盘子对象
 * 
 * @author 陈雷
 * @date 2015年10月31日
 */
public class Disc extends JButton {
	private static final long serialVersionUID = 1L;
	
	private int number;// 盘子编号
	private TowerPoint towerPoint;// 盘子所在的塔点

	public Disc() {
		setBackground(Color.cyan);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public TowerPoint getTowerPoint() {
		return towerPoint;
	}

	public void setTowerPoint(TowerPoint towerPoint) {
		this.towerPoint = towerPoint;
	}

}
