package com.company;
import java.io.*;
import java.util.*;

public class Main {
    private final static String FILE_URL = "C:\\LICENCE 2\\S4\\Algorithmique 1\\dico.txt";
    private static final Hashtable<String,ArrayList<Character>> dictionary = new Hashtable<>();

    public static void main(String[] args){

        //test

        System.out.println(search(creationDictionary(),"apennins"));
        //System.out.println(creationDictionary());
//---------------------------------------------------------------
/*  public static void main(String[] args) throws IOException {
        File file = new File(FILE_URL);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        //ajouter des mots en dictionnaire
        String line;
        while((line = reader.readLine())!= null)
        {
            dictionary.put(line.toLowerCase(Locale.ROOT),splitString(line.toLowerCase(Locale.ROOT)));
        }
        System.out.println(dictionary);

 */
    }
    //Créer un dictionnaire
    public static Hashtable<String,ArrayList<Character>> creationDictionary(){
        try {
            File file = new File(FILE_URL);
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            //ajouter des mots en dictionnaire
            String line;
            while((line = reader.readLine())!= null)
            {
                dictionary.put(line.toLowerCase(Locale.ROOT),splitString(line.toLowerCase(Locale.ROOT)));
            }
        }
        catch (IOException ex){
            System.out.println("Error : unable to record file :" + ex);
        }
        return dictionary;
    }
    //Découper des suites
    public static ArrayList<Character> splitString(String word){
         ArrayList<Character> value = new ArrayList<>();
        for (int i = 0; i < word.length(); i++){
            value.add(word.charAt(i));
        }
        return value;
    }
    public static ArrayList<Character> findComplement(String request_1, String word){   // request : mot requête, w2 : un mot en dictionnaire
        ArrayList<Character> request = splitString(request_1);
        ArrayList<Character> w2 = splitString(word);
        ArrayList<Character> rest = new ArrayList<>();
        int i =0, j=0, k=0;
        while (i < request.size() && j < w2.size()){
            if ((request.get(i)).equals(w2.get(j))){
                //rest.add(request.get(i));
                i++;
                j++;

            }
            if (request.get(i) < w2.get(j)){
                rest.add(k,request.get(i));
                i++;
                k++;
            }
            else {
                while (i < request.size()){
                    rest.add(k,request.get(i));
                    i++;
                    k++;
                }
            }
        }
        return rest;
    }
    public static ArrayList<String> search(Hashtable<String, ArrayList<Character>> dict, String request){
        ArrayList<String> content = new ArrayList<>();
        for (String element :dictionary.keySet()){
            if (dictionary.containsValue(findComplement(request,element))){
                if (checkElementsOfValue(dictionary.get(element),findComplement(request,element))){
                    content.add(element);
                }
            }
            else {
                System.out.println("Error : not find the word");
                break;
            }
        }
        return content;
    }
    public static boolean checkElementsOfValue(ArrayList<Character> list1, ArrayList<Character> list2){
        for (int i = 0; i < list1.size(); i++){
            if (!list1.contains(list2.get(i))){
                return false;
            }
        }
        return true;
    }




}
