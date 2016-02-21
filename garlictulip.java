/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package garlictulip;
//import com.google.gson.*;
import java.nio.charset.*;
import java.io.*;
import java.net.*;
/**
 *
 * @author Typhoid
 */
public class Garlictulip {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Garlictulip spaghetti = new Garlictulip();
        spaghetti.performJsonPost();
    }
    
    
     private String getRequestCookie() throws IOException {
        // URL myUrl = new URL("http://www.hccp.org/cookieTest.jsp");
      /*  URLConnection urlConn = null;
        try {
            urlConn = url.openConnection(currentProxy);
            urlConn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        String cookies = "";
        URL myUrl = new URL("https://www.facebook.com/ajax/bz");
        URLConnection urlConn = myUrl.openConnection();
        urlConn.connect();
        String headerName=null;
        for (int i=1; (headerName = urlConn.getHeaderFieldKey(i))!=null; i++) {
            if (headerName.equals("Set-Cookie")) {
                String cookie = urlConn.getHeaderField(i);
                cookie = cookie.substring(0, cookie.indexOf(";"));
                String cookieName = cookie.substring(0, cookie.indexOf("="));
                String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
                cookies = cookies+cookieName+"="+cookieValue+"; ";
                //cookieNameList.add(cookieValue);
                System.out.println("da cookies:" + cookies);

                return cookies;
            }
        }
        // must have failed yo
        cookies = "failed";
        return cookies;
    }
    private String performJsonPost() {
        try {
            String cookies = getRequestCookie();
            String query = "email=EMAIL%40EMAIL.com&pass=PASSWORD&lsd=AVrVVnf1&default_persistent=0&timezone=&lgnrnd=132329_hCSS&lgnjs=n&locale=tr_TR";
            URL urls = new URL("https://www.facebook.com/login.php?login_attempt=1");

            final HttpURLConnection conn = (HttpURLConnection)urls.openConnection();
            final byte[] payloadAsBytes = query.getBytes(Charset.forName("UTF-8"));
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Cookie", "reg_fb_gate=https%3A%2F%2Fwww.facebook.com%2F; reg_fb_ref=https%3A%2F%2Fwww.facebook.com%2F; reg_ext_ref=deleted; Max-Age=0; datr=0aFzVK8Fu0Gl7M8cn_6TSqlZ");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", "" + payloadAsBytes.length);
            conn.setRequestProperty("Content-Language", "en-US");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            final DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
            outStream.write(payloadAsBytes);
            //   outStream.write(payloadAsBytes);
            outStream.flush();
            outStream.close();

            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;

          /*  catch (Exception e2) {
                //inStream = conn.getErrorStream();
            }*/
                final StringBuilder response = new StringBuilder();
                final byte[] buffer = new byte[1024];
                int bytesRead;
                //System.out.println(inStream.toString() + "WHAT ARE U SAYING BRO");
                //while ((bytesRead = inStream.read(buffer)) > 0) {
                //response.append(new String(buffer, "UTF-8").substring(0, bytesRead));
                while ((line = rd.readLine()) != null) {
                    System.out.println(line);
                    response.append(line);
                    //  inStream = conn.getInputStream();
                }
                return response.toString();
            }catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        //    System.out.println("Response!:"+response.toString());
          //  return response.toString();
    }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
