package ccpe001.familywallet;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import ccpe001.familywallet.admin.SignIn;
import ccpe001.familywallet.admin.SignUp;
import com.github.orangegangsters.lollipin.lib.PinActivity;

import java.util.Calendar;

import static java.lang.Thread.sleep;

/**
 * Created by harithaperera on 4/29/17.
 */
public class Splash extends PinActivity {

    private Notification.Builder notification;
    private NotificationManager nm;
    private final static int PERMENT_NOT = 0;

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

        notifyMe();

        //displaing status icon
        PendingIntent scanBill = PendingIntent.getActivity(this,PERMENT_NOT,new Intent(this,SignIn.class),PERMENT_NOT);//update here
        PendingIntent addMem = PendingIntent.getActivity(this,PERMENT_NOT,new Intent(this,SignUp.class),PERMENT_NOT);//update here


        notification = new Notification.Builder(this)
                .setContentTitle("Family Wallet")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(Notification.PRIORITY_MIN)
                .setOngoing(true)
                .addAction(R.mipmap.email,"Scan bill",scanBill)
                .addAction(R.mipmap.email,"Add member",addMem);

        nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(PERMENT_NOT,notification.build());
    }


    public void offStatusBar(){
        //notification.flags = Notification.FLAG_AUTO_CANCEL;
        //nm.cancel(PERMENT_NOT);
        if(Context.NOTIFICATION_SERVICE != null){
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(PERMENT_NOT);
        }
    }

    public void notifyMe(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,10);
        calendar.set(Calendar.MINUTE,49);
        calendar.set(Calendar.SECOND,00);
        Intent intent = new Intent(getApplicationContext(),Notification_Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }

    public static class Notification_Receiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("df","dfdf");

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Intent newAct = new Intent(context,Dashboard.class);
            newAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,100,newAct,PendingIntent.FLAG_UPDATE_CURRENT);

            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                    .setContentIntent(pendingIntent)
                    .setSound(soundUri)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setTicker("Daily reminder")
                    .setContentTitle("Family Wallet")
                    .setContentText("Add your daily transactions to the wallet..");
            notificationManager.notify(100,builder.build());
        }

    }
}
