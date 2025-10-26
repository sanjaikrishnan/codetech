import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.*;
public class Task2 {
    //private static final String key="your_actual_api_key_here";
    private static final String key="325b000030ddc9c2948a60ba94c2bfc5";
    private static final String url= "https://api.openweathermap.org/data/2.5/weather?q=";
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter city:");
        String city=sc.nextLine();
        try{
            String str=url+city+"&appid="+key+"&units=metric";
            URL u=new URL(str);
            HttpURLConnection conn=(HttpURLConnection)
            u.openConnection();
            conn.setRequestMethod("GET");
            int status=conn.getResponseCode();
            if(status!=200){
                System.out.println("Failed to fetch weather data.HTTP status code:"+status);
                return;
            }
            BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb=new StringBuilder();
            String line;
            while((line = br.readLine())!=null){
                sb.append(line);
            }
            conn.disconnect();
            String w=sb.toString();
            weather(w);
        }
        catch(Exception e){
            System.out.println("Exception occured:"+e.getMessage());
        }
    }
    private static void weather(String w){
        JSONObject obj=new JSONObject(w);
        String city=obj.getString("name");
        JSONObject main=obj.getJSONObject("main");
        double temperature=main.getDouble("temp");
        int humidity=main.getInt("humidity");
        JSONObject weather=obj.getJSONArray("weather").getJSONObject(0);
        String des=weather.getString("description");
        System.out.println("===================================");
        System.out.println("Weather");
        System.out.println("City: "+city);
        System.out.println("Temp(°C):"+temperature);
        System.out.println("Humidity:"+humidity+"%");
        System.out.println("Condition:"+des);
        System.out.println("=====================================");
    }
}
