package org.yakupyazar.translateapplication;

import android.content.Context;
import android.os.StrictMode;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static Translate getTranslateService(Context ctx) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //raw'daki json dosyası okumada Kotlin'deki fonksiyon istediğim sonucu vermedi, Java fonksiyonunu kullandık
        try (InputStream is = ctx.getResources().openRawResource(R.raw.translateids)) {

            //Get credentials:
            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);

            //Set credentials and get translate service:
            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
             return translateOptions.getService();

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }

    }
}
