# Flutter Foreground Service 

## Setup

### Step 1
This plugin expects your application icon to be saved as `ic_launcher.png` which is the default name.

If you use the package `flutter_launcher_icons` to generate a new launcher icon, make sure to name the icon `ic_launcher.png`.

### Step 2
Add the plugin to your pubspec.yaml file:

````yaml
dependencies:
  flutter:
    sdk: flutter
  flutter_foreground_service: ^0.5.1
````

### Step 3
Import the package into your project

```dart
import 'package:flutter_android_foreground_service/flutter_android_foreground_service.dart';
```

## Usage
Check out the `example` tab here on pub.dev to view the plugin in action.

In essence, the following line of code will start the foreground service:

````dart
await ForegroundService.startService('title', 'message');
````

To stop the service again, use the following line of code:

````dart
await ForegroundService.stopService();
````
