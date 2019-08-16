var exec = require('cordova/exec');

exports.openAutoStartAuthorizationDialog = function (option,success, error) {
	exec(success, error, 'AutoStartAuthorization', 'openAutoStartAuthorizationDialog',[option]);
};
