package com.zendrive.cordova.plugin;

import com.zendrive.sdk.Zendrive;
import com.zendrive.sdk.ZendriveConfiguration;
import com.zendrive.sdk.ZendriveDriveDetectionMode;
import com.zendrive.sdk.ZendriveOperationCallback;
import com.zendrive.sdk.ZendriveOperationResult;
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
			ZendriveConfiguration configuration = new ZendriveConfiguration("6ylBI0yLVuPUADaB6FxlrbeUkSAQ3vK8", "test_id", ZendriveDriveDetectionMode.AUTO_OFF);
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
							this.resultSend = new PluginResult(PluginResult.Status.OK, "Setup successfully completed!");
						}
						else {
							this.resultSend = new PluginResult(PluginResult.Status.ERROR, "Setup not completed!");
							Log.d("debug_msg","Teardown not successful :(");
						}
						callbackContext.sendPluginResult(this.resultSend);
					}
			);
		}

		else if(action.equals("startPeriod2")) {
			this.context = cordova.getActivity().getApplicationContext();
			ZendriveOperationCallback insuranceCallback = zendriveOperationResult -> {
                if (!zendriveOperationResult.isSuccess()) {
					Log.d("debug_msg","Insurance period 2 start failed!");
					this.resultSend = new PluginResult(PluginResult.Status.ERROR, "Period 2 call completed!");
                }
                else {
					this.resultSend = new PluginResult(PluginResult.Status.OK, "Period 2 call successfully completed!");
				}
            };
			ZendriveInsurance.startDriveWithPeriod2(this.context, "12345", insuranceCallback);
			callbackContext.sendPluginResult(this.resultSend);
		}

		else if(action.equals("stopPeriod")) {
			this.context = cordova.getActivity().getApplicationContext();
			ZendriveOperationCallback insuranceCallback = zendriveOperationResult -> {
				if (!zendriveOperationResult.isSuccess()) {
					Log.d("debug_msg","Insurance period stop failed!");
					this.resultSend = new PluginResult(PluginResult.Status.ERROR, "Period stop call completed!");
				}
				else {
					this.resultSend = new PluginResult(PluginResult.Status.OK, "Period stop call successfully completed!");
				}
			};
			ZendriveInsurance.stopPeriod(this.context, insuranceCallback);
			callbackContext.sendPluginResult(this.resultSend);
		}

		return true;
	}

	private Context context;
	private PluginResult resultSend;
}