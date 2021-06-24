import javax.swing.*;
import java.util.PrimitiveIterator;

public class TimerRunnable implements Runnable{
    private int minutes;
    private int seconds;
    private JLabel Jtext;
    private JButton Jb;
    private JTextField Jt1,Jt2;
    public volatile boolean mustStope=false;
    //public volatile boolean isEnd=false;

    TimerRunnable(int minute,int seconds,JLabel Jtext,JButton Jb,JTextField Jt,JTextField Jt2){
        this.minutes=minute;
        this.seconds=seconds;
        this.Jtext=Jtext;
        this.Jb=Jb;
        this.Jt1=Jt;
        this.Jt2=Jt2;
    }
    @Override
    public void run() {
        int min=minutes,sec=seconds;
        StringBuilder text=new StringBuilder();
        while (min!=-1){
            if(min<10)
                text.append('0');
            text.append(min).append(':');
            if(sec<10)
                text.append('0').append(sec);
            else
                text.append(sec);
            //System.out.println(text);
            Jtext.setText(text.toString());
            --sec;
            text.setLength(0);
            if(sec==-1) {
                --min;
                sec=59;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (mustStope){

            }
        }
        Jtext.setText("--END--");
        Jb.setEnabled(true);
        Jt1.setEnabled(true);
        Jt2.setEnabled(true);
        Jt1.setText("");
        Jt2.setText("");

    }
}
