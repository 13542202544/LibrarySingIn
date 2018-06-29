package com.library.UI;

import com.library.tool.HibernateSpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by mobk on 2015/12/7.
 */
public class SingInModel2 extends JFrame {

    private HibernateSpring hibernateSpring;
    private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem inportEmployeeItem,outputSingInItem,outputModelItem,inportWorkItem;
    private JPanel topPanel,middlePanel,centerPanel;
    private JLabel textLabel1,textLabel2,textLabel3;
    private JTextField jobIDText,stuIDText,noteText;
    private JButton buttonStart,buttonEnd;
    private JComboBox jcb;


    public SingInModel2(){
        hibernateSpring = new HibernateSpring();

        menubar = new JMenuBar();
        menu = new JMenu("菜单");
        inportEmployeeItem = new JMenuItem("导入员工表");
        inportEmployeeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //选择框
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc.showDialog(new JLabel(), "选择文件");
                File file = jfc.getSelectedFile();
                if (file != null) {
                    //文件绝对路径
                    hibernateSpring.inportEmployee(file.getAbsolutePath());
                }
            }
        });
        menu.add(inportEmployeeItem);
        
        outputSingInItem = new JMenuItem("导出签到表");
        outputSingInItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.showDialog(new JLabel(), "选择文件夹");
                File file = jfc.getSelectedFile();
                if (file != null) {
                    //文件绝对路径
                    hibernateSpring.outputSingIn(file.getAbsolutePath() + File.separator, "签到表.xls");
                }
            }
        });
        menu.add(outputSingInItem);
        
        outputModelItem = new JMenuItem("导出值班表模板");
        outputModelItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.showDialog(new JLabel(), "选择文件夹");
                File file = jfc.getSelectedFile();
                if (file != null) {
                    //文件绝对路径
                    hibernateSpring.outputModelWork(file.getAbsolutePath());
                }
            }
        });
        menu.add(outputModelItem);
        
        inportWorkItem = new JMenuItem("导入值班表");
        inportWorkItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc.showDialog(new JLabel(), "选择文件");
                File file = jfc.getSelectedFile();
                if (file != null) {
                    //文件绝对路径
                    hibernateSpring.importWork(file.getAbsolutePath());
                }
            }
        });
        menu.add(inportWorkItem);
        
        menubar.add(menu);
        this.setJMenuBar(menubar);

        topPanel = new JPanel();
        textLabel1 = new JLabel("工号:");
        topPanel.add(textLabel1);
        jobIDText = new JTextField();
        jobIDText.setPreferredSize(new Dimension(100, 25));
        topPanel.add(jobIDText);
        textLabel2 = new JLabel("学号:");
        topPanel.add(textLabel2);
        stuIDText = new JTextField();
        stuIDText.setPreferredSize(new Dimension(100, 25));
        topPanel.add(stuIDText);
        this.add(topPanel, BorderLayout.NORTH);

        centerPanel = new JPanel();
        textLabel3 = new JLabel("备注:");
        noteText = new JTextField();
        noteText.setPreferredSize(new Dimension(240, 25));
        centerPanel.add(textLabel3);
        centerPanel.add(noteText);
        this.add(centerPanel);

        middlePanel = new JPanel();
        String jg[] = {"请选择工作内容","整架","大厅值班","书库值班","书吧值班"};
        jcb=new JComboBox(jg);
        middlePanel.add(jcb);
        buttonStart = new JButton("上班");
        buttonStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jobIDText.getText().length() == 8 && stuIDText.getText().length() == 11) {
                    try {
                        Integer.parseInt(jobIDText.getText());
                        Double.parseDouble(stuIDText.getText());
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "请输入正确的8位数字工号和11位学号!", "系统信息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (jcb.getSelectedItem().equals("请选择工作内容") || jcb.getSelectedItem().equals("") || jcb.getSelectedItem() == null) {
                        JOptionPane.showMessageDialog(null, "请选择工作内容!!!!", "系统信息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    String s = hibernateSpring.startTime(jobIDText.getText(), stuIDText.getText(), (String) jcb.getSelectedItem(), "");
                    if (s.equals("签到成功!")) {
                        JOptionPane.showMessageDialog(null, s, "系统信息", JOptionPane.WARNING_MESSAGE);
                        jobIDText.setText("");
                        stuIDText.setText("");
                        jcb.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, s, "系统信息", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请输入正确的8位数字工号和11位学号!", "系统信息", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        buttonEnd = new JButton("下班");
        buttonEnd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jobIDText.getText().length() == 8 && stuIDText.getText().length() == 11) {
                    try {
                        Integer.parseInt(jobIDText.getText());
                        Double.parseDouble(stuIDText.getText());
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "请输入正确的8位数字工号和11位学号!", "系统信息", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    String s = hibernateSpring.endTime(jobIDText.getText(), stuIDText.getText(), "");
                    if (s.substring(0, 3).equals("谢谢!")) {
                        JOptionPane.showMessageDialog(null, s, "系统信息", JOptionPane.WARNING_MESSAGE);
                        jobIDText.setText("");
                        stuIDText.setText("");
                        jcb.setSelectedIndex(0);
                    } else {
                        JOptionPane.showMessageDialog(null, s, "系统信息", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请输入正确的8位数字工号和11位学号!", "系统信息", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        middlePanel.add(buttonStart);
        middlePanel.add(buttonEnd);
        this.add(middlePanel, BorderLayout.SOUTH);

        //设定窗口名字
        this.setTitle("签到系统");
        //设定大小
        this.setSize(300, 160);
        //获取当前分辨率
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        //设定窗口出现的位置
        this.setLocation((dimension.width-300)/2, (dimension.height-160)/2);
        //设定出现窗口
        this.setVisible(true);
        //设置是否可自由调整大小
        this.setResizable(false);
        //设置关闭窗口同时关闭占用的资源
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
