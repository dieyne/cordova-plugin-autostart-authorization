var exec = require('cordova/exec');

exports.openAutoStartPermissionDialog = function (success, error) {
	exec(success, error, 'AutoStartPermission', 'openAutoStartPermissionDialog',[]);
};
