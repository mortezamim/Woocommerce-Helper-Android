package ir.mjmim.woocommercehelper.Interfaces;

import ir.mjmim.woocommercehelper.Models.Product;
import retrofit.RetrofitError;

/**
 * Created by kumardivyarajat on 22/02/16.
 */
public interface ObjectCallback {
    void Callback(Product content, RetrofitError error);

}
