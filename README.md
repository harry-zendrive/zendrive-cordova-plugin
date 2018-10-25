# zendrive-cordova-plugin
Basic Cordova plugin that wraps around Zendrive API function calls.

## How to use the plugin
1. Have a valid Cordova project or create a project:
``cordova create <dir_name> <company_name> <project_name>``
2. Have the correct platforms included in your project:
``cordova platform add android``
``cordova platform add ios``
3. Source the dependencies for Zendrive SDK:

**Android - add to build.gradle in /platforms/android/app:**
  ```
  repositories { 
    jcenter()
  }
  dependencies {
    compile 'com.zendrive.sdk.android:ZendriveSDK:5.5.2'
  }
  
  android { 
    lintOptions {
      // This is needed to avoid spurious lint errors from libthrift and log4j.
      disable 'InvalidPackage' 
    }
    packagingOptions {
      exclude 'META-INF/LICENSE.txt' 
      exclude 'META-INF/NOTICE.txt'
    } 
   }  
   ```
**iOS - in top project level:**
- Add Cocoapods plugin: ``cordova plugin add cordova-plugin-cocoapod-support --save``
- Put following lines under ``<platform name="ios">``
   ```
   <preference name="pods_ios_min_version" value="8.0" />
   <preference name="pods_use_frameworks" value="true" />
   <pod name="ZendriveSDK" git="https://bitbucket.org/zendrive-root/zendrive_cocoapod.git" tag="5.6.0" />   
   ```
- Add the Swift support plugin: ``cordova plugin add cordova-plugin-add-swift-support --save``
4. Build both platforms to ensure that there's no errors:
``cordova build android``
``cordova build ios``
5. Download this plugin to a new folder in the top level of your project. You can call it "ZendriveFinalPlugin".
6. Then add the plugin to your Cordova project:
``cordova plugin add {top_level_project_dir/ZendriveFinalPlugin} --save``
7. If you're using iOS, the bridging header to Objective C should already have been added for you.
8. Any other development changes you can make directly in the respective platforms directory, otherwise you'll have to uninstall and reinstall the plugin again.

## Platforms in development
- Android

## Platforms currently testing
- iOS (Swift)
