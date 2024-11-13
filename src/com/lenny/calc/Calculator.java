//The package name should be written in lower case to avoid conflict with class names
package com.lenny.calc;
//javax 包通常包含与 Java EE（Enterprise Edition）和其他 Java 扩展技术相关的库
//javax.swing：用于构建图形用户界面的 Swing 组件
//Contains all of the classes for creating user interfaces and for painting graphics and images.
//java.awt.event 包提供了一组用于处理 AWT 组件上的用户交互的事件和监听器。事件可以是用户的鼠标操作、键盘输入、窗口状态变化等。

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//这个类实现了 ActionListener 接口。ActionListener 是一个用于处理用户界面组件（如按钮、菜单等）动作事件的接口。实现这个接口需要重写 actionPerformed 方法。
public class Calculator implements ActionListener {
  //  instance Attributes
//  这是一个窗口（框架），是所有组件（如按钮、文本框等）的容器。它是应用程序的主窗口。
//  Jxxx 是swing里面的类
  JFrame frame;
  //  这是一个文本框，用于显示用户输入的数字和计算结果。用户输入的内容将显示在这个文本框中。
  JTextField textField;
  //  这是一个按钮数组，用于存放数字按钮（从0到9）。每个按钮代表一个数字，使用户可以通过点击按钮来输入数字。
  JButton[] numBtns = new JButton[10];
  //  这是一个按钮数组，用于存放功能按钮（如加、减、乘、除等操作按钮）。这个数组可以给定大小以容纳不同的功能按钮。
  JButton[] funcBtns = new JButton[8];
  JButton addBtn, subBtn, mulBtn, divBtn;
  JButton decBtn, equBtn, delBtn, clrBtn;
  //  JPanel panel: 这是一个面板容器，通常用于组织和布局其他组件（如按钮和文本框）。它可以帮助创建更复杂的布局
  JPanel panel;
  Font myFont = new Font("Ink Free", Font.BOLD, 30);
  double num1 = 0, num2 = 0, result = 0;
  char operator;

  //  class constructor initialising
  Calculator() {
//    创建了一个新的 JFrame 对象，命名为 frame。这个窗口的标题设置为 "Calculator"，这是用户在窗口标题栏中看到的文本。
    frame = new JFrame("Calculator");
//    设置了窗口的关闭操作。当用户点击窗口的关闭按钮时，程序将终止并退出。JFrame.EXIT_ON_CLOSE 是一个常量，表示关闭窗口时会退出整个应用程序。
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    设置了窗口的大小，宽度为 420 像素，高度为 550 像素。这决定了窗口在屏幕上占用的空间。
    frame.setSize(420, 550);
//    将窗口的布局管理器设置为 null，这意味着你将手动管理组件的位置和大小，而不是使用自动布局。这通常适用于自定义布局的情况。
    frame.setLayout(null);

    textField = new JTextField();
    textField.setBounds(50, 25, 300, 50);
    textField.setFont(myFont);
    textField.setEditable(false);
    textField.setFocusable(false);

    addBtn = new JButton("+");
    subBtn = new JButton("-");
    mulBtn = new JButton("*");
    divBtn = new JButton("/");

    decBtn = new JButton(".");
    equBtn = new JButton("=");
    delBtn = new JButton("Delete");
    clrBtn = new JButton("Clear");
    funcBtns[0] = addBtn;
    funcBtns[1] = subBtn;
    funcBtns[2] = mulBtn;
    funcBtns[3] = divBtn;
    funcBtns[4] = decBtn;
    funcBtns[5] = equBtn;
    funcBtns[6] = delBtn;
    funcBtns[7] = clrBtn;
    for (JButton funcBtn : funcBtns) {
//      当前的 funcBtn 添加一个动作监听器，this 表示当前的类实例。当按钮被点击时，当前类中的 actionPerformed 方法将被调用，处理按钮的点击事件
      funcBtn.addActionListener(this);
//      设置当前按钮的字体为 myFont
      funcBtn.setFont(myFont);
//      将按钮的焦点设置为不可聚焦。这样，按钮在使用键盘导航时不会获得焦点，通常用于避免在使用 Tab 键时意外选择按钮。
      funcBtn.setFocusable(false);
    }

    for (int i = 0; i < numBtns.length; i++) {
//      Return a string representation of different data types
//      numBtns[i] = new JButton(i + ""); //有问题 引用发生了变化 对原来的元素没有起到作用
      numBtns[i] = new JButton(String.valueOf(i));
      numBtns[i].addActionListener(this);
      numBtns[i].setFont(myFont);
      numBtns[i].setFocusable(false);
    }


    delBtn.setBounds(50, 430, 145, 50);
    clrBtn.setBounds(205, 430, 145, 50);

    panel = new JPanel();
    panel.setBounds(50, 100, 300, 300);
    panel.setLayout(new GridLayout(4, 4, 10, 10));
    panel.add(numBtns[1]);
    panel.add(numBtns[2]);
    panel.add(numBtns[3]);
    panel.add(addBtn);
    panel.add(numBtns[4]);
    panel.add(numBtns[5]);
    panel.add(numBtns[6]);
    panel.add(subBtn);
    panel.add(numBtns[7]);
    panel.add(numBtns[8]);
    panel.add(numBtns[9]);
    panel.add(mulBtn);
    panel.add(decBtn);
    panel.add(numBtns[0]);
    panel.add(equBtn);
    panel.add(divBtn);

    frame.add(panel);
    frame.add(delBtn);
    frame.add(clrBtn);
    frame.add(textField);
//    这行代码使窗口可见。true 表示窗口将被显示在屏幕上。如果这行代码没有被执行，窗口将不会出现在用户的界面中。
    frame.setVisible((true));
  }

  // 主方法
  public static void main(String[] args) {
     new Calculator();
  }

  //  这是 ActionListener 接口中必须实现的 actionPerformed 方法。每当与用户界面相关的组件发生动作事件（如按钮被点击）时，这个方法就会被调用。当前是一个空的实现，意味着当事件发生时，没有任何操作会被执行。这里通常会添加处理事件的代码，如执行计算、更新界面等。
  @Override
  public void actionPerformed(ActionEvent e) {
    for (JButton numBtn : numBtns) {
      if (e.getSource() == numBtn) {
        textField.setText(textField.getText() + numBtn.getText());
      }
    }
    if (e.getSource() == decBtn) {
      textField.setText(textField.getText() + ".");
    }
    if (e.getSource() == addBtn) {
      num1 = Double.parseDouble(textField.getText());
      operator = '+';
      textField.setText("");
    }
    if (e.getSource() == subBtn) {
      num1 = Double.parseDouble(textField.getText());
      operator = '-';
      textField.setText("");
    }
    if (e.getSource() == mulBtn) {
      num1 = Double.parseDouble(textField.getText());
      operator = '*';
      textField.setText("");
    }
    if (e.getSource() == divBtn) {
      num1 = Double.parseDouble(textField.getText());
      operator = '/';
      textField.setText("");
    }
    if (e.getSource() == equBtn) {
      num2 = Double.parseDouble(textField.getText());
      switch (operator) {
        case '+':
          result = num1 + num2;
//          在 case 块中不使用 break，程序将继续执行后面的 case 块，直到遇到 break 或 switch 结束。
          break;
        case '-':
          result = num1 - num2;
          break;
        case '*':
          result = num1 * num2;
          break;
        case '/':
          result = num1 / num2;
          break;
      }
//      int => double
      textField.setText(String.valueOf(result));
//      num1 = result;
    }
    if (e.getSource() == clrBtn) {
      textField.setText("");
      num1 = 0;
      num2 = 0;
      result = 0;
    }
    if (e.getSource() == delBtn) {
      String curStr = textField.getText();
      textField.setText(curStr.substring(0, curStr.length() - 1));
    }


  }
}
