import 'package:flutter/material.dart';
import 'package:flutter_android_foreground_service/flutter_android_foreground_service.dart';

void main() {
  runApp(MyApp());
  startForegroundService();
}

void startForegroundService() async {
  ForegroundService.startService('test title', 'test text');
  debugPrint("Started service");
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Foreground Service Example'),
        ),
        body: Center(
            child: Text('Foreground service example, check notification bar')),
      ),
    );
  }

  @override
  void dispose() {
    ForegroundService.stopService();
    super.dispose();
  }
}
