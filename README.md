# Woocommerce-Helper-Android

-   Kotlin - **1.3.41**
-   CURRENT_VERSION - **0.0.6**

Very lightweight library to help you communicate with Woocommerce  **REST API** 

So far you can generate **Request Link** with **OAuth 1.0**

  <img src="https://github.com/mortezamim/Woocommerce-Helper-Android/blob/master/assets/logo.png?raw=true"/>


## Installation
We offer jitpack installation.

1. In your module level/app level build.gradle file, add the following lines

        allprojects {
	        repositories{
		        google()
	            jcenter()
          
				 maven { url 'https://jitpack.io' }
		       }
		}
		
2. Then in dependencies, add the following line to include the library:
  
        dependencies {
            .
            .
            def woo_helper_version = "CURRENT_VERSION"
            implementation "com.github.mortezamim:Woocommerce-Helper-Android:$woo_helper_version"
        }

3. Sync the project with the gradle files. The library should be imported to your project.
  
4. just put the following lines in your app.

		class MainActivity : AppCompatActivity() {  
			override fun onCreate(savedInstanceState: Bundle?) {  
			super.onCreate(savedInstanceState)  
			setContentView(R.layout.activity_main)
			.
			.
			//create params
			val params = HashMap<String, String>().apply {
				put("param_1", "VALUE")
				put("param_2", "VALUE")
				.
				.
			}
			
			//create WooBuilder
                val wooBuilder = WooBuilder().apply {
				//make it false if you don't have ssl
				isHttps = true  
		
				//example : "mj-dev.ir/wp-json/wc/v3"
				baseUrl = "YOUR URL"  
		
				signing_method = SigningMethod.HMACSHA1
		
				//replace by your key
				wc_key = "CUSTOMER_KEY"
		 
		 		//replace by your key
		 		wc_secret = "CUSTOMER_SECRET"
                }
		
			//lets generate new request link to get products
			val resultLink: String? = OAuthSigner(wooBuilder)
				.getSignature(RequestMethod.GET, "/products", params)
		
			// use Http Request library to fetch data from generated link in result		
            }
        }

## Goals
- Add inside request to fetch data
- Add [Gson](https://github.com/google/gson) to parse data
