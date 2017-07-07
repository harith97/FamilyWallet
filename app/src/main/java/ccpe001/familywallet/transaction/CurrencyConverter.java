package ccpe001.familywallet.transaction;

import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Knight on 7/7/2017.
 */

public class CurrencyConverter {

    private static final String CURRENCY_API = "https://openexchangerates.org/api/latest.json?app_id=0dee46c64b7f4d339415facf13e29242";


    public double toLKR(final Double amount){

        Double convertedCurrency =0.0;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(CURRENCY_API, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try {
                    JSONObject jsnObj =new JSONObject(new String(bytes));
                    JSONObject ratesObj = jsnObj.getJSONObject("rates");
                    final Double lkr = ratesObj.getDouble("LKR");

                    //convertedCurrency = amount *lkr;

                }catch (Exception e){

                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }

        });


        return 1.0;
    }
}
