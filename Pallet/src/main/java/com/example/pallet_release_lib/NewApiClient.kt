package com.example.pallet_release_lib

import com.example.pallet_release_lib.actitivties.FirstActivityPalletReleaseLibrary.Companion.BASE_URL
import com.example.pallet_release_lib.actitivties.FirstComposeActivityPalletRelease
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

object NewApiClient {



//    private const val BASE_URL = "https://swms.mobillor.net/api/api/api/swms/"
//    private const val BASE_URL2 = "https://swms.mobillor.net/usr_api/"
//    private const val BASE_URL_VERSION_CONTROL = "https://wmsapi-precisiondev.nerolac.com/api/"



        private val client = OkHttpClient.Builder()
                .sslSocketFactory(createInsecureSslContext().socketFactory, createTrustAllManager())
                .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                })
                .build()

        private fun createTrustAllManager(): X509TrustManager {
                return object : X509TrustManager {
                        override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                                // No need to implement
                        }

                        override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
                                // No need to implement
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                                return arrayOf() // Return an empty array to trust all certificates
                        }
                }
        }

        private fun createInsecureSslContext(): SSLContext {
                return SSLContext.getInstance("TLS").apply {
                        init(null, arrayOf(createTrustAllManager()), null)
                }
        }

        private val retrofit = Retrofit.Builder()
                .baseUrl(FirstComposeActivityPalletRelease.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()


        //    TODO for normal API calls
        fun getService(): ApiInterface {
                return retrofit.create(ApiInterface::class.java)
        }

}