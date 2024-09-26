import 'dart:async';

import 'package:flutter/services.dart';
import 'package:barcode_newland_flutter/newland_scan_result.dart';

class Newlandscanner {
  static const EventChannel _eventChannel =
      EventChannel('newland_listenToScanner');

  static final Stream<NewlandScanResult> _stream = _eventChannel
      .receiveBroadcastStream()
      .map((event) => event as Map)
      .map((event) => NewlandScanResult.fromNative(event));

  static Stream<NewlandScanResult> get listenForBarcodes {
    return _stream;
  }
}
