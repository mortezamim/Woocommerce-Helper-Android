package ir.mjmim.woocommercehelpersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.mjmim.woocommercehelper.enums.RequestMethod
import ir.mjmim.woocommercehelper.enums.SigningMethod
import ir.mjmim.woocommercehelper.helpers.OAuthSigner
import ir.mjmim.woocommercehelper.main.WooBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//       val wcBuilder = WooBuilder().apply {
//            isHttps = Const.isHttp
//            baseUrl = Const.CORE_PATH
//            signing_method = SigningMethod.HMACSHA1
//            wc_key = Const.CUSTOMER_KEY
//            wc_secret = Const.CUSTOMER_SECRET
//        }
//        val res = OAuthSigner(wcBuilder).getSignature(RequestMethod.GET, "${api.path}/$urlMeta", params)
    }
}
