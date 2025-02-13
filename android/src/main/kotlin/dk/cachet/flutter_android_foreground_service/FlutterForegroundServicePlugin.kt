package dk.cachet.flutter_android_foreground_service

import android.app.Activity
import androidx.annotation.NonNull

class FlutterForegroundServicePlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
    private lateinit var methodChannel : MethodChannel
    private var activity: Activity? = null


    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        methodChannel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_media_foreground_service")
        methodChannel.setMethodCallHandler(this)
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "startService" -> startService(call, result)
            "stopService" -> stopService()
            else -> result.notImplemented()
        }
    }

    private fun startService(call: MethodCall, result: Result) {
        val title: String? = call.argument("title")
        val text: String? = call.argument("text")

        activity?.apply {
            MediaForegroundService.startService(this, title, text)
        }
    }

    private fun stopService() {
        activity?.apply {
            MediaForegroundService.stopService(this)
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        methodChannel.setMethodCallHandler(null)
    }

    override fun onDetachedFromActivity() {
        this.activity = null
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        onAttachedToActivity(binding)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        this.activity = binding.activity
    }

    override fun onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity()
    }
}