package com.zendrive.cordova.plugin;

import com.zendrive.sdk.Zendrive;
import com.zendrive.sdk.ZendriveConfiguration;
import com.zendrive.sdk.ZendriveDriveDetectionMode;
import com.zendrive.sdk.ZendriveOperationCallback;
import com.zendrive.sdk.insurance.ZendriveInsurance;

import android.content.Context;
import android.util.Log;

import org.apache.cordova.*;
import org.json.JSONArray;

public class ZendriveFinalPlugin extends CordovaPlugin {

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
	}

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
		if(action.equals("setup")) {
			this.context = cordova.getActivity().getApplicationContext();
			ZendriveConfiguration configuration = new ZendriveConfiguration("sdk-key-here", "test_id", ZendriveDriveDetectionMode.AUTO_OFF); //AUTO_ON if implementing period 1 insurance period as well
=======
			ZendriveConfiguration configuration = new ZendriveConfiguration("sdk-key", "test-id", ZendriveDriveDetectionMode.AUTO_OFF);
>>>>>>> 9ff5192092b196afa17e98ce02cdb30757d0ceee
			Log.d("debug_msg","In setup function.");
			Zendrive.setup(
					this.context,
					configuration,
					null,
					SDKNotificationProvider.class,
					result -> {
                        if (result.isSuccess()) {
							Log.d("debug_msg","Setup is successful!");
							this.resultSend = new PluginResult(PluginResult.Status.OK, "Setup successfully completed!");
                        }
                        else {
							this.resultSend = new PluginResult(PluginResult.Status.ERROR, "Setup not completed!");
							Log.d("debug_msg","Setup not successful :(");
                        }
						callbackContext.sendPluginResult(this.resultSend);
                    }
			);
		}

		else if(action.equals("teardown")) {
			this.context = cordova.getActivity().getApplicationContext();
			Zendrive.teardown(
					this.context,
					result -> {
						if (result.isSuccess()) {
							Log.d("debug_msg","Teardown is successful!");
							this.resultSend = new PluginResult(PluginResult.Status.OK, "Teardown successfully completed!");
						}
						else {
							this.resultSend = new PluginResult(PluginResult.Status.ERROR, "Teardown not successful!");
							Log.d("debug_msg","Teardown not successful :(");
						}
						callbackContext.sendPluginResult(this.resultSend);
					}
			);
		}

		else if(action.equals("startDrive")) {
			this.context = cordova.getActivity().getApplicationContext();
			ZendriveOperationCallback callback = zendriveOperationResult -> {
				if (!zendriveOperationResult.isSuccess()) {
					Log.d("debug_msg","Drive start failed!");
					this.resultSend = new PluginResult(PluginResult.Status.ERROR, "Drive start failed!");
				}
				else {
					Log.d("debug_msg","Drive start succeeded!");
					this.resultSend = new PluginResult(PluginResult.Status.OK, "Drive start successfully completed!");
				}
				callbackContext.sendPluginResult(this.resultSend);
			};
			Zendrive.startDrive(context, "abcde", callback);
		}

		else if(action.equals("stopDrive")) {
			this.context = cordova.getActivity().getApplicationContext();
			ZendriveOperationCallback callback = zendriveOperationResult -> {
				if (!zendriveOperationResult.isSuccess()) {
					Log.d("debug_msg","Drive stop failed!");
					this.resultSend = new PluginResult(PluginResult.Status.ERROR, "Drive stop failed!");
				}
				else {
					Log.d("debug_msg","Drive stop succeeded!");
					this.resultSend = new PluginResult(PluginResult.Status.OK, "Drive stop successfully completed!");
				}
				callbackContext.sendPluginResult(this.resultSend);
			};
			Zendrive.stopDrive(context, "abcde", callback);
		}

		else if(action.equals("startPeriod2")) {
			this.context = cordova.getActivity().getApplicationContext();
			ZendriveOperationCallback insuranceCallback = zendriveOperationResult -> {
                if (!zendriveOperationResult.isSuccess()) {
					Log.d("debug_msg","Insurance period 2 start failed!");
					this.resultSend = new PluginResult(PluginResult.Status.ERROR, "Period 2 call failed!");
                }
                else {
					Log.d("debug_msg","Insurance period 2 start succeeded!");
					this.resultSend = new PluginResult(PluginResult.Status.OK, "Period 2 call successfully completed!");
				}
				callbackContext.sendPluginResult(this.resultSend);
            };
			ZendriveInsurance.startDriveWithPeriod2(this.context, "12345", insuranceCallback);
		}

		else if(action.equals("startPeriod3")) {
			this.context = cordova.getActivity().getApplicationContext();
			ZendriveOperationCallback insuranceCallback = zendriveOperationResult -> {
				if (!zendriveOperationResult.isSuccess()) {
					Log.d("debug_msg","Insurance period 3 start failed!");
					this.resultSend = new PluginResult(PluginResult.Status.ERROR, "Period 3 call failed!");
				}
				else {
					Log.d("debug_msg","Insurance period 3 start succeeded!");
					this.resultSend = new PluginResult(PluginResult.Status.OK, "Period 3 call successfully completed!");
				}
				callbackContext.sendPluginResult(this.resultSend);
			};
			ZendriveInsurance.startDriveWithPeriod3(this.context, "12345", insuranceCallback);
		}

		else if(action.equals("stopPeriod")) {
			this.context = cordova.getActivity().getApplicationContext();
			ZendriveOperationCallback insuranceCallback = zendriveOperationResult -> {
				if (!zendriveOperationResult.isSuccess()) {
					Log.d("debug_msg","Insurance period stop failed!");
					this.resultSend = new PluginResult(PluginResult.Status.ERROR, "Period stop call failed!");
				}
				else {
					this.resultSend = new PluginResult(PluginResult.Status.OK, "Period stop call successfully completed!");
				}
				callbackContext.sendPluginResult(this.resultSend);
			};
			ZendriveInsurance.stopPeriod(this.context, insuranceCallback);
		}

		return true;
	}

	private Context context;
	private PluginResult resultSend;
}
