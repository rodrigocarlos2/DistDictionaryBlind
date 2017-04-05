/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributeddictionary;

/**
 *
 * @author Ricardo, update of Rodrigo
 */
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ricardo on 30/03/2017.
 */
public class Peer {
    
    int id;
    ArrayList <String> list_words = new ArrayList <String>();
    ArrayList <String> list_meanings = new ArrayList<String>();
    ArrayList<Integer> adjacents = new ArrayList<Integer>();
    boolean status;

    public Peer(int id){
        this.id = id;
        status=false;
    }

    public int searchWord(String palavra){

        for(int i = 0; i<list_words.size(); i++){
            if(list_words.get(i).equalsIgnoreCase(palavra)){
                return i;
            }
        }
        return -1;
    }

    public String getMeaning(int index) {

        return list_meanings.get(index);
    }

    public void addWord(String s){
        if (s!=null)
            list_words.add(s);
    }

    public void addMeaning(String s){
        if (s!=null)
            list_meanings.add(s);
    }
    
    public void addAdjacent(int d){
        adjacents.add(d);
    }
    
    public int getAdjacent(int i){
        return adjacents.get(i);
    }

}