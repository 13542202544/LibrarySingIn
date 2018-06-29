package com.library.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mobk on 2015/12/7.
 */
public class SingInModel extends JFrame {

    private JPanel panel,panel1;
    private JLabel textLabel1, textLabel2;
    private JTextField numberText;
    private JButton buttonSing;
    private JScrollPane jsp;


    public SingInModel(){

        panel = new JPanel();
        textLabel1 = new JLabel("工号:");
        numberText = new JTextField();
        numberText.setPreferredSize(new Dimension(250, 35));
        buttonSing = new JButton("上班");
        panel.add(textLabel1);
        panel.add(numberText);
        panel.add(buttonSing);
        this.add(panel, BorderLayout.NORTH);

        jsp = new JScrollPane();
        panel1 = new JPanel();
        panel1.setSize(new Dimension(500, 200));
        for(int i = 0; i < 200; i++){
            JLabel numberText1 = new JLabel("第" + i + "个/n");
            panel1.add(numberText1, BorderLayout.EAST);
        }
        panel1.setBackground(new Color(100, 5, 5));
        jsp.setViewportView(panel1);
        panel1.revalidate();
        this.add(jsp);

        //设定窗口名字
        this.setTitle("签到系统");
        //设定大小
        this.setSize(450, 300);
        //设定窗口出现的位置
        this.setLocation(400, 150);
        //设定出现窗口
        this.setVisible(true);
        //设置是否可自由调整大小
        this.setResizable(false);
        //设置关闭窗口同时关闭占用的资源
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
