package ir.mjmim.woocommercehelper.helpers


import android.util.Log
import com.google.api.client.auth.oauth.OAuthHmacSigner
import com.google.api.client.http.GenericUrl
import ir.mjmim.woocommercehelper.enums.RequestMethod
import ir.mjmim.woocommercehelper.main.WooBuilder
import java.security.GeneralSecurityException
import java.util.*
import kotlin.collections.Map.Entry

class OAuthSigner(private val wooCommerce: WooBuilder) {
    companion object {
        const val TAG = " OAuthSigner ->  "
    }

    fun getSignature(requestMethod: RequestMethod, endpoint: String, params: Map<String, String>?): String? {
        val parameters = OAuthParameters()
        parameters.computeTimestamp()
        parameters.computeNonce()
        parameters.version = "1.0"
        val genericUrl = GenericUrl()
        val map = LinkedHashMap<String, String>()

        try {
            parameters.consumerKey = this.wooCommerce.wc_key
            genericUrl.scheme = if (this.wooCommerce.isHttps) "https" else "http"
            genericUrl.host = this.wooCommerce.baseUrl!!
            //      genericUrl.appendRawPath("/wp-json");
            //      genericUrl.appendRawPath("/wc");
            //      genericUrl.appendRawPath("/v3");
            genericUrl.appendRawPath(endpoint)
            val oAuthHmacSigner = OAuthHmacSigner()
            oAuthHmacSigner.clientSharedSecret = this.wooCommerce.wc_secret
            parameters.signer = oAuthHmacSigner
            parameters.signatureMethod = this.wooCommerce.signing_method!!.`val`

            try {
                parameters.computeSignature(requestMethod.`val`, genericUrl, params)
            } catch (var8: GeneralSecurityException) {
                var8.printStackTrace()
            }

            map["oauth_consumer_key"] = parameters.consumerKey
            map["oauth_signature_method"] = parameters.signatureMethod
            map["oauth_timestamp"] = parameters.timestamp
            map["oauth_nonce"] = parameters.nonce
            map["oauth_version"] = parameters.version
            map["oauth_signature"] = parameters.signature
            genericUrl["oauth_consumer_key"] = parameters.consumerKey
            genericUrl["oauth_signature_method"] = parameters.signatureMethod
            genericUrl["oauth_timestamp"] = parameters.timestamp
            genericUrl["oauth_nonce"] = parameters.nonce
            genericUrl["oauth_version"] = parameters.version
            genericUrl["oauth_signature"] = parameters.signature

            if (params != null) {
                val ite = params.entries.iterator()
                while (ite.hasNext()) {
                    val entry = ite.next() as Entry<*, *>
                    Log.i(TAG, entry.key.toString() + "/" + entry.value as String)
                    genericUrl[entry.key.toString()] = entry.value
                }
            }
            return genericUrl.build()
        } catch (var9: Exception) {
            var9.printStackTrace()
            return null
        }

    }
}
