package com.cl.hannoi;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * 自动移动盘子类
 * @author 陈雷
 * @date 2015年10月31日
 */
public class AutoMoveDisc extends JDialog implements ActionListener {
	int amountOfDisc = 3;
	private TowerPoint[] pointA, pointB, pointC;
	private char[] towerName;// 三个底座名称数组
	Container container;
	StringBuffer moveStep;
	JTextArea showStep;
	JButton bStart, bStop, bContinue, bClose;
	Timer timer;
	int i = 0, number = 0;

	public AutoMoveDisc(Container container) {
		setModal(true);
		
		this.container = container;
		moveStep = new StringBuffer();
		timer = new Timer(1000, this);
		timer.setInitialDelay(10);
		showStep = new JTextArea(10, 12);
		bStart = new JButton("开始");
		bStop = new JButton("暂停");
		bContinue = new JButton("继续");
		bClose = new JButton("关闭");

		bStart.addActionListener(this);
		bStop.addActionListener(this);
		bContinue.addActionListener(this);
		bClose.addActionListener(this);

		JPanel south = new JPanel();
		south.setLayout(new FlowLayout());
		south.add(bStart);
		south.add(bStop);
		south.add(bContinue);
		south.add(bClose);

		add(new JScrollPane(showStep), BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("自动演示搬盘子过程");

		towerName = new char[3];
		addWindowFocusListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				timer.stop();
				setVisible(false);
			}
		});
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

	public void setTowerName(char[] towerName) {
		if (towerName[0] == towerName[1] || towerName[0] == towerName[2] || towerName[1] == towerName[2]) {
			this.towerName[0] = 'A';
			this.towerName[0] = 'B';
			this.towerName[0] = 'C';
		} else {
			this.towerName = towerName;
		}
	}

	public void setAmountOfDisc(int amountOfDisc) {
		this.amountOfDisc = amountOfDisc;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			number++;
			char cStart, cEnd;
			if (i < moveStep.length() - 2) {
				cStart = moveStep.charAt(i);
				cEnd = moveStep.charAt(i + 1);
				showStep.append("(" + number + ")从" + cStart + "座搬一个盘子到" + cEnd + "座\n");
				autoMoveDisc(cStart, cEnd);
			}
			i = i + 2;
			if (i > moveStep.length() - 1) {
				timer.stop();
			}
		} else if (e.getSource() == bStart) {
			if (moveStep.length() == 0) {
				if (timer.isRunning() == false) {
					i = 0;
					moveStep = new StringBuffer();
					setMoveStep(amountOfDisc, towerName[0], towerName[1], towerName[2]);
					number = 0;
					timer.start();
				}
			}
		} else if (e.getSource() == bStop) {
			if (timer.isRunning() == true) {
				timer.stop();
			}
		} else if (e.getSource() == bContinue) {
			if (timer.isRunning() == false) {
				timer.restart();
			}
		} else if (e.getSource() == bClose) {
			timer.stop();
			setVisible(false);
		}
	}

	private void setMoveStep(int amountOfDisc, char one, char two, char three) {
		if (amountOfDisc == 1) {
			moveStep.append(one);
			moveStep.append(three);
		} else {
			setMoveStep(amountOfDisc - 1, one, three, two);
			moveStep.append(one);
			moveStep.append(three);
			setMoveStep(amountOfDisc - 1, two, one, three);
		}
	}

	private void autoMoveDisc(char cStart, char cEnd) {
		Disc disc = null;
		if (cStart == towerName[0]) {
			for (int i = 0; i < pointA.length; i++) {
				if (pointA[i].isHaveDisc() == true) {
					disc = pointA[i].getDiscOnPoint();
					pointA[i].setHaveDisc(false);
					break;
				}
			}
		}
		if (cStart == towerName[1]) {
			for (int i = 0; i < pointB.length; i++) {
				if (pointB[i].isHaveDisc() == true) {
					disc = pointB[i].getDiscOnPoint();
					pointB[i].setHaveDisc(false);
					break;
				}
			}
		}
		if (cStart == towerName[2]) {
			for (int i = 0; i < pointC.length; i++) {
				if (pointC[i].isHaveDisc() == true) {
					disc = pointC[i].getDiscOnPoint();
					pointC[i].setHaveDisc(false);
					break;
				}
			}
		}

		TowerPoint endPoint = null;
		int i = 0;
		if (cEnd == towerName[0]) {
			for (i = 0; i < pointA.length; i++) {
				if (pointA[i].isHaveDisc() == true) {
					if (i > 0) {
						endPoint = pointA[i - 1];
						break;
					} else if (i == 0) {
						break;
					}
				}
			}
			if (i == pointA.length) {
				endPoint = pointA[pointA.length - 1];
			}
		}
		if (cEnd == towerName[1]) {
			for (i = 0; i < pointB.length; i++) {
				if (pointB[i].isHaveDisc() == true) {
					if (i > 0) {
						endPoint = pointB[i - 1];
						break;
					} else if (i == 0) {
						break;
					}
				}
			}
			if (i == pointB.length) {
				endPoint = pointB[pointB.length - 1];
			}
		}
		if (cEnd == towerName[2]) {
			for (i = 0; i < pointC.length; i++) {
				if (pointC[i].isHaveDisc() == true) {
					if (i > 0) {
						endPoint = pointC[i - 1];
						break;
					} else if (i == 0) {
						break;
					}
				}
			}
			if (i == pointC.length) {
				endPoint = pointC[pointC.length - 1];
			}
		}
		if (endPoint != null && disc != null) {
			endPoint.putDisc(disc, container);
			endPoint.setHaveDisc(true);
		}
	}

}
