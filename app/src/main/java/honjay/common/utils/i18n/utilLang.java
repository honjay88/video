package honjay.common.utils.i18n;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

/**
 * Created by honjayChen on 2017/4/1.
 */

public class utilLang {

    public static String GetLanguage(SharedPreferences settings,Context ctx)
    {
        String lang = settings.getString("LANG", "en");
        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = ctx.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = ctx.getResources().getConfiguration().locale;
        }
        Configuration config = ctx.getResources().getConfiguration();
        if (! "".equals(lang) && ! locale.getLanguage().equals(lang)) {
            langSetting(config,lang,ctx);
        }
        return lang;
    }

    private static void langSetting(Configuration config ,String lang,Context ctx)
    {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        config.setLocale(locale);
        ctx.createConfigurationContext(config);
    }

    public static boolean SetLanguage(Integer langpos,SharedPreferences settings,Context ctx)
    {
        switch(langpos) {
            case 0: //English
                settings.edit().putString("LANG", "en").commit();
                return setLangRecreate("en",ctx);
            // return;
            case 1: //Hindi
                settings.edit().putString("LANG", "zh").commit();
                return setLangRecreate("zh",ctx);
            //return;
            default: //By default set to english
                //PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                settings.edit().putString("LANG", "en").commit();
                return setLangRecreate("en",ctx);
            // return;
        }

    }

    private static boolean setLangRecreate(String lang,Context ctx) {
        Configuration config = ctx.getResources().getConfiguration();
        langSetting(config,lang,ctx);
        return true;
    }
}
