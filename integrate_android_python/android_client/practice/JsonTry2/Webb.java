import java.io.*;
import java.net.*;
import java.util.*;

public class Webb{

  public static void main(String[] args) throws Exception{
    String Data = "title=HelloWorldsname=PritishYuvraj";
       URL url = new URL("http://localhost:8000/todo/api/v1.0/tasks");
       HttpURLConnection con = (HttpURLConnection) url.openConnection();
       con.setRequestMethod("POST");
       con.setDoOutput(true);
       con.getOutputStream().write(Data.getBytes("UTF-8"));
       //con.getInputStream();
       Reader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        for (int c; (c = in.read()) >= 0;)
            System.out.print((char)c);
   }
}

