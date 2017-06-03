/**
 * All rights Reserved, Designed By Letv
 *
 * @Title: MyFrame.java
 * @Package com.java.swing
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: liqiuwei
 * @date: 2016年11月15日 上午9:11:28
 * @version
 */
package com.java.swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author liqiuwei
 * @create time:2016年11月15日上午9:11:28
 * @Description:TODO(这里用一句话描述这个类的作用)
 */
public class MyFrame extends JFrame {
    private JTextField textField;

    public MyFrame() {
        this.setTitle("我的swing界面");
        getContentPane().setLayout(new FlowLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createTitledBorder("分组框")); // 设置面板边框，实现分组框的效果，此句代码为关键代码

        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.red));// 设置面板边框颜色

        Checkbox checkbox = new Checkbox("New check box");
        buttonPanel.add(checkbox);

        JCheckBox chckbxNewCheckBox = new JCheckBox("web");
        buttonPanel.add(chckbxNewCheckBox);
        JButton button = new JButton("我的按钮");
        buttonPanel.add(button);
        buttonPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "修改信息"));
        this.setSize(516, 300);

        Checkbox checkbox_1 = new Checkbox("New check box");
        getContentPane().add(checkbox_1);
        this.getContentPane().add(buttonPanel);

        textField = new JTextField();
        textField.setText("测试信息");
        textField.setToolTipText("<html>我最后还是选择用的mouselistener这个接口-<br>-不过还是没实现我的想法0 0</html>");
        buttonPanel.add(textField);
        textField.setColumns(10);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MyFrame myFrame = new MyFrame();
        myFrame.show();
    }
}
