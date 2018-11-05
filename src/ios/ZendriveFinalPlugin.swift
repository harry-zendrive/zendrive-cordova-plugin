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
    
    @objc(setup:)
    func setup(command: CDVInvokedUrlCommand) {
        let configuration = ZendriveConfiguration()
        configuration.applicationKey = "app-key"
        configuration.driverId = "driver-id"
        configuration.driveDetectionMode = ZendriveDriveDetectionMode.autoOFF
        print("In setup function")
        
        Zendrive.setup(with: configuration, delegate: self) { (success, error) in
            var pluginResult = CDVPluginResult.init()
            if (error == nil) {
                pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Zendrive successfully setup!")
                print("Zendrive is setup.")
                //var error: NSError? = nil
                //ZendriveInsurance.startDrive(withPeriod2: self.trackingID, error: &error)
                //print("start drive call didn't break everything.")
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
        print("Zendrive toredown.")
        let pluginResult = CDVPluginResult.init(status: CDVCommandStatus_OK, messageAs: "Zendrive successfully toredown!")
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
    
    @objc(startDrive:)
    func startDrive(command: CDVInvokedUrlCommand) {
        Zendrive.startDrive("tracking-id")
        print("Zendrive drive started.")
        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Zendrive successfully started drive!")
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
    
    @objc(stopDrive:)
    func stopDrive(command: CDVInvokedUrlCommand) {
        Zendrive.stopDrive("tracking-id")
        print("Zendrive drive stopped.")
        let pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Zendrive successfully stopped drive!")
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
    
    @objc(startPeriod2:)
    func startPeriod2(command: CDVInvokedUrlCommand) {
        var error: NSError? = nil
        ZendriveInsurance.startDrive(withPeriod2: "tracking-id", error: &error)
        var pluginResult = CDVPluginResult.init()
        if (error == nil) {
            pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Zendrive successfully started period 2!")
            print("Zendrive started period 2.")
            //var error: NSError? = nil
            //ZendriveInsurance.startDrive(withPeriod2: self.trackingID, error: &error)
            //print("start drive call didn't break everything.")
        }
        else {
            pluginResult = CDVPluginResult.init(status: CDVCommandStatus_ERROR, messageAs: error?.localizedDescription)
        }
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
    
    @objc(startPeriod3:)
    func startPeriod3(command: CDVInvokedUrlCommand) {
        var error: NSError? = nil
        ZendriveInsurance.startDrive(withPeriod3: "tracking-id", error: &error)
        var pluginResult = CDVPluginResult.init()
        if (error == nil) {
            pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Zendrive successfully started period 3!")
            print("Zendrive started period 3.")
            //var error: NSError? = nil
            //ZendriveInsurance.startDrive(withPeriod2: self.trackingID, error: &error)
            //print("start drive call didn't break everything.")
        }
        else {
            pluginResult = CDVPluginResult.init(status: CDVCommandStatus_ERROR, messageAs: error?.localizedDescription)
        }
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
    
    @objc(stopPeriod:)
    func stopPeriod(command: CDVInvokedUrlCommand) {
        var error: NSError? = nil
        ZendriveInsurance.stopPeriod(&error)
        var pluginResult = CDVPluginResult.init()
        if (error == nil) {
            pluginResult = CDVPluginResult(status: CDVCommandStatus_OK, messageAs: "Zendrive successfully stopped period!")
            print("Zendrive stopped period.")
            //var error: NSError? = nil
            //ZendriveInsurance.startDrive(withPeriod2: self.trackingID, error: &error)
            //print("start drive call didn't break everything.")
        }
        else {
            pluginResult = CDVPluginResult.init(status: CDVCommandStatus_ERROR, messageAs: error?.localizedDescription)
        }
        self.commandDelegate.send(pluginResult, callbackId: command.callbackId)
    }
}

