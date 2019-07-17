package ir.mjmim.woocommercehelper.Helpers;


import android.util.Log;
import com.google.api.client.auth.oauth.OAuthHmacSigner;
import com.google.api.client.http.GenericUrl;
import ir.mjmim.woocommercehelper.Enums.RequestMethod;
import ir.mjmim.woocommercehelper.Main.WCBuilder;
import ir.mjmim.woocommercehelper.OAuthParameters;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class OAuthSigner {

  private static final String TAG = "Inside OAuthSigner -   ";
  private WCBuilder wooCommerce;

  public OAuthSigner(WCBuilder wooCommerce) {
    this.wooCommerce = wooCommerce;
//    Log.i("Inside OAuthSigner -   ", wooCommerce.toString());
  }

  public String getSignature(RequestMethod requestMethod, String endpoint, Map<String, String> params) {
    OAuthParameters parameters = new OAuthParameters();
    parameters.computeTimestamp();
    parameters.computeNonce();
    parameters.version = "1.0";
    GenericUrl genericUrl = new GenericUrl();
    LinkedHashMap map = new LinkedHashMap();

    try {
      parameters.consumerKey = this.wooCommerce.getWc_key();
      genericUrl.setScheme(this.wooCommerce.isHttps() ? "https" : "http");
      genericUrl.setHost(this.wooCommerce.getBaseUrl());
//      genericUrl.appendRawPath("/wp-json");
//      genericUrl.appendRawPath("/wc");
//      genericUrl.appendRawPath("/v3");
      genericUrl.appendRawPath(endpoint);
      OAuthHmacSigner oAuthHmacSigner = new OAuthHmacSigner();
      oAuthHmacSigner.clientSharedSecret = this.wooCommerce.getWc_secret();
      parameters.signer = oAuthHmacSigner;
      parameters.signatureMethod = this.wooCommerce.getSigning_method().getVal();

      try {
        parameters.computeSignature(requestMethod.getVal(), genericUrl, params);
      } catch (GeneralSecurityException var8) {
        var8.printStackTrace();
      }

      map.put("oauth_consumer_key", parameters.consumerKey);
      map.put("oauth_signature_method", parameters.signatureMethod);
      map.put("oauth_timestamp", parameters.timestamp);
      map.put("oauth_nonce", parameters.nonce);
      map.put("oauth_version", parameters.version);
      map.put("oauth_signature", parameters.signature);
      genericUrl.put("oauth_consumer_key", parameters.consumerKey);
      genericUrl.put("oauth_signature_method", parameters.signatureMethod);
      genericUrl.put("oauth_timestamp", parameters.timestamp);
      genericUrl.put("oauth_nonce", parameters.nonce);
      genericUrl.put("oauth_version", parameters.version);
      genericUrl.put("oauth_signature", parameters.signature);

      if (params != null) {
        Iterator i$ = params.entrySet().iterator();
        while (i$.hasNext()) {
          Entry<String, String> entry = (Entry) i$.next();
          Log.i(TAG,entry.getKey() + "/" + (String) entry.getValue());
          genericUrl.put(entry.getKey(), entry.getValue());
        }
      }
      return genericUrl.build();
    } catch (Exception var9) {
      var9.printStackTrace();
      return null;
    }
  }
}
