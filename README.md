# zendrive-cordova-plugin
Basic Cordova plugin that wraps around Zendrive API function calls.

## Prerequisites
- Android Studio
- Xcode 10

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
**iOS - in Cordova top project level:**
- Add Cocoapods plugin: ``cordova plugin add cordova-plugin-cocoapod-support --save``
- Put following lines under ``<platform name="ios">`` in config.xml:
   ```
   <preference name="pods_ios_min_version" value="8.0" />
   <preference name="pods_use_frameworks" value="true" />
   <pod name="ZendriveSDK" git="https://bitbucket.org/zendrive-root/zendrive_cocoapod.git" tag="5.5.0" />   
   ```
- Add the Swift support plugin: ``cordova plugin add cordova-plugin-add-swift-support --save``
4. Build both platforms to ensure that there's no errors:
``cordova build android``
``cordova build ios``
5. Download this plugin to a new folder in the top level of your project. You can call it "ZendriveFinalPlugin".
6. Replace the SDK Key in ZendriveFinalPlugin.swift and ZendriveFinalPlugin.java with your Zendrive SDK Key located in your account. 
7. Then add the plugin to your Cordova project:
``cordova plugin add {top_level_project_dir/ZendriveFinalPlugin} --save``
8. If you're using iOS, the bridging header to Objective C should already have been added for you.
9. Make sure to reference the functions in your index.js and index.html portions of the Cordova project.
10. Any other development changes you can make directly in the respective platforms directory, otherwise you'll have to uninstall and reinstall the plugin again.

**Android - in Android Studio:**
- Make sure to import your own R class within the NotificationUtility.java class.
  * Ex: ``import com.companyname.projectname.R``

**iOS - in XCode:**
- Top Level Project -> Capabilities -> Background Modes -> ON
- Enable Location Updates under Background Modes
- Add Location Permission Keys in Info: 
  * Privacy - Location Always Usage Description
    * Add permissions explanation for the string field.
  * Privacy - Location Always And When In Use Usage Description
    * Add permissions explanation for the string field.
  * Privacy - Privacy - Location When In Use Usage Description
    * Add permissions explanation for the string field.
  
*Note: When building for iOS in Xcode 10, you will have to adjust your Workspace Settings to allow for Legacy Build settings. Go to: File -> Workspace Settings... -> Build System -> Legacy Build System*

**Feel free to build and test both projects at this point!**

## Platforms in development

## Platforms currently testing
- iOS (Swift)
- Android

## Features to be added for iOS
- Callbacks (iOS)
- Callbacks (Android)
