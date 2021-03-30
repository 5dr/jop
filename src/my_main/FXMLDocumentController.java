/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my_main;

import com.jfoenix.controls.JFXTextField;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author asd
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML
    private JFXTextField time_static;
   public static Timer  t;
   public static TimerTask  t1;
     
     @FXML
    private void static_ip(ActionEvent event) {
        
         try {
             double ti=Double.parseDouble(time_static.getText());
            int time=(int)(ti*1000);
           t.schedule(t1,0,time);
         } catch (Exception e) {
             System.out.println("يجب ادخال ارقام فقط");
         }
        
    }
    
    private void change_ip(int ip){
        String s_ip=String.valueOf(ip);
        String adapter_name="Wi-fi";
		String ip_address="192.168.1."+ip;
		String subnet_mask="255.255.255.0";
		String default_gateway="192.168.1.1";
		String dns_1="8.8.8.8";
		String dns_2="8.8.4.4";
        
        String[] command =
	    {
	        "cmd",
	    };
        System.out.println(ip_address);
	    Process p;
		try {
			p = Runtime.getRuntime().exec(command);
		        new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
	                new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
	                PrintWriter stdin = new PrintWriter(p.getOutputStream());
	                stdin.println("netsh int ip set address "+adapter_name+" static "+ip_address+" "+subnet_mask+" "+default_gateway);
		        stdin.println("netsh int ip set dns "+adapter_name+" static "+dns_1+" primary");
	                stdin.println("netsh interface ip add dns "+adapter_name+" "+dns_2+" INDEX=2");
                        stdin.close();
	                p.waitFor();
	    	} catch (Exception e) {
	 		e.printStackTrace();
		}
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          t =new Timer(); 
          t1=new TimerTask()
        {
            @Override
            public void run() {
                int r=(int)(20+Math.ceil(Math.random()*200));
            change_ip(r);
            System.out.println(r);      
            }
        } ;
    }    
    
}
