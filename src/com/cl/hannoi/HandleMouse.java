package com.cl.hannoi;

import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 盘子对象disc的鼠标事件监听对象
 * 
 * @author 陈雷
 * @date 2015年10月31日
 */
public class HandleMouse implements MouseListener, MouseMotionListener {
	private TowerPoint[] pointA, pointB, pointC;
	TowerPoint startPoint = null, endPoint = null;
	int leftX, leftY, x0, y0;
	boolean move = false;
	Container container;

	public HandleMouse(Container container) {
		this.container = container;
	}

	public void setPointA(TowerPoint[] pointA) {
		this.pointA = pointA;
	}

	public void setPointB(TowerPoint[] pointB) {
		this.pointB = pointB;
	}

	public void setPointC(TowerPoint[] pointC) {
		this.pointC = pointC;
	}

	/**
	 * 按压鼠标时
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		move = false;
		Disc disc = null;
		disc = (Disc) e.getSource();
		startPoint = disc.getTowerPoint();
		x0 = e.getX();
		y0 = e.getY();
		int m = 0;
		// 判断是否可移动
		for (int i = 0; i < pointA.length; i++) {
			if (pointA[i].equals(startPoint)) {
				m = i;
				if (m == 0) {
					move = true;
					break;
				} else if (m > 0 && (pointA[m - 1].isHaveDisc() == false)) {
					move = true;
					break;
				}
			}
		}
		for (int i = 0; i < pointB.length; i++) {
			if (pointB[i].equals(startPoint)) {
				m = i;
				if (m == 0) {
					move = true;
					break;
				} else if (m > 0 && (pointB[m - 1].isHaveDisc() == false)) {
					move = true;
					break;
				}
			}
		}
		for (int i = 0; i < pointC.length; i++) {
			if (pointC[i].equals(startPoint)) {
				m = i;
				if (m == 0) {
					move = true;
					break;
				} else if (m > 0 && (pointC[m - 1].isHaveDisc() == false)) {
					move = true;
					break;
				}
			}
		}
	}

	/**
	 * 拖动鼠标时
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (move == true) {
			Disc disc = (Disc) e.getSource();
			leftX = disc.getBounds().x + e.getX();// 按钮的横坐标+鼠标相对于按钮左上角的位置
			leftY = disc.getBounds().y + e.getY();
			disc.setLocation(leftX - x0, leftY - y0);
		}
	}

	/**
	 * 放开鼠标时
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		Disc disc = (Disc) e.getSource();
		Rectangle rectangle = disc.getBounds();
		// rectangle.setSize(rectangle.width,
		// rectangle.height+disc.getHeight()*pointA.length);
		boolean location = false;
		int x = -1, y = -1;
		for (int i = 0; i < pointA.length; i++) {
			x = pointA[i].getX();
			y = pointA[0].getY();
			if(rectangle.intersects(new Rectangle(x, y-disc.getHeight()/2, 1,disc.getHeight()*pointA.length))){
				endPoint = pointA[i];
				if (i == pointA.length - 1 && endPoint.isHaveDisc() == false) {// 如果是最后一个节点，并且节点上没有盘子
					location = true;
					break;
				} else if (i < pointA.length - 1 && pointA[i + 1].isHaveDisc() == true && endPoint.isHaveDisc() == false// 如果不是最后一个节点，并且该节点下的节点必须有盘子，
						&& pointA[i + 1].getDiscOnPoint().getNumber() > disc.getNumber()) {// 并且下面那个盘子的number必须大于该盘子
					location = true;
					break;
				}

			}
		}
		for (int i = 0; i < pointB.length; i++) {
			x = pointB[i].getX();
			y = pointB[0].getY();
			if(rectangle.intersects(new Rectangle(x, y-disc.getHeight()/2, 1, disc.getHeight()*pointA.length))){
				endPoint = pointB[i];
				if (i == pointB.length - 1 && endPoint.isHaveDisc() == false) {// 如果是最后一个节点，并且节点上没有盘子
					location = true;
					break;
				} else if (i < pointB.length - 1 && pointB[i + 1].isHaveDisc() == true && endPoint.isHaveDisc() == false// 如果不是最后一个节点，并且该节点下的节点必须有盘子，
						&& pointB[i + 1].getDiscOnPoint().getNumber() > disc.getNumber()) {// 并且下面那个盘子的number必须大于该盘子
					location = true;
					break;
				}

			}
		}
		for (int i = 0; i < pointC.length; i++) {
			x = pointC[i].getX();
			y = pointC[0].getY();
			if(rectangle.intersects(new Rectangle(x, y-disc.getHeight()/2, 1, disc.getHeight()*pointA.length))){
				endPoint = pointC[i];
				if (i == pointC.length - 1 && endPoint.isHaveDisc() == false) {// 如果是最后一个节点，并且节点上没有盘子
					location = true;
					break;
				} else if (i < pointC.length - 1 && pointC[i + 1].isHaveDisc() == true && endPoint.isHaveDisc() == false// 如果不是最后一个节点，并且该节点下的节点必须有盘子，
						&& pointC[i + 1].getDiscOnPoint().getNumber() > disc.getNumber()) {// 并且下面那个盘子的number必须大于该盘子
					location = true;
					break;
				}

			}
		}
		if (endPoint != null && location == true) {
			endPoint.putDisc(disc, container);
			startPoint.setHaveDisc(false);
		} else {
			startPoint.putDisc(disc, container);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
