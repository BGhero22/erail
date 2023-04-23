package com.example.ebulgarianrailway.Helpers;


import android.app.Activity;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.example.ebulgarianrailway.CustomClasses.CustomDialog;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiHelper {

    public static void GetApiResults(Activity activity)
    {
        HashMap<String, String> result = new HashMap<>();
        Thread t1 = new Thread(() -> {
//////////////////////////////////
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://covid-193.p.rapidapi.com/statistics?country=Bulgaria")
                            .get()
                            .addHeader("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                            .addHeader("X-RapidAPI-Key", "9dc68fc476msheaaddf1ee4408d4p1ac47cjsnadd811c98af4")
                            .build();

                    Response response = client.newCall(request).execute();


                    if (response.isSuccessful()) {
                        InputStream responseBody = response.body().byteStream();
                        result.putAll(readJsonStream(responseBody));
                        CustomDialog dialog = new CustomDialog(activity);
                        dialog.startCovidDialog(result);

                    } else {
                        Log.d("Error", "GetApiResults: FAILURE ");
                    }

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
          ////////////////////////////////////////////////
        });
        t1.start();
    }

    public static HashMap<String, String> readJsonStream(InputStream in) throws IOException {
        try (JsonReader reader = new JsonReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
            return readResults(reader);
        }
    }

    public static HashMap<String, String> readResults(JsonReader reader) throws IOException {
        HashMap<String,String> result = new HashMap<>();

        reader.beginObject();
        while (reader.hasNext()) {
            result.putAll(readResponse(reader));
        }
        reader.endObject();
        return result;
    }

    public static HashMap<String, String> readResponse(JsonReader reader) throws IOException {
        HashMap<String,String> result = new HashMap<>();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("response") && reader.peek() != JsonToken.NULL) {
                result.putAll(readResponseArray(reader));
            } else {
                reader.skipValue();
            }
        }
        return result;
    }

    public static HashMap<String,String> readResponseArray(JsonReader reader) throws IOException {
        HashMap<String,String> result = new HashMap<>();

        reader.beginArray();
        while (reader.hasNext()) {
            reader.beginObject();
            while (reader.hasNext())
            {
                String name = reader.nextName();
                if (name.equals("cases")&& reader.peek() != JsonToken.NULL)
                {
                    result.putAll(readCases(reader));
                }
                else if(name.equals("deaths")&& reader.peek() != JsonToken.NULL)
                {
                    result.putAll(readDeaths(reader));
                }
                else {
                    reader.skipValue();
                }
            }
            reader.endObject();
        }
        reader.endArray();
        return result;
    }

    public static HashMap<String,String> readCases(JsonReader reader) throws IOException {
        HashMap<String,String> result = new HashMap<>();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("new")&& reader.peek() != JsonToken.NULL) {
               result.put("new_cases",reader.nextString());
            } else if (name.equals("active")&& reader.peek() != JsonToken.NULL) {
                result.put("active_cases",reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return result;
    }

    public static HashMap<String,String> readDeaths(JsonReader reader) throws IOException {
        HashMap<String,String> result = new HashMap<>();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("new")&& reader.peek() != JsonToken.NULL) {
                result.put("new_deaths",reader.nextString());
            } else if (name.equals("total")&& reader.peek() != JsonToken.NULL) {
                result.put("total_deaths",reader.nextString());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return result;
    }

}
