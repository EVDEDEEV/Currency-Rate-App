package my.project.currenciestestapp.data.api

import my.project.currenciestestapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val apikey = BuildConfig.API_KEY
        val url =
            original.url.newBuilder().addQueryParameter("apikey", apikey).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}
