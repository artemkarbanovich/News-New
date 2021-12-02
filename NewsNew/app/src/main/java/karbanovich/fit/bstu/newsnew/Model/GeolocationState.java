package karbanovich.fit.bstu.newsnew.Model;

import android.content.Context;
import java.util.Arrays;
import java.util.Locale;

public class GeolocationState {

    private static GeolocationState instance;
    private boolean state;
    private String countryCode;
    private String[] availableCountries = new String[] {
            "ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", "co", "cu", "cz", "de",
            "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in", "it", "jp", "kr", "lt",
            "lv", "ma", "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro", "rs", "ru",
            "sa", "se", "sg", "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za"
    };

    private GeolocationState() {
        state = false;
        countryCode = null;
    }


    public static GeolocationState getInstance() {
        if(instance == null)
            instance = new GeolocationState();
        return instance;
    }

    public boolean getState() {
        return state;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setState(boolean state, Context context) {
        this.state = state;

        setCountryCode(context.getResources().getConfiguration().locale.getCountry().toLowerCase(Locale.ROOT));

        if(state && !Arrays.asList(availableCountries).contains(getCountryCode()))
            setCountryCode(null);
        else if(!state)
            setCountryCode(null);
    }

    private void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
