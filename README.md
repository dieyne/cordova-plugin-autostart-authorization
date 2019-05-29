# cordova-plugin-autostart-permission
Plugin for showing autostart permission dialogue box in android 

<div>
<img src="https://user-images.githubusercontent.com/15616596/58562658-4ac19400-8247-11e9-8642-f7c509129445.png" width="250">
</div>



Installation:-

```
cordova plugin add https://github.com/saty932/cordova-plugin-autostart-permission.git

```

To show AutostartPermissionDialog use the below method

```
 cordova.plugins.AutoStartPermission.openAutoStartPermissionDialog((data)=>{
        console.log(data);
      }),error=>{
        console.log("error");
      };



```
