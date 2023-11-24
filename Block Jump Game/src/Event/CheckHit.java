package Event;

import Thing.*;
public class CheckHit {
    public static boolean checkHit(Obj box,Wave wave)
    {
        //ระยะกล่องไม่พ้นระยะของ wave (เช็คเเกน X)
        //คร่าวๆ คือเช็คขอบล่างกล่อง ว่าเข้าไปอยู่ใน ขอบล่างของ wave ไหม?
       if(box.x + box.Size > wave.x_current && box.x < wave.x_current)
       {
        //ความสูงของกล่องไม่พ้นความสูงเของ wave (เช็คเเกน Y)
        //คร่าวๆ คือเช็คขอบข้างกล่อง ว่าเข้าไปอยู่ใน ขอบข้างของ wave ไหม?
        if(box.y + box.Size >= wave.y - wave.height) 
        {
            //ชน
            return true;
        }
       }
       //ไม่ชน
       return false;
    }
}
