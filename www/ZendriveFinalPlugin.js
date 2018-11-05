cordova.define("com-zendrive-plugin-sdk.ZendriveFinalPlugin", function(require, exports, module) {
var exec = require('cordova/exec');

exports.setup = function (arg0, success, error) {
    exec(success, error, 'ZendriveFinalPlugin', 'setup', [arg0]);
};
               
exports.startDrive = function (arg0, success, error) {
    exec(success, error, 'ZendriveFinalPlugin', 'startDrive', [arg0]);
};
          
exports.stopDrive = function (arg0, success, error) {
    exec(success, error, 'ZendriveFinalPlugin', 'stopDrive', [arg0]);
};
               
exports.startPeriod2 = function (arg0, success, error) {
    exec(success, error, 'ZendriveFinalPlugin', 'startPeriod2', [arg0]);
};

exports.startPeriod3 = function (arg0, success, error) {
	exec(success, error, 'ZendriveFinalPlugin', 'startPeriod3', [arg0]);
};

exports.teardown = function (arg0, success, error) {
	exec(success, error, 'ZendriveFinalPlugin', 'teardown', [arg0]);
};

exports.stopPeriod = function (arg0, success, error) {
	exec(success, error, 'ZendriveFinalPlugin', 'stopPeriod', [arg0]);
};

});
