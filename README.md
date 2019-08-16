# cordova-plugin-autostart-authorization
Plugin for showing autostart authorization dialogue box in android 

<div>
<img src="https://user-images.githubusercontent.com/15616596/58562658-4ac19400-8247-11e9-8642-f7c509129445.png" width="250">
</div>



Installation:-

```
cordova plugin add https://github.com/dieyne/cordova-plugin-autostart-authorization.git

```

To show AutostartAuthorizationDialog use the below method

```
 const option = {yesBtnTitle:"Yes",noBtnTitle:"No",title:"Turn on the autostart to receive notification"};
 cordova.plugins.AutoStartPermission.openAutoStartPermissionDialog(option,(data)=>{
        console.log(data);
      }),error=>{
        console.log("error");
      };



```
