# newlandscanner

Optimized lib from **<https://pub.dev/packages/newlandscanner>**
All credits to the original author.

Capture SDK of [Newland Barcode Android devices](https://www.newland-id.com/de) for Flutter using native Intent broadcast receiver.

## Installation

Add this to your package's pubspec.yaml file:

```
dependencies:
  newland_scanner: ^0.0.1
```

### iOS

not supported

### Android

Make sure the device scan output mode is set to "Output via API".

## Usage

```dart
Newlandscanner.listenForBarcodes.listen((event) {
 log('BarcodeScanned: ${event.barcodeData}')
});
```

## Author

- [Mateusz Maziec](https://www.nexaion.de/)
