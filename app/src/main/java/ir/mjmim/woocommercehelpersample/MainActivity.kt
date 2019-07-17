package ir.mjmim.woocommercehelpersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.mjmim.woocommercehelper.Enums.RequestMethod
import ir.mjmim.woocommercehelper.Enums.SigningMethod
import ir.mjmim.woocommercehelper.Helpers.OAuthSigner
import ir.mjmim.woocommercehelper.Main.WCBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       val wcBuilder = WCBuilder().apply {
            setIsHttps(Const.isHttp)
            baseUrl = Const.CORE_PATH
            signing_method = SigningMethod.HMACSHA1
            wc_key = Const.CUSTOMER_KEY
            wc_secret = Const.CUSTOMER_SECRET
        }
        val res = OAuthSigner(wcBuilder).getSignature(RequestMethod.GET, "${api.path}/$urlMeta", params)
    }
}
