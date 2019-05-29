# cordova-plugin-autostart-permission
Plugin for showing autostart permission dialogue box in android 


![Screenshot_2019-05-29-19-17-42-909_io ionic starter](https://user-images.githubusercontent.com/15616596/58562658-4ac19400-8247-11e9-8642-f7c509129445.png)



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
