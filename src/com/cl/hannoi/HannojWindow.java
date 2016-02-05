package com.cl.hannoi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * 主窗口
 * @author L
 * @date 2015年10月31日
 */
public class HannojWindow extends JFrame implements ActionListener{
	private Tower tower;//容器
	private int amounOfDisc=3;//定义盘子数
	private char[] towerName={'A','B','C'};//三个底座名称数组
	private JMenuBar bar;//菜单条
	private JMenu menuGrade;//菜单项
	private JMenuItem oneGradeItem,twoGradeItem,threeGradeItem;//菜单项中的选项
	private JButton renew;//重新开始按钮
	private JButton autoButton;//自动演示按钮
	
//	JPanel chenter=new JPanel();
	
	public HannojWindow() {
	//	System.out.println("HannojWindow.HannojWindow()");
		
		/**************绘画主界面*****************/
		tower=new Tower(towerName);
		tower.setAmountOfDisc(amounOfDisc);
		tower.setMaxDiscWidth(120);
		tower.setMinDiscWidth(50);
		tower.setDiscHeight(16);
		tower.putDiscOnTower();
		add(tower,BorderLayout.CENTER);
		/**************绘画菜单栏*****************/
		bar=new JMenuBar();
		menuGrade=new JMenu("选择级别");
		oneGradeItem=new JMenuItem("初级");
		twoGradeItem=new JMenuItem("中级");
		threeGradeItem=new JMenuItem("高级");
		oneGradeItem.addActionListener(this);
		twoGradeItem.addActionListener(this);
		threeGradeItem.addActionListener(this);
		
		menuGrade.add(oneGradeItem);
		menuGrade.add(twoGradeItem);
		menuGrade.add(threeGradeItem);
		bar.add(menuGrade);
		this.setJMenuBar(bar);
		/**************绘画按钮栏*****************/
		renew=new JButton("重新开始");
		autoButton=new JButton("自动演示");
		String mess="将全部盘子从"+towerName[0]+"座搬运到"+towerName[1]+"座或"+towerName[2]+"座";
		JLabel hintMess=new JLabel(mess,JLabel.CENTER);
		renew.addActionListener(this);
		autoButton.addActionListener(this);
		
		JPanel panel=new JPanel();
		panel.add(renew);
		panel.add(autoButton);
		panel.add(hintMess);
		this.add(panel,BorderLayout.NORTH);
		
		/**************构建主视图框架*****************/
		this.setResizable(false);
		this.setVisible(true);
		this.setBounds(360,120,460,410);
		this.validate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("汉罗塔小游戏");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==oneGradeItem) {
			amounOfDisc=3;
			tower.setAmountOfDisc(amounOfDisc);
			tower.putDiscOnTower();
		}else if (e.getSource()==twoGradeItem) {
			amounOfDisc=5;
			tower.setAmountOfDisc(amounOfDisc);
			tower.putDiscOnTower();
		}else if (e.getSource()==threeGradeItem) {
			amounOfDisc=8;
			tower.setAmountOfDisc(amounOfDisc);
			tower.putDiscOnTower();
		}else if(e.getSource()==renew){
			tower.setAmountOfDisc(amounOfDisc);
			tower.putDiscOnTower();
		}else if(e.getSource()==autoButton){
			tower.setAmountOfDisc(amounOfDisc);
			tower.putDiscOnTower();
			int x=this.getBounds().x+this.getBounds().width;
			int y=this.getBounds().y;
			tower.getAutoMoveDisc().setLocation(x, y);
			tower.getAutoMoveDisc().setSize(330,this.getBounds().height);
			tower.getAutoMoveDisc().setVisible(true);
		}
		validate();
	}
	
	public static void main(String[] args) {
		new HannojWindow();
	}

}
