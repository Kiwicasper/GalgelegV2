package dk.due;

import com.google.gson.*;
import okhttp3.*;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by casper on 13/03/2017.
 */
public class RestHelper {
    public static void main(String[] args){

        try {
            RestHelper.getInstance().addWord("hej");
            String[] s = RestHelper.getInstance().getWords();
            for(String str: s){
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private RestHelper(){}

    private static RestHelper instance;

    public String[] getWords() throws Exception{
        String uri = "http://217.61.221.58:10000/api/words";
        URL url = new URL(uri);
        HttpURLConnection connection =  (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        Scanner sc = new Scanner(url.openStream());

        String data = "";

        while(sc.hasNext()){
            data += sc.nextLine();
        }
        connection.disconnect();
        Gson gson = new Gson();
        WordContainer words = gson.fromJson(data, WordContainer.class);
        return words.words;
    }

    public static RestHelper getInstance(){
        if (instance == null){
            instance = new RestHelper();
        }
        return instance;
    }

    public void deleteWord(String word) throws Exception{
        String uri = "http://217.61.221.58:10000/api/words/"+ word;
        URL url = new URL(uri);
        HttpURLConnection connection =  (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("DELETE");

        System.out.println("Connection responce code = " + connection.getResponseCode());

    }

    public void addWord(String word) throws Exception{
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "word=" + word);
        Request request = new Request.Builder()
                .url("http://217.61.221.58:10000/api/words/")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
    }


}
