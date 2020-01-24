package com.corro.gothouses.koin

import android.app.Application
import com.corro.gothouses.model.HouseRepository
import com.corro.gothouses.model.retrofit.HouseApi
import com.corro.gothouses.viewmodel.HouseViewModel
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object MainModule {

    val netModule = module {
        fun provideCache(application: Application): Cache {
            val cacheSize = 10 * 1024 * 1024
            return Cache(application.cacheDir, cacheSize.toLong())
        }

        fun provideHttpClient(cache: Cache): OkHttpClient {
            val okHttpClientBuilder = OkHttpClient.Builder().cache(cache)
            return okHttpClientBuilder.build()
        }

        fun provideRetrofit(client: OkHttpClient): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://anapioficeandfire.com/api/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build()
        }

        single { provideCache(androidApplication()) }
        single { provideHttpClient(get()) }
        single { provideRetrofit(get()) }
    }

    val apiModule = module {
        fun provideUserApi(retrofit: Retrofit): HouseApi {
            return retrofit.create(HouseApi::class.java)
        }

        single { provideUserApi(get()) }
    }

    val repositoryModule = module {
        single { HouseRepository(get()) }
    }

    val viewModelModule = module {
        viewModel { HouseViewModel(get()) }
    }
}