package ccpe001.familywallet.admin;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import ccpe001.familywallet.Dashboard;
import ccpe001.familywallet.R;
import ccpe001.familywallet.Splash;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by harithaperera on 7/9/17.
 */
public class Notification {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private android.app.Notification.Builder notification;
    private NotificationManager nm;
    private final static int PERMENT_NOT = 0;

    public void dailyReminder(Context c){
        prefs = c.getSharedPreferences("App Settings", c.MODE_PRIVATE);

        String remTime = prefs.getString("appDailyRem", "09:00");

        String[] arr = remTime.split(":");
        Log.d("df","dfdf"+Integer.parseInt(arr[0])+Integer.parseInt(arr[1]));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(arr[0]));
        calendar.set(Calendar.MINUTE,Integer.parseInt(arr[1]));
        calendar.set(Calendar.SECOND,00);
        Intent intent = new Intent(c,Notification_Receiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(c,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) c.getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
    }

    public void statusIcon(Context c){
        //displaing status icon
        PendingIntent scanBill = PendingIntent.getActivity(c,PERMENT_NOT,new Intent(c,SignIn.class),PERMENT_NOT);//update here
        PendingIntent addMem = PendingIntent.getActivity(c,PERMENT_NOT,new Intent(c,SignUp.class),PERMENT_NOT);//update here
        notification = new android.app.Notification.Builder(c)
                .setContentTitle("Family Wallet")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(android.app.Notification.PRIORITY_MIN)
                .setOngoing(true)
                .addAction(R.mipmap.email,"Scan bill",scanBill)
                .addAction(R.mipmap.email,"Add member",addMem);

        nm = (NotificationManager)c.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(PERMENT_NOT,notification.build());
    }



    public static class Notification_Receiver extends BroadcastReceiver {
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
                    .setAutoCancel(true)
                    .setTicker("Daily reminder")
                    .setContentTitle("Family Wallet")
                    .setContentText("Add your daily transactions to the wallet..");
            notificationManager.notify(100,builder.build());
        }
    }
}
