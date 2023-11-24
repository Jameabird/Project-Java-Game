package Thing;
import javax.swing.*;
import java.awt.event.*;

public class Obj {
    public int x,y,Size;
    public double HP,Point;
    private int jumpheight = 100;
    
    public Obj(int x, int y, int Size,double HP,double Point)
    {
      this.x = x;
      this.y = y;
      this.Size = Size;
      this.HP = HP;
      this.Point = Point;
    }

    //ทำให้ Obj. กระโดดได้ตามความสูงที่กำหนด
    public void Jump(JPanel main)
    {
        //ขาลง
        this.y -=  jumpheight;
        main.repaint();

        //ใช้ Class Timer จาก Java Swing เพื่อทำ Obj. มีเเรงโน้มถ่วงตกลงมาได้ฆ
        Timer timer = new Timer(230, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //ขาขึ้น
                y +=  jumpheight;
                main.repaint();
            }
        });//การทำงานเบื้องต้นคือ Obj. จะกลับลงมาในเวลา 230ms 
        
        //ทำให้ไม่เกิดการ loop ของ Class Timer
        timer.setRepeats(false);

        //ทำให้ timer ทำงาน
        timer.start();
    }
}
