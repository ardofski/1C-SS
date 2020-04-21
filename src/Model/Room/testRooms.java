package Model.Room;

import java.io.*;
import java.util.ArrayList;

import Model.Card;
import org.json.simple.*;

public class testRooms
{
    public static void main(String [] args)
    {
        System.out.println("cards");
        writeRooms();
    }


    public static void writeRooms()
    {
        JSONArray acts = new JSONArray();
        //Create act1
        JSONObject act1 = new JSONObject();
        JSONArray enemyRooms1 = new JSONArray();
        JSONArray merchantRooms1 = new JSONArray();
        act1.put("enemyRooms", enemyRooms1);
        act1.put("merchantRooms", merchantRooms1);

        JSONObject enemyRoom = new JSONObject();
        enemyRoom.put("type","Monster");

        JSONObject enemyRoom1 = new JSONObject();
        enemyRoom1.put("type","Elite");

        enemyRooms1.add(enemyRoom);
        enemyRooms1.add(enemyRoom1);

        //Create act2
        JSONObject act2 = new JSONObject();
        JSONArray enemyRooms2 = new JSONArray();
        JSONArray merchantRooms2 = new JSONArray();
        act2.put("enemyRooms", enemyRooms2);
        act2.put("merchantRooms", merchantRooms2);

        JSONObject enemyRoom3 = new JSONObject();
        enemyRoom3.put("type","Monster");

        JSONObject enemyRoom4 = new JSONObject();
        enemyRoom4.put("type","Elite");

        enemyRooms2.add(enemyRoom3);
        enemyRooms2.add(enemyRoom4);

        acts.add(act1);
        acts.add(act2);
        try (FileWriter file = new FileWriter("rooms.json")) {

            file.write(acts.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


