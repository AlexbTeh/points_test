package com.him.eurohim.data.module

import android.annotation.SuppressLint
import android.util.Log
import com.him.eurohim.data.apiservice.WebSocketService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import javax.net.ssl.X509TrustManager
import kotlin.time.Duration.Companion.seconds

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideWebSocketService(client: HttpClient): WebSocketService {
        return WebSocketService(client)
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideWebSocketHttpClient(): HttpClient {
        return HttpClient(CIO) {

            engine {
                https {
                    // Disable certificate verification
                    trustManager = @SuppressLint("CustomX509TrustManager")
                    object : X509TrustManager {
                        override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> =
                            arrayOf()

                        @SuppressLint("TrustAllX509TrustManager")
                        override fun checkClientTrusted(
                            certs: Array<java.security.cert.X509Certificate>,
                            authType: String
                        ) {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        override fun checkServerTrusted(
                            certs: Array<java.security.cert.X509Certificate>,
                            authType: String
                        ) {
                        }
                    }
                }
            }

            install(WebSockets) {
                contentConverter = KotlinxWebsocketSerializationConverter(Json)
            }

            install(HttpRequestRetry) {

                retryOnServerErrors(maxRetries = Int.MAX_VALUE)
                exponentialDelay(maxDelayMs = 128.seconds.inWholeMilliseconds)
                modifyRequest {
                    it.setBody(TextContent("ErrorHttp + ${it.body}", ContentType.Text.Plain))
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = true
                    coerceInputValues = true
                    explicitNulls = false
                })
                register(
                    ContentType.Text.Html, KotlinxSerializationConverter(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        }
                    )
                )
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.i("HttpClient", message)
                    }
                }
            }
        }
    }
}
