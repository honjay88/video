package honjay.common.dagger.module;
import android.app.Application;
import android.util.Base64;

import com.example.honjaychen.dagger.BuildConfig;

import com.example.honjaychen.dagger.initial.App;
/*import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;*/
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import honjay.common.utils.CompressEngine;
import honjay.common.utils.CryptoEngine;
import okhttp3.Cache;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.jackson.JacksonConverterFactory;
//import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by honjayChen on 2017/2/25.
 */

@Module
public class NetModule {
    String mBaseUrl;

    public NetModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    Cache provideHttpCache(App application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder
                //.disableHtmlEscaping()
              //     //.registerTypeAdapter(Byte.class, new StringConverter())
               //    .registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter())
                   .setLenient();

                   //.setPrettyPrinting()
                   //.serializeNulls()
                 //  .disableHtmlEscaping();

                   //.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        return gsonBuilder.create();
    }

    public  class StringConverter implements JsonSerializer<String>, JsonDeserializer<String> {
        public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
            if (src == null) {
                return new JsonPrimitive("");
            } else {
                return new JsonPrimitive(src.toString());
            }
        }
        public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return json.getAsJsonPrimitive().getAsString();
        }
    }

    private static class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
        public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Base64.decode(json.getAsString(), Base64.NO_WRAP);
        }

        public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(Base64.encodeToString(src, Base64.NO_WRAP));
        }
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG)
        {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(logging);
        }
        //載入自訂義標頭
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request();
                final Response response = chain.proceed(request);
                final Headers headers = response.headers();
                ResponseBody body = null;
                String crypt =headers.get("x-crypt-type");
                String compress =headers.get("x-encoding");
                String result = "";
                String decrypt ="";
                try {
                      if(response.code() == 200) {
                         String stringJson = response.body().string();
                         if (crypt !=null){
                             decrypt = CryptoEngine.AESDecrypt(stringJson,"8080808080808080");
                             if(compress !=null){
                              //gzip
                                 if(compress =="gzip")
                                     result = CompressEngine.GzipBase64toDecompress(stringJson);
                                 else
                                     result = CompressEngine.DeflaterBase64toDecompress(stringJson);
                             }
                             else {
                                byte[] data = Base64.decode(decrypt, Base64.DEFAULT);
                                result = new String(data, StandardCharsets.UTF_8);
                             }
                         }
                         else{
                               if (compress !=null) {
                               //gzip
                                   if(compress =="gzip")
                                       result = CompressEngine.GzipBase64toDecompress(stringJson);
                                   else
                                       result = CompressEngine.DeflaterBase64toDecompress(stringJson);
                               }
                               else {
                                //  byte[] data = Base64.decode(decrypt, Base64.DEFAULT);
                                   result = stringJson;//new String(data, StandardCharsets.UTF_8);
                               }
                           }
                           body = ResponseBody.create(response.body().contentType(), result);
                        }else if(response.code() == 403) {

                      }
                }catch (Exception e) {
                    e.printStackTrace();
                }
                return response.newBuilder().body(body).build();
               // return response.newBuilder()
                        //   .addHeader("Content-Type", "application/json;charset=UTF-8")
                        // .addHeader("Accept-Encoding", "gzip, deflate")
                        //  .addHeader("Connection", "keep-alive")
                        //   .addHeader("Accept", "application/json")
                        //.addHeader("Cookie", "add cookies here")
                      //  .build();

            }
        });
        client.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .cache(cache);
        return client.build();
    }

    @Provides
    @Named("Retrofit")
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        //RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addConverterFactory(JacksonConverterFactory.create(getObjectMapper()))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
    }

   /* private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        objectMapper.setVisibilityChecker(objectMapper
                .getSerializationConfig()
                .getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.DEFAULT)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
        return objectMapper;
    }*/
}