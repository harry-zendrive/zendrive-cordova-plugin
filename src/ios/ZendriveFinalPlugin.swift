//
//  ZendriveFinalPlugin.swift
//  CZendriveTest
//
//  Created by Harry Liu on 10/23/18.
//

import Foundation
import ZendriveSDK
import ZendriveSDK.Insurance

@objc(ZendriveFinalPlugin) class ZendriveFinalPlugin: CDVPlugin, ZendriveDelegateProtocol {
    let appKey = "sdk-key"
    let driverID = "cordova.driver"
    let trackingID = "12345"
    
    @objc(setup:)
    func setup(command: CDVInvokedUrlCommand) {
        let configuration = ZendriveConfiguration()
        configuration.applicationKey = self.appKey
        configuration.driverId = self.driverID
        configuration.driveDetectionMode = ZendriveDriveDetectionMode.autoOFF
        print("In setup function.")
        
        Zendrive.setup(with: configuration, delegate: self) { (success, error) in
            var pluginResult = CDVPluginResult.init()
            if (error == nil) {
                pluginResult = CDVPluginResult(status: CDVCommandStatus_OK)
                print("Zendrive is setup.")
            }
            else {
                pluginResult = CDVPluginResult.init(status: CDVCommandStatus_ERROR, messageAs: error?.localizedDescription)
            }
            self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
        }
    }
    
    @objc(teardown:)
    func teardown(command: CDVInvokedUrlCommand) {
        Zendrive.teardown(completionHandler: nil)
        let pluginResult = CDVPluginResult.init(status: CDVCommandStatus_OK)
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
    
    @objc(startPeriod2:)
    func startPeriod2(command: CDVInvokedUrlCommand) {
        var error: NSError? = nil
        ZendriveInsurance.startDrive(withPeriod2: trackingID, error: &error)
        let pluginResult = CDVPluginResult.init(status: CDVCommandStatus_OK)
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
    
    @objc(startPeriod3:)
    func startPeriod3(command: CDVInvokedUrlCommand) {
        var error: NSError? = nil
        ZendriveInsurance.startDrive(withPeriod3: trackingID, error: &error)
        let pluginResult = CDVPluginResult.init(status: CDVCommandStatus_OK)
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
    
    @objc(stopPeriod:)
    func stopPeriod(command: CDVInvokedUrlCommand) {
        var error: NSError? = nil
        ZendriveInsurance.stopPeriod(&error)
        let pluginResult = CDVPluginResult.init(status: CDVCommandStatus_OK)
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
}

