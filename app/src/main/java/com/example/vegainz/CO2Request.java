package com.example.vegainz;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class CO2Request {
    private static String method = "GET";
    private static String uri = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator";

    public static void getData(){
        BufferedReader br = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();
            System.out.println(status);

            /*Map<String, Integer> parameters = new HashMap<>();
            parameters.put("query.diet", diet);
            parameters.put("query.lowCarbonPreference", lowCarbonPreference);
            parameters.put("query.beefLevel", beef);
            parameters.put("query.fishLevel", fish);
            parameters.put("query.porkPoultryLevel", pork);
            parameters.put("query.dairyLevel", dairy);
            parameters.put("query.cheeseLevel", cheese);
            parameters.put("query.riceLevel", rice);
            parameters.put("query.eggLevel", eggs);

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();


            String diet, boolean lowCarbonPreference,
                                 int beef, int fish, int pork, int dairy,
                                 int cheese, int rice, int eggs
             */

        } catch (Exception e) {



        } finally {


        }


    }


}
