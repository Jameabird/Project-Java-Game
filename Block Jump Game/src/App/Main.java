package App;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import Event.CheckHit;
import Thing.*;


public class Main extends JPanel implements KeyListener 
{
    //this คือการนำ Class ของ Main ทั้งหมดไปใส่ใน Main() อีกทีนึง
    public Main()
    {   
        //ให้ Focus อยู่ในหน้าต่าง window 
        this.setFocusable(true);

        //เอา Class Main addKeyListener เพื่อรับสิ่งที่เกิดขึ้นกับ Keyboard (ข้างล่าง)
        this.addKeyListener(this);

    }

    //สร้าง Obj. box ขึ้นมาเป็นตัวกระโดด
    Obj box = new Obj(50,300,50,100.0,1.0);
    
    //กำหนดเวลาเริ่มต้นโดยเริ่มนับตั้งเเต่ 0
    long Last_Pressed = 0;

    //implements KeyListener เข้ามาเพื่อเช็คเเละจัดการสิ่งที่เกิดขึ้นกับ Keyboard
    @Override
    public void keyPressed(KeyEvent e) 
    {
        /*
        System.out.println(e.getKeyCode());
        การเขียนบรรทัดด้านบน คือการทำให้รู้ code ของตัวคีย์บอร์ด
        38 Space,32 ลูกศรชี้ขึ้น
        */

        //เเก้บัคของเกมไม่ให้กดย้ำหลายๆรอบได้ โดยเอาเวลาของระบบไปลบกับเวลาที่กดครั้งสุดท่าย
        if(System.currentTimeMillis() - Last_Pressed > 350)//คร่าวๆคือ ต้องผ่านเวลา 350ms ก่อนถึงจะกดได้อีกครั้ง
        {
            if(e.getKeyCode() == 38 || e.getKeyCode() == 32)
            {
                box.Jump(this);
            }
            Last_Pressed = System.currentTimeMillis();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
    }

    //สร้าง wave เป็นสิ่งขีดขวาง
    Wave[] waves = Muti_wave(4);
  
    //Method ที่สร้าง wave หลายๆอันเเบบสุ่มตำเเหน่งเกิดด้วย Array
    private Wave[] Muti_wave(int wave_amount)
    {
        Wave[] wave = new Wave[wave_amount];
    
        //ทำลูป ให้มี wave ตามจำนวนของบรรทัดที่ 61 โดยมีตำเเหน่งเเบบสุ่ม
        for(int i=0;i < wave_amount;i++)
        {
            double wave_coming = 1000 + Math.floor(Math.random()*1000); //วางตำเเหน่งของ wave โดยการบวกกับเลขสุ่ม
            wave[i] = new Wave(this,(int)wave_coming,300,30,40,35); 
        }
        return wave;
    }

    //คะเเนนตอนขึ้น Score ตอนจบเกม
    double Final_Point = 0;

    //๋ส่วนของ JPanel
    public void paintComponent(Graphics g)
    {
        //วาดสิ่งที่อยู่ใน Thing เเละ Background
        super.paintComponent(g);
        
        //Background
        g.setColor(Color.CYAN);
        g.fillRect(0,0, 1000, 600);

        //Tree
        g.setColor(Color.GREEN);
        g.fillRect(170, 300, 40, box.Size);
        g.fillRect(340, 300 , 40, box.Size);
        g.fillRect(520, 300 , 40, box.Size);
        g.fillRect(720, 300, 40, box.Size);
        g.fillRect(920, 300 , 40, box.Size);

        //Flower on Tree
        g.setColor(Color.RED);
        g.fillRect(180, 290, 20, box.Size-40);
        g.fillRect(350, 290 , 20, box.Size-40);
        g.fillRect(530, 290 , 20, box.Size-40);
        g.fillRect(730, 290, 20, box.Size-40);
        g.fillRect(930, 290 , 20, box.Size-40);

        //Ground
        g.setColor(Color.GRAY);
        g.fillRect(0, 350, 1000, 600);

        //Road
        g.setColor(Color.YELLOW);
        g.fillRect(100, 375, 150, 40);
        g.fillRect(400, 375, 150, 40);
        g.fillRect(670, 375, 150, 40);
        g.fillRect(900, 375, 150, 40);

        //Cloud
        g.setColor(Color.WHITE);
        g.fillRect(100, 50, 150, 30);
        g.fillRect(180, 60, 150, 30);
        g.fillRect(500, 80, 150, 30);
        g.fillRect(600, 50, 150, 40);
        g.fillRect(900, 50, 150, 40);
        
        //Sun
        g.setColor(Color.ORANGE);
        g.fillRect(375, 50, 80, 80);

        //สิ่งขีดขวาง 
        g.setColor(Color.PINK);

        //เป็น Loop ที่สร้าง wave ตามจำนวนที่ใส่ใว้
        for(Wave wave : waves)
        {
            g.fillRect(wave.x_current, wave.y, wave.width, wave.height);

            if(CheckHit.checkHit(box,wave) == true)//ชน
            {
              box.HP -= 1.0;
              if(box.HP <= 0)
              {
                box.HP = 0.0;
              }
            }
            else if(CheckHit.checkHit(box,wave) == false)//ไม่ชน
            {
                box.Point += 1.0;
                if(box.HP == 0.0)
                {
                  box.Point = Final_Point;
                }
                Final_Point = box.Point;//คะเเนนล่าสุด
            } 
        } 

        //Obj.
        g.setColor(Color.YELLOW);
        g.fillRect(box.x, box.y, box.Size, box.Size);
        g.setColor(Color.BLACK); 
        g.fillRect(box.x + 10, box.y + 10 , 5, 5);
        g.fillRect(box.x + 35, box.y + 10 , 5, 5);
        g.fillRect(box.x + 20, box.y + 30, 10, 10);
        g.setColor(Color.RED);
        g.fillRect(box.x + 22, box.y + 34, 6 ,6);

       //สร้าง หลอด HP Score เเละกำหนดตำเเหน่ง
        g.setColor(Color.RED);
        g.drawString("HP : " + box.HP + "%", 40, 40);
        g.drawString("Score : " + box.Point/100 , 900, 40);

        //หน้าตอนจบเกม
        if(box.HP == 0)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, 1000, 600);

            g.setColor(Color.WHITE);
            g.drawString("YOU DIED!" ,440, 225);
            g.drawString("Game Over" ,440, 250);
            g.drawString("Score : " + Final_Point/100 , 435, 300);
        } 
    }  
}