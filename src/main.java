import com.sun.source.tree.WhileLoopTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
    static JFrame ff;
    static JTextField tfs;
    static JTextField tfm;
    static JLabel jLabel,jLabel1,text;
    static JLabel jLabm,jLabs;
    static JButton bStart, bStop;
    static Thread thread;
    static TimerRunnable timerRunnable;
    public static void main(String[] args) {
        ff=new JFrame("Timer");
        tfs=new JTextField();
        tfm=new JTextField();
        jLabel=new JLabel("Welcome");
        jLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        jLabel1=new JLabel("Please Type Minutes And Seconds");
        jLabel1.setBounds(105,40,250,30);
        jLabel.setBounds(165,20,100,30);
        tfm.setBounds(120,70, 80,20);
        tfs.setBounds(220,70, 80,20);
        jLabm=new JLabel("minutes");
        jLabs=new JLabel("seconds");
        jLabm.setBounds(130,80,250,30);
        jLabs.setBounds(230,80,250,30);
        bStart=new JButton("Start");
        bStop=new JButton("Stop");
        bStart.setBounds(155,110,95,30);
        bStop.setBounds(155,140,95,30);
        text=new JLabel("00:00");
        text.setFont(new Font("Serif", Font.PLAIN, 25));
        text.setBounds(170,180,95,30);
        bStart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startF();
            }
        });

        bStop.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                stopF();
            }
        });
        ff.add(jLabel);
        ff.add(jLabel1);
        ff.add(bStart);ff.add(tfs);ff.add(tfm);
        ff.add(jLabm);
        ff.add(jLabs);
        ff.add(bStop);
        ff.add(text);
        ff.setSize(400,400);
        ff.setLayout(null);
        ff.setVisible(true);
        bStop.setEnabled(false);


    }

    static void  startF(){
        tfm.setEnabled(false);
        tfs.setEnabled(false);
        bStop.setEnabled(true);
        String tt=bStart.getText();
        if(tt.equals("Continue")){
            timerRunnable.mustStope=false;
            bStart.setText("Start");
            bStart.setEnabled(false);
            bStop.setEnabled(true);
        }
        else {
            try {
                int minutes = Integer.parseInt(tfm.getText());
                System.out.println(minutes);
                int second = Integer.parseInt(tfs.getText());
                if(minutes<0|| second>59||second<0){
                    throw new Exception("Wrong numbers");
                }
                System.out.println(second);
                timerRunnable = new TimerRunnable(minutes, second, text,bStart,tfs,tfm);
                thread = new Thread(timerRunnable);
                thread.start();
                bStart.setEnabled(false);

            } catch (Exception e) {
                System.out.println("Something is wrong");
                e.printStackTrace();
                ff.setVisible(false);
                ff.dispose();
            }
        }

    }

    static void  stopF() {
        bStart.setText("Continue");
        bStart.setEnabled(true);
        timerRunnable.mustStope=true;
        bStop.setEnabled(false);
    }
}
