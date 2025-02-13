library flutter_android_foreground_service;

import 'dart:async';
import 'dart:io' show Platform;

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/foundation.dart';

part 'src/android_foreground_service_handler.dart';

class ForegroundService {
  static startService(String title, String text) async {
    if (Platform.isAndroid) {
      await ForegroundServiceHandler.startService(title, text);
    } else {
      debugPrint("Error: Can only use foreground services on Android!");
    }
  }

  static stopService() async {
    if (Platform.isAndroid) {
      await ForegroundServiceHandler.stopService();
    } else {
      debugPrint("Error: Can only use foreground services on Android!");
    }
  }

}
