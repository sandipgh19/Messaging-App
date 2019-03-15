package sandip.example.com.multibhashijokes.broadcast

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.telephony.SmsMessage
import sandip.example.com.multibhashijokes.AppController
import sandip.example.com.multibhashijokes.MainActivity
import sandip.example.com.multibhashijokes.R


class SmsListener : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if (intent.action == "android.provider.Telephony.SMS_RECEIVED") {
            val bundle = intent.extras           //---get the SMS message passed in---
            var msgs: Array<SmsMessage?>? = null
            var msgFrom: String?
            if (bundle != null) {
                //---retrieve the SMS message received---
                try {
                    val pdus = bundle.get("pdus") as Array<Any>
                    msgs = arrayOfNulls(pdus.size)
                    for (i in msgs.indices) {
                        msgs[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                        msgFrom = msgs[i]?.getOriginatingAddress()
                        val msgBody = msgs[i]?.getMessageBody()
                        val timeStamp = msgs[i]?.timestampMillis

                        createNotification(header = msgFrom, message = msgBody, timeStamp = timeStamp.toString())

                    }
                } catch (e: Exception) {
                }

            }
        }
    }

    private fun createNotification(header: String?, message: String?, timeStamp: String?) {
        // Prepare intent which is triggered if the
        // notification is selected
        val channelID = "sandip.example.com.multibhashijokes.fragments"
        val intent = Intent(AppController.instance, MainActivity::class.java)
        intent.putExtra("timestamp", timeStamp)
        val pIntent = PendingIntent.getActivity(AppController.instance, System.currentTimeMillis().toInt(), intent, 0);

        // Build notification
        val noti = Notification.Builder(AppController.instance)
            .setContentTitle(header)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_forum_black_24dp)
            .setContentIntent(pIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            noti.setChannelId(channelID)

        val notificationManager = AppController.instance.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager

        notificationManager.notify(0, noti.build());

    }
}