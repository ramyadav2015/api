import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Thinkspeek
{
    public static void main(String[] args)
    {
        try
        {
            // create connection api

            URL url = new URL("https://api.thingspeak.com/channels/875453/feeds.json?api_key=1DSQ7R1XTT1OK0Z1&results=20");
            HttpURLConnection obj = (HttpURLConnection) url.openConnection();
            obj.setRequestMethod("GET");

            //Read data in api

            InputStreamReader in = new InputStreamReader(obj.getInputStream());
            BufferedReader in1 = new BufferedReader(in);
            String st = in1.readLine();

            // Save api data in json file

            FileWriter fw = new FileWriter("Thinkapi.json");
            fw.write(st);
            fw.close();

            // Read data to json file

            JSONParser js = new JSONParser();
            Object ob = js.parse(new FileReader("Thinkapi.json"));
            JSONObject job = (JSONObject)ob;
            JSONObject sob = (JSONObject) job.get("channel");

            // Print id or name

            System.out.println("Extract id is = "+sob.get("id"));
            System.out.println("name is = "+sob.get("name"));
            System.out.println("filed1 is = "+sob.get("field1")+"\n");

            // print field1

            JSONArray arr = (JSONArray) job.get("feeds");
            for(int i=0;i<arr.size();i++)
            {
                JSONObject jno = (JSONObject) arr.get(i);
                System.out.println("Filed1 is = "+jno.get("field1"));
            }
        }

        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
