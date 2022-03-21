package com.company;
import java.io.*;
import java.util.*;


public class Main {
    private final static String FILE_URL = "C:\\LICENCE 2\\S4\\Algorithmique 1\\dico.txt";
    private static final Hashtable<ArrayList<Character>, ArrayList<String>> dictionary = new Hashtable<>();

    public static void main(String[] args) {

        //test
        search(creationDictionary(), "giseleherve"); //giseleherve
        //System.out.println(search(creationDictionary(), "duong")); //mongoliemonod
        //System.out.println(creationDictionary().values());
        //System.out.println(findComplement(sortList("giseleherve"),"gisele"));
        //System.out.println(sortList("mongolie"));
    }

    public static ArrayList<Character> sortList(String line) {
        ArrayList<Character> newList = splitString(line);
        Collections.sort(newList);
        return newList;
    }

    //Créer un dictionnaire
    public static Hashtable<ArrayList<Character>, ArrayList<String>> creationDictionary() {
        try {
            File file = new File(FILE_URL);
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            //ajouter des mots en dictionnaire
            String line;
            while ((line = reader.readLine()) != null) {
                ArrayList<String> value = new ArrayList<>();
                line = line.toLowerCase();
                ArrayList<Character> key = sortList(line);
                if (dictionary.containsKey(key)) {
                    dictionary.get(key).add(line);
                } else {
                    value.add(line);
                    dictionary.put(key, value);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error : unable to record file :" + ex);
        }
        return dictionary;
    }

    //Découper des suites
    public static ArrayList<Character> splitString(String word) {
        ArrayList<Character> key = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            key.add(word.charAt(i));
        }
        return key;
    }

    public static ArrayList<Character> findComplement(ArrayList<Character> request, String word) {   // request : mot requête, w2 : un mot en dictionnaire
        ArrayList<Character> w2 = splitString(word);
        ArrayList<Character> rest = new ArrayList<>();
        Collections.sort(request);
        Collections.sort(w2);
        int i = 0, j = 0, k = 0;

        while (i < request.size() && j < w2.size()) {
            if (request.get(i) == w2.get(j)) {
                i++;
                j++;
            }

            if (!(i < request.size() && j < word.length())) break;
            if (request.get(i) < w2.get(j)) {
                rest.add(k, request.get(i));
                i++;
                k++;
            }
            if (!(i < request.size())) break;

            if (request.get(i) > w2.get(j)) {
                j++;
            }
            if (!(j < w2.size())) break;

        }
        if (j < w2.size()) {
            return rest;
        } else {
            while (i < request.size()) {
                rest.add(k, request.get(i));
                i++;
                k++;
            }
        }
        return rest;
    }

    public static void search(Hashtable<ArrayList<Character>, ArrayList<String>> dict, String request) {
        ArrayList<Character> req = sortList(request);
        for (ArrayList<Character> key : dict.keySet()) {
            if (req.containsAll(key)){
                ArrayList<Character> complement = findComplement(req, String.valueOf(dict.get(key)));
                if (dict.containsKey(complement)){
                    if (verifyList(req,key,complement)){
                        System.out.println(request + " - " + dict.get(key) + " = " + dict.get(complement));
                    }
                }
            }
        }
    }
    public static boolean verifyList(ArrayList<Character> request,ArrayList<Character> key,ArrayList<Character> complement){
        ArrayList<Character> listConcatenate = new ArrayList<>(key);
        listConcatenate.addAll(complement);
        Collections.sort(listConcatenate);
        return listConcatenate.equals(request);
    }
}
