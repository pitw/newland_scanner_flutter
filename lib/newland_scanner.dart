import 'dart:async';

import 'package:flutter/services.dart';
import 'package:barcode_newland_flutter/newland_scan_result.dart';

/// Provides functionality for interacting with a Newland scanner device.
/// This class is responsible for listening for barcode scan events from the
/// Newland scanner and exposing them as a stream of [NewlandScanResult] objects.
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
