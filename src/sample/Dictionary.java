package sample;

import java.io.*;
import java.util.*;

public class Dictionary {
    Map<Character, LinkedList<String>> dictionary = new TreeMap<>();

    public Dictionary(){
        setDictionary();
    }
    public void setDictionary(){
        Character value;
        try{
            Scanner scanner = new Scanner(new File("sowpods.txt")); //the dictionary file

            //initializes the tree
            for(int x = 0; x < 26; x++){
                dictionary.put((char)('A' + x), new LinkedList<>());
            }

            //populates the tree with all the words from the dictionary text file
            while (scanner.hasNext()) {
                String word = scanner.nextLine();
                value = word.charAt(0);
                dictionary.get(value).addLast(word);
            }
        }
        catch(FileNotFoundException e){
            System.out.println(e);
        }
    }


    public void printMap() {
        for (Map.Entry entry : dictionary.entrySet()) {
            System.out.println("Key: " + entry.getKey() + "\tValue: " + entry.getValue());
        }
    }

    public boolean dictionaryChecker(String word) {
        word = word.toUpperCase();
        Character letter = word.charAt(0);
        LinkedList<String> list = dictionary.get(letter);

        for (String str : list) {
            if(word.equals(str)){
                return true;// word found return true otherwise it ll return false
            }
        }
        return false;
    }

    // ================== MAIN ==================
    public static void main(String[] args) throws IOException {
        long startTime = 0;
        long elapsedTime = 0;

        Dictionary dic = new Dictionary();
        dic.setDictionary();

        System.out.println(dic.dictionary.get('Z').size());

        startTime = System.currentTimeMillis();

        System.out.println(dic.dictionaryChecker("ZEALOUSNESSES"));

        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("The time taken: " + elapsedTime);
    }
}

