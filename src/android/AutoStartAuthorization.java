package cordova.plugin.autostart.permission;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.util.Log;
import android.content.SharedPreferences;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;


/**
 * This class echoes a string called from JavaScript.
 */
public class AutoStartAuthorization extends CordovaPlugin {
      String message = "Turn on autostart permission";
      String yesBtnTitle="Yes";
      String noBtnTitle="No";
      boolean force = false;
    public Context context;
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        context = this.cordova.getActivity().getApplicationContext();
    }
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

      try {
        JSONObject options = args.getJSONObject(0);
        message = options.getString("title");
        yesBtnTitle = options.getString("yesBtnTitle");
        noBtnTitle = options.getString("noBtnTitle");
        force = options.getBoolean("force");
      } catch (JSONException e) {
      }
        if (action.equals("openAutoStartAuthorizationDialog")) {
            if(appGetFirstTimeRun() == 0||force==true){
           openAutoStartAuthorizationDialog(callbackContext);
            return true;
            }
            return false;
        }
        return false;
    }

    private void openAutoStartAuthorizationDialog(CallbackContext callbackContext) {
            showDialogBox(callbackContext);
    }
    private void showDialogBox(CallbackContext callbackContext){
        try {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(cordova.getActivity());
            builder1.setMessage(Html.fromHtml("<font color='#000000'>"+message+"</font>"));
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    yesBtnTitle,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            addAutoStartup(callbackContext);
                            dialog.cancel();
                        }
                    });

            builder1.setNegativeButton(
                    noBtnTitle,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
            alert11.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            alert11.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
            alert11.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);

        }
        catch (Exception e){
            Log.d("dailougeerror",""+e);
            callbackContext.error("Error while showing dialogue");
        }
    }

    private void addAutoStartup(CallbackContext callbackContext) {

        try {
            Intent intent = new Intent();

              for (Intent intent1 : POWERMANAGER_INTENTS){
                if (getPackageManager().resolveActivity(intent1, PackageManager.MATCH_DEFAULT_ONLY) == null) {
                        intent = new Intent(android.provider.Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
                }else{
                    intent = intent1;
                }
                
            }
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            callbackContext.success("success");
 
        } catch (Exception e) {
            callbackContext.error("Error with opening AutoStartAuthorization activity");
        }
    }
    private int appGetFirstTimeRun() {
        //Check if App Start First Time
        SharedPreferences appPreferences = context.getSharedPreferences("MyAPP", 0);
        int appCurrentBuildVersion = BuildConfig.VERSION_CODE;
        int appLastBuildVersion = appPreferences.getInt("app_first_time", 0);
    
        //Log.d("appPreferences", "app_first_time = " + appLastBuildVersion);
    
        if (appLastBuildVersion == appCurrentBuildVersion ) {
            return 1; //ya has iniciado la appp alguna vez
    
        } else {
            appPreferences.edit().putInt("app_first_time",
                    appCurrentBuildVersion).apply();
            if (appLastBuildVersion == 0) {
                return 0; //es la primera vez
            } else {
                return 2; //es una versión nueva
            }
        }
    }


    private static final Intent[] POWERMANAGER_INTENTS = {

        new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")),
        new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
        new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
        new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
        new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")),
        new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
        new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
        new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
        new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
        new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
        new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
        new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.MainActivity")),
        new Intent().setComponent(new ComponentName("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity"))
};
}



