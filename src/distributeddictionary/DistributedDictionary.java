
package distributeddictionary;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DistributedDictionary {

    static Peer[] p;
    static int TTL=7;

    public static void main(String[] args){
        
        int n_peers;
        
        do{
          String n_peers_string = JOptionPane.showInputDialog(null, "Write the number of nodes: ");
          n_peers = Integer.parseInt(n_peers_string);
        }while(n_peers<1);
        
        p = new Peer[n_peers];

        for(int i=0; i<p.length; i++){
            
            p[i] = new Peer(i);
            
            int n_words;
            
            do{
              String n_words_string = JOptionPane.showInputDialog(null, "Write the number of words of Peer: "+i);
              n_words = Integer.parseInt(n_words_string);
            }while(n_words<1);
            
            for(int j=0; j<n_words; j++){
                
                String word;
                
                do{
                
                    word = JOptionPane.showInputDialog(null, "Write "+(j+1)+" word: ");
                
                }while(word.isEmpty());
                
                p[i].addWord(word);
                
                String meaning;
                
                meaning = JOptionPane.showInputDialog(null, "Write "+(j+1)+" your meaning: ");
                
                p[i].addMeaning(meaning);
                
            }

            int n_adjacents;
            
            do{
              String n_adjacents_string = JOptionPane.showInputDialog(null, "Write the number of adjacencys: ");
              n_adjacents = Integer.parseInt(n_adjacents_string);
            }while(n_adjacents<1);
            
            for(int j=0; j<n_adjacents; j++){
                
                int adjacency;
                
                do{
                    String adjacency_string = JOptionPane.showInputDialog(null, "Write "+(j+1)+" adjaceny: ");
                    adjacency = Integer.parseInt(adjacency_string);
                }while(n_adjacents<1);
                
                p[i].addAdjacent(adjacency);
                
            }
            
        }
        
        for(int i=0; i<p.length; i++){
            System.out.println("Peer "+i);
            System.out.println("Adjancecys");
            for(int j=0; j<p[i].adjacents.size(); j++){
                System.out.println("Adjacency with "+p[i].adjacents.get(j));
            }
        }
        
        String palavra = "";

        while(!palavra.equalsIgnoreCase("-1")){

            do{
                
                palavra = JOptionPane.showInputDialog(null, "Write the word: ");
                System.out.println("Your word is: " + palavra);
                
            }while(palavra.equals(""));

            int i=0, j=0;
            
            while (i!=-1){
                
                    int indiceRetornado = -1;
                    indiceRetornado = p[j].searchWord(palavra);

                    if(indiceRetornado!=-1){
                        
                        for(int n = 0; n < p.length; n++){
                            p[n].status=false;
                        }
                        
                        TTL = 7;
                        
                        System.out.println("Peer "+p[j].id);
                        JOptionPane.showMessageDialog(null, "Peer " + p[j].id + " says:  " + p[j].getMeaning(indiceRetornado));
                        break;
                    }else{
                        if(TTL>0){
                            j = random(p[i]);
                            if(!p[j].status){
                                p[j].status=true;
                                TTL--;
                            } 
                        }
                        else{
                            
                            for(int n = 0; n < p.length; n++){
                                p[n].status=false;
                            }
                        
                            TTL = 7;
                            
                            JOptionPane.showMessageDialog(null, "Word not found!");
                            break;
                        }
                    }
                    //
                    
                    i = j;
            }
        }
    }

    static int random(Peer peer) {
        
        ArrayList<Integer> nextPeers = new ArrayList<>();
        Random randomGenerator = new Random();

        System.out.println("Peer "+peer.id);
        
        for (int i = 0; i < peer.adjacents.size(); i++) {
            nextPeers.add(peer.getAdjacent(i));
        }

        // First Index of Vector.

        return ((Integer) nextPeers.get(randomGenerator.nextInt(peer.adjacents.size())));

    }
}