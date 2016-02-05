package com.cl.hannoi;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 容器
 * 
 * @author L
 * @date 2015年10月30日
 */
public class Tower extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private int amountOfDisc=3;// 定义盘子数
	private Disc[] discs;// 该数组长度与盘子数相同，用来表明tower对象中有怎样多的盘子
	private int maxDiscWidth, minDiscWidth, discHeight;
	private char[] towerName;// 塔座名称
	private TowerPoint[] pointA, pointB, pointC;// 每个塔座的塔点
	private HandleMouse handleMouse;// 盘子对象的鼠标事件监听器
	private AutoMoveDisc autoMoveDisc;// 自动移动盘子方法

	/**
	 * 创建容器
	 * 
	 * @author L
	 * @date 2015年10月30日
	 * @param towerName
	 *            容器名称
	 */
	public Tower(char[] towerName) {
		handleMouse = new HandleMouse(this);
		this.towerName = towerName;
		setLayout(null);
		setBackground(new Color(200, 226, 226));
	}

	/**
	 * 设定盘子数
	 * 
	 * @author L
	 * @date 2015年10月31日
	 * @param number
	 */
	public void setAmountOfDisc(int number) {
		if (number <= 1) {
			amountOfDisc = 1;
		} else {
			amountOfDisc = number;
		}
	}

	/**
	 * 设置盘子最大宽度
	 * 
	 * @author L
	 * @date 2015年10月31日
	 * @param maxDiscWidth
	 */
	public void setMaxDiscWidth(int maxDiscWidth) {
		this.maxDiscWidth = maxDiscWidth;
	}

	/**
	 * 设置盘子最小宽度
	 * 
	 * @author L
	 * @date 2015年10月31日
	 * @param minDiscWidth
	 */
	public void setMinDiscWidth(int minDiscWidth) {
		this.minDiscWidth = minDiscWidth;
	}

	/**
	 * 设置盘子高度
	 * 
	 * @author L
	 * @date 2015年10月31日
	 * @param discHeight
	 */
	public void setDiscHeight(int discHeight) {
		this.discHeight = discHeight;
	}

	/**
	 * 得到自动移动盘子方法
	 * 
	 * @author L
	 * @date 2015年10月31日
	 * @return
	 */
	public AutoMoveDisc getAutoMoveDisc() {
		return autoMoveDisc;
	}

	/**
	 * 重新摆放盘子
	 * @author L
	 * @date 2015年10月31日
	 */
	private void removeDisk() {
		if (pointA != null) {
			for (int i = 0; i < pointA.length; i++) {
				pointA[i].removeDisc(pointA[i].getDiscOnPoint(), this);
				pointB[i].removeDisc(pointB[i].getDiscOnPoint(), this);
				pointC[i].removeDisc(pointC[i].getDiscOnPoint(), this);
			}
		}
	}

	/**
	 * 设置3个塔的节点，初始化盘子，摆放盘子到搭上
	 * @author L
	 * @date 2015年10月31日
	 */
	public void putDiscOnTower() {
		removeDisk();
		//初始化3个塔的节点
		pointA = new TowerPoint[amountOfDisc];
		pointB = new TowerPoint[amountOfDisc];
		pointC = new TowerPoint[amountOfDisc];

		//设置3个塔的节点的坐标
		int vertialDistance = discHeight;
		for (int i = 0; i < pointA.length; i++) {
			pointA[i] = new TowerPoint(maxDiscWidth, 100 + vertialDistance);
			vertialDistance = vertialDistance + discHeight;
		}
		
		vertialDistance = discHeight;
		for (int i = 0; i < pointB.length; i++) {
			pointB[i] = new TowerPoint(2 * maxDiscWidth, 100 + vertialDistance);
			vertialDistance = vertialDistance + discHeight;
		}

		vertialDistance = discHeight;
		for (int i = 0; i < pointC.length; i++) {
			pointC[i] = new TowerPoint(3 * maxDiscWidth, 100 + vertialDistance);
			vertialDistance = vertialDistance + discHeight;
		}
		
		//初始化盘子
		int n = (maxDiscWidth - minDiscWidth) / amountOfDisc;// 相邻盘子的宽度差
		discs = new Disc[amountOfDisc];
		for (int i = 0; i < discs.length; i++) {
			discs[i] = new Disc();
			discs[i].setNumber(i);
			int diskwidth = minDiscWidth + i * n;
			discs[i].setSize(diskwidth, discHeight);
			discs[i].addMouseListener(handleMouse);
			discs[i].addMouseMotionListener(handleMouse);
			discs[i].setText(i+1+"");
		}

		//在A座上摆放盘子
		for (int i = 0; i < pointA.length; i++) {
			pointA[i].putDisc(discs[i], this);
		}

		handleMouse.setPointA(pointA);
		handleMouse.setPointB(pointB);
		handleMouse.setPointC(pointC);
		
		autoMoveDisc=new AutoMoveDisc(this);
		autoMoveDisc.setTowerName(towerName);
		autoMoveDisc.setAmountOfDisc(amountOfDisc);
		autoMoveDisc.setPointA(pointA);
		autoMoveDisc.setPointB(pointB);
		autoMoveDisc.setPointC(pointC);
		
		validate();
		repaint();
	}

	/**
	 * 绘制出主要界面
	 */
	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		int x1, y1, x2, y2;

		//画出3个塔的节点对应的线
		x1 = pointA[0].getX();		//第一个节点的x
		y1 = pointA[0].getY() - discHeight / 2;		//第一个节点的y-盘子的高度的1/2
		x2 = pointA[amountOfDisc - 1].getX();		//最后一个盘子的x
		y2 = pointA[amountOfDisc - 1].getY() + discHeight / 2;		//最后一个盘子的y+盘子的高度的1/2
		graphics.drawLine(x1, y1, x2, y2);
		//B塔
		x1 = pointB[0].getX();
		y1 = pointB[0].getY() - discHeight / 2;
		x2 = pointB[amountOfDisc - 1].getX();
		y2 = pointB[amountOfDisc - 1].getY() + discHeight / 2;
		graphics.drawLine(x1, y1, x2, y2);
		//C塔
		x1 = pointC[0].getX();
		y1 = pointC[0].getY() - discHeight / 2;
		x2 = pointC[amountOfDisc - 1].getX();
		y2 = pointC[amountOfDisc - 1].getY() + discHeight / 2;
		graphics.drawLine(x1, y1, x2, y2);
		
		graphics.setColor(Color.black);

		//画出底部
		x1 = pointA[amountOfDisc - 1].getX() - maxDiscWidth / 2;	// A塔最后一个节点的x-最大盘子宽度的1/2
		y1 = pointA[amountOfDisc - 1].getY() + discHeight / 2;		// A塔最后一个节点的y+盘子高度的1/2
		x2 = pointC[amountOfDisc - 1].getX() + maxDiscWidth / 2;	// C塔最后一个节点的x+最大盘子宽度的1/2
		y2 = pointC[amountOfDisc - 1].getY() + discHeight / 2;		// C塔最后一个节点的y+盘子高度的1/2
		int length = x2 - x1, height = 5;
		graphics.fillRect(x1, y1, length, height);
		
		//在节点处画个点
//		int size = 5;
//		for (int i = 0; i < pointA.length; i++) {
//			graphics.fillOval(pointA[i].getX() - size / 2, pointA[i].getY() - size / 2, size, size);
//			graphics.fillOval(pointB[i].getX() - size / 2, pointB[i].getY() - size / 2, size, size);
//			graphics.fillOval(pointC[i].getX() - size / 2, pointC[i].getY() - size / 2, size, size);
//		}
		//塔名称
		graphics.drawString(towerName[0] + "座", pointA[amountOfDisc - 1].getX(), pointA[amountOfDisc - 1].getY() + 30);
		graphics.drawString(towerName[1] + "座", pointB[amountOfDisc - 1].getX(), pointB[amountOfDisc - 1].getY() + 30);
		graphics.drawString(towerName[2] + "座", pointC[amountOfDisc - 1].getX(), pointC[amountOfDisc - 1].getY() + 30);
	}

}
