package com.meplus.client.utils;

import android.content.Context;
import android.util.Log;

import com.marvinlabs.intents.MediaIntents;
import com.meplus.client.Constants;
import com.meplus.client.api.model.FirVersion;

import cn.trinea.android.common.util.PackageUtils;
import im.fir.sdk.FIR;
import im.fir.sdk.VersionCheckCallback;

/**
 * Created by dandanba on 3/2/16.
 */
public class FIRUtils {
    public static void checkForUpdateInFIR(final Context context) {
        FIR.checkForUpdateInFIR(Constants.FIR_TOKEN, new VersionCheckCallback() {

                    @Override
                    public void onSuccess(String versionJson) {
                        Log.i("fir", "check from fir.im success! " + "\n" + versionJson);
                        final FirVersion version = JsonUtils.readValue(versionJson, FirVersion.class);
                        final int versionCode = PackageUtils.getAppVersionCode(context);
                        if (versionCode < version.version) {
                            context.startActivity(MediaIntents.newOpenWebBrowserIntent(version.update_url));
                        }
                    }

                    @Override
                    public void onFail(Exception exception) {
                        Log.i("fir", "check fir.im fail! " + "\n" + exception.getMessage());
                    }

                    @Override
                    public void onStart() {
                        Log.i("fir", "check fir.im start! " + "\n");
                    }

                    @Override
                    public void onFinish() {
                        Log.i("fir", "check fir.im finish! " + "\n");
                    }
                }
        );
    }
}
