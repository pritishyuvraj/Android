import java.io.*;
import java.net.*;
import java.util.*;

// class Test {
//     public static void main(String[] args) throws Exception {
//         URL url = new URL("http://localhost:8000/todo/api/v1.0/tasks");
//         Map<String,Object> params = new LinkedHashMap<>();
//         //params.put("name", "Freddie the Fish");
//         params.put("title", "Read a book");
//         //params.put("email", "fishie@seamail.example.com");
//         //params.put("reply_to_thread", 10394);
//         //params.put("message", "Shark attacks in Botany Bay have gotten out of control. We need more defensive dolphins to protect the schools here, but Mayor Porpoise is too busy stuffing his snout with lobsters. He's so shellfish.");

//         StringBuilder postData = new StringBuilder();
//         for (Map.Entry<String,Object> param : params.entrySet()) {
//             if (postData.length() != 0) postData.append('&');
//             postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
//             postData.append('=');
//             postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
//         }
//         byte[] postDataBytes = postData.toString().getBytes("UTF-8");

//         HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//         conn.setRequestMethod("POST");
//         // conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//         // conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//         conn.setDoOutput(true);
//         conn.getOutputStream().write(postDataBytes);

//         Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

//         for (int c; (c = in.read()) >= 0;)
//             System.out.print((char)c);
//     }
// }

class Test2{
	public static void main(String[] args) throws Exception{
		Webb webb = Webb.create();
		webb.post("http://example.com/index.php")
        .param("param1", "a")
        .param("param2", "b")
        .param("param3", "c")
        .ensureSuccess()
        .asVoid();
	}
}