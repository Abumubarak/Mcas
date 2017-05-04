package rexzen.maps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by harishananth on 20/11/16.
 */

public class IncomingSms extends BroadcastReceiver {


    // Get the object of SmsManager
    //public String details;
    public String[] array;
    public static Integer count;
    public SharedPreferences sharedpreferences;
    public static String lat,lon,regno,details;
    public char[] latitude = new char[10];
    public char[] longitude = new char[10];
    MediaPlayer mp;
    public static String firstsen,secondsen,thirdsen,fourthsen;
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            final Bundle bundle = intent.getExtras();

            try {

                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    String format = bundle.getString("format");

                    for (int i = 0; i < pdusObj.length; i++) {


                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                        String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                        String senderNum = phoneNumber;
                        String message = currentMessage.getDisplayMessageBody();

                        Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);


                        // Show Alert
                        int duration = Toast.LENGTH_LONG;
                       //if(senderNum.equals("+919176767255"))

                        {
                            if(message.contains("lat")) {
                                mp = MediaPlayer.create(context, R.raw.alert);
                                mp.start();
                            }

                            List<String> array = Arrays.asList(message.split(","));

                           lat= array.get(0).replaceAll("lat","");
                            lon=array.get(1).replaceAll("lon","");
                            regno=array.get(2);
                            details=array.get(3);

                            count=1;
                            //details=array.get(2);





                            Toast toast = Toast.makeText(context,
                                    "sender " + senderNum + ", msg " + lat+" " + lon, duration);
                            toast.show();
                            /*Intent newint=new Intent("IncomingSms");
                            newint.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            newint.putExtra("lat",33.33);
                            newint.putExtra("lon",33.33);
                            newint.putExtra("lat",Double.parseDouble(lat));
                            newint.putExtra("lon",Double.parseDouble(lon)); */
                            //newint.putExtra("details","deta");
                            //context.sendBroadcast(newint);

                            /*SharedPreferences sharedPref= context.getSharedPreferences("mypref", MODE_PRIVATE);
                            SharedPreferences.Editor editor= sharedPref.edit();
                            editor.putString("lat",lat);
                            editor.putString("lon",lon);
                            editor.putString("details",details);
                            //editor.putInt("prevdate",prevdate);
                            editor.commit();*/



                        }


                    } // end for loop
                } // bundle is null

            } catch (Exception e) {
                Log.e("SmsReceiver", "Exception smsReceiver" + e);


            }


        }
    }


}
