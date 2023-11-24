package App;

import javax.swing.*;
public class Game extends JFrame{
    public static void main(String[] args) 
    { 
        Main panel = new Main();
        JFrame frame = new JFrame("Block Jump Game Version 1.0.0");

        //สร้าง Jframe ตั้งชื่อ ตั้งขนาด 
         frame.setSize(1000,600);
         frame.setLocation(400, 200);
         frame.setVisible(true);
         frame.setResizable(false);
         frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
         
        //นำ Class Main มาใส่ใน Class Game(frame) เพื่อเเสดงผล
        frame.add(panel);
    }
}
