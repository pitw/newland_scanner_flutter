package ch.pitw.newland_scanner

import androidx.annotation.NonNull
import ch.pitw.newland_scanner.receiver.BarcodeScanReceiver

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** NewlandscannerPlugin */
class NewlandscannerPlugin : FlutterPlugin, MethodCallHandler {
    private lateinit var channel : MethodChannel
    private lateinit var eventChannel: EventChannel

    private var scanReceiver: BarcodeScanReceiver? = null;

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, "newland_scanner")
        channel.setMethodCallHandler(this)

        eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, "newland_listenToScanner")

        eventChannel.setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                scanReceiver = BarcodeScanReceiver(events)

                flutterPluginBinding.applicationContext.registerReceiver(
                    scanReceiver,
                    BarcodeScanReceiver.filter
                )
            }

            override fun onCancel(arguments: Any?) {
                flutterPluginBinding.applicationContext.unregisterReceiver(scanReceiver)
            }
        })
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        if (call.method == "getPlatformVersion") {
            result.success("Android ${android.os.Build.VERSION.RELEASE}")
        } else {
            result.notImplemented()
        }
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
        eventChannel.setStreamHandler(null)
    }
}