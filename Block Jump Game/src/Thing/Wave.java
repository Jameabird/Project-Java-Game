package Thing;
import javax.swing.*;
import java.awt.event.*;

public class Wave{
    public int x_current,y,width,height,speed;
    private int x_start;

    public Wave(JPanel main,int x,int y,int w,int h,int s)
    {
     this.x_current = x;
     this.x_start = x;
     this.y = y;
     this.width = w;
     this.height = h;
     this.speed = s;
         
     //ทำให้ wave ขยับไปทางซ้าย
     Move(main);
    }

   //ทำให้ Wave ขยับได้เเละกำหนดความเร็วในการขยับ
    public void Move(JPanel main)
    {
        //ใช้ Class Timer จาก Java Swing เพื่อทำ Wave ขยับไปทางซ้ายได้
        Timer timer = new Timer(65, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //speed เยอะ x_current จะลดเร็วขึ้นตาม
                x_current -= speed;
                main.repaint();
                if(x_current < 0)
                {
                    //ทำการรีเซ็ตค่าของ x เพื่อทำให้มี Wave เรื่อยๆ
                    x_current = x_start;
                }
            }
        });//การทำงานเบื้องต้นคือ Wave จะไปทางด้านซ้ายในเวลา 65ms ต่อ 1 wave 

        //ทำให้ timer ทำงาน
        timer.start();
    }
}
