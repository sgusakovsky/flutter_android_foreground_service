package dk.cachet.flutter_android_foreground_service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.ServiceCompat
import dk.cachet.flutter_android_foreground_service.NotificationUtils.Companion.FOREGROUND_SERVICE_NOTIFICATION_ID
import dk.cachet.flutter_android_foreground_service.NotificationUtils.Companion.FOREGROUND_SERVICE_TEXT
import dk.cachet.flutter_android_foreground_service.NotificationUtils.Companion.FOREGROUND_SERVICE_TITLE

class MediaForegroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        var title: String? = null
        var text: String? = null
        intent?.apply {
            title = getStringExtra(FOREGROUND_SERVICE_TITLE)
            text = getStringExtra(FOREGROUND_SERVICE_TEXT)
        }
        startForegroundService(title, text)
        return START_STICKY
    }

    private fun startForegroundService(title: String?, text: String?) {
        val notification = NotificationUtils(this).getForegroundServiceNotification(title, text)
        startForeground(FOREGROUND_SERVICE_NOTIFICATION_ID, notification)
    }

    override fun onDestroy() {
        super.onDestroy()
        ServiceCompat.stopForeground(this, ServiceCompat.STOP_FOREGROUND_REMOVE)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    companion object {
        fun startService(context: Context, title: String? = null, text: String? = null) {
            val intent = Intent(context, MediaForegroundService::class.java).apply {
                putExtra(FOREGROUND_SERVICE_TITLE, title)
                putExtra(FOREGROUND_SERVICE_TEXT, text)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        fun stopService(context: Context) {
            context.stopService(Intent(context, MediaForegroundService::class.java))
        }
    }
}