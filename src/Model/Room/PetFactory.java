package Model.Room;

import Model.Buff;
import Model.Buffs.*;
import Model.Effects.ApplyBuff;
import Model.Effects.Block;
import Model.Effects.Damage;
import Model.Effects.Effect;
import Model.Enemy;
import Model.Pet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The type Pet factory.
 */
public class PetFactory
{
    /**
     * Gets all pets.
     *
     * @return the all pets
     */
    public static ArrayList<Pet> getAllPets()
    {
        ArrayList<Pet> result = new ArrayList<Pet>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("pets.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray pets = (JSONArray) obj;
            int count = 0;
            for (Object pet:pets)
            {
                count++;
                JSONObject toSet = (JSONObject) pet;
                //created an enemy
                Pet toAdd = new Pet((String) toSet.get("name"));
                toAdd.setDescription((String) toSet.get("description"));
                ArrayList<Buff> buffs = new ArrayList<Buff>();
                //Attack pattern
                Queue<ArrayList<Effect>> effects = new LinkedList<>();
                toAdd.setEffects(effects);

                Queue<ArrayList<Integer>> targets = new LinkedList<>();
                toAdd.setTargets(targets);
                JSONArray pattern2d = (JSONArray) toSet.get("pattern");

                JSONArray buffList = (JSONArray) toSet.get("buffs");
                for(Object buff:buffList)
                {
                   String type = (String) buff;


                }
                for( Object row: pattern2d)
                {
                    ArrayList<Effect> oneTurn = new ArrayList<Effect>();
                    effects.add(oneTurn);

                    ArrayList<Integer> oneTurnTarget = new ArrayList<Integer>();
                    targets.add(oneTurnTarget);
                    JSONArray line = (JSONArray) row;
                    //it will become more generalized
                    long attack = (long) line.get(0);
                    long defense = (long) line.get(1);

                    if(attack > 0)
                    {
                        //create an attack effect
                        Damage damage = new Damage((int) attack,null,null);
                        oneTurn.add(damage);
                        oneTurnTarget.add(0);
                    }
                    if(defense > 0)
                    {
                        Block block = new Block((int) defense,null);
                        oneTurn.add(block);
                        oneTurnTarget.add(1);
                    }
                    for(int k=2; k<line.size(); k = k + 2)
                    {
                        long buffAmount = (long) line.get(k+1);
                        long locationBuff = (long) line.get(k);
                        System.out.println(locationBuff);
                        String type = (String) ((JSONArray)toSet.get("buffs")).get((int)locationBuff);
                        if(type.equals("strength"))
                        {
                            Strength strength = new Strength((int) buffAmount);
                            ApplyBuff apply = new ApplyBuff(strength,null);
                            oneTurn.add(apply);
                            oneTurnTarget.add(1);
                        }
                        if(type.equals("weak"))
                        {
                            Weak weak = new Weak((int) buffAmount );
                            ApplyBuff apply = new ApplyBuff(weak,null);
                            oneTurn.add(apply);
                            oneTurnTarget.add(0);
                        }
                        if(type.equals("vulnerable"))
                        {
                            Vulnerable vulnerable = new Vulnerable((int) buffAmount );
                            ApplyBuff apply = new ApplyBuff(vulnerable,null);
                            oneTurn.add(apply);
                            oneTurnTarget.add(0);
                        }
                        if(type.equals("artifact"))
                        {
                            Artifact artifact = new Artifact( (int) buffAmount) ;
                            ApplyBuff apply = new ApplyBuff(artifact,null);
                            oneTurn.add(apply);
                            oneTurnTarget.add(1);
                        }
                        if(type.equals("buffer"))
                        {
                            Buffer buffer = new Buffer((int) buffAmount );
                            ApplyBuff apply = new ApplyBuff(buffer,null);
                            oneTurn.add(apply);
                            oneTurnTarget.add(1);
                        }
                    }
                }
                result.add(toAdd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
