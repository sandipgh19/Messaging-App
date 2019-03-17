package sandip.example.com.message.notification


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.provider.Telephony
import android.support.v4.app.NotificationCompat
import android.telephony.SmsMessage
import sandip.example.com.message.MainActivity
import sandip.example.com.message.R
import kotlin.random.Random


class NotificationService : BroadcastReceiver() {

    override fun onReceive(p0: Context?, p1: Intent?) {

        if (p1?.action == Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            val bundle = p1.extras           //---get the SMS message passed in---
            var msgs: Array<SmsMessage?>? = null
            val msgFrom: String?
            if (bundle != null) {
                //---retrieve the SMS message received---
                try {
                    val pdus = bundle.get("pdus") as Array<Any>
                    msgs = arrayOfNulls(pdus.size)

                    val sb = StringBuilder()
                    for (i in msgs.indices) {
                        msgs[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray)
                        sb.append(msgs[i]?.messageBody)
                    }
                    msgFrom = msgs[0]?.originatingAddress

                    createNotification(
                        context = p0,
                        header = msgFrom,
                        message = sb.toString()
                    )

                } catch (e: Exception) {
                } catch (e: Throwable) {
                }

            }
        }
    }


    private fun createNotification(context: Context?, header: String?, message: String?) {
        // Prepare intent which is triggered if the
        // notification is selected
        val notificationIntent = Intent(context, MainActivity::class.java)
        notificationIntent.putExtra("message", message)
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val random = generateRandom()

        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val pendingIntent = PendingIntent.getActivity(context, random, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Create Notification
        val notification = NotificationCompat.Builder(context, context.getString(R.string.app_name))
            .setChannelId(context.getString(R.string.app_name))
            .setContentTitle(header)
            .setContentText(message)
            .setTicker(context.getString(R.string.app_name))
            .setSmallIcon(R.drawable.ic_forum_black_24dp)
            .setSound(notificationSound)
            .setLights(Color.RED, 3000, 3000)
            .setVibrate(longArrayOf(500, 500))
            .setWhen(System.currentTimeMillis())
            .setDefaults(Notification.DEFAULT_SOUND)
            .setPriority(Notification.PRIORITY_LOW)
            .setDefaults(Notification.DEFAULT_ALL)
            .setOngoing(true)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nameChannel = context.getString(R.string.app_name)
            val descChannel = context.getString(R.string.app_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(context.getString(R.string.app_name), nameChannel, importance)
            channel.description = descChannel
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(random, notification.build())

    }

    fun generateRandom() = Random(1000).nextInt()


}