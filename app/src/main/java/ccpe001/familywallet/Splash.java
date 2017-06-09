package ccpe001.familywallet;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import com.github.orangegangsters.lollipin.lib.PinCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;

import static java.lang.Thread.sleep;

/**
 * Created by harithaperera on 4/29/17.
 */
public class Splash extends PinCompatActivity {

    private Notification notification;
    private NotificationManager nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent("ccpe001.familywallet.INTRODUCTIONPAGE");
                startActivity(intent);
                overridePendingTransition(R.animator.transition1,R.animator.transition2);//for animations(shoul have 2 files)
                finish();
            }
        });
        t1.start();


        //displaing status icon
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,new Intent(),0);

        notification = new Notification.Builder(this)
                .setTicker("Ticker")
                .setContentTitle("Family Wallet")
                .setSmallIcon(R.mipmap.ic_launcher)
                .addAction(R.mipmap.email,"Scan bill",pendingIntent)
                .addAction(R.mipmap.email,"Add member",pendingIntent)
                .setContentIntent(pendingIntent).getNotification();

        nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,notification);
    }


    public void offStatusBar(){
        //notification.flags = Notification.FLAG_AUTO_CANCEL;
        //nm.cancel(0);
    }
}
