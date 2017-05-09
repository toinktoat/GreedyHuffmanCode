/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import javax.swing.JOptionPane;



public class Huffman {

      //INITIALIZATIONS

    static ArrayList<Node> string = new ArrayList<Node>() ;
    static ArrayList<Node> characters = new ArrayList<Node> ();
    static TreeMap<Character,String> tree = new TreeMap<>();
    static String input ;
    static char[] temp ;
    static String Encoded;
    static String Decoded;

    static ArrayList<Node> string2 = new ArrayList<Node>() ;
    static ArrayList<Node> characters2 = new ArrayList<Node> ();
    static String input2 ;
    static char[] temp2 ;
    static String Encoded2;
    static String Decoded2;


    public static void main(String[] args) {


        settingFrequency();
        //BTETBA3 EL INITIAL STRING , KOL CHARACTER BEL FREQUENCY BETA3TO
        System.out.println("Karakter dengan Frekuensi Awal");
        for(int i = 0 ; i <string.size();i++ ){
            System.out.println("'"+string.get(i).data + "'"+ "  :  " + string.get(i).freq);
            System.out.println("---------------");
        }
        makingTree();
        //binary codes
        String tString ="";
        Node nFinal = string.get(0);
        Node n =  nFinal ;
        System.out.println(string.size());
        //ESTABLISH THE TREE
        Traverse(nFinal , tString);
        Travers(nFinal);
        //PRINT THE CHARACTERS OF THE STRING

        System.out.println("======================");
        System.out.println("Karakter setelah Encoding : ");
        System.out.println();
        for(int j=0;j<characters.size();j++){
            System.out.println(tree.get(characters.get(j).data.charAt(0)) + " : " + characters.get(j).data);
         }
        System.out.println("Pesan Awal adalah : " + input);

        Encoded = "";
        for(int j = 0 ; j < input.length() ; j++){
            Encoded = Encoded + tree.get(input.charAt(j));
        }

        System.out.println("Pesan setelah Encoding : "  + Encoded);
        Decoded = Encoded ;
        decode(Decoded,n);

  ///////////////////////////////////////////////////////////////////////////////////////////////
  ///////////////////////////////////// SECOND INPUT ///////////////////////////////////////////

        settingFrequency2();
        System.out.println("===============================");
        System.out.println("Bagian Masukan Kedua");
        if(checkTheSame() == true){
            System.out.println("Karakter dari Input Kedua dengan Frekuensi Awal mereka");
            for(int i = 0 ; i <string2.size();i++ ){
            System.out.println("'"+string2.get(i).data + "'"+ "  :  " + string2.get(i).freq);
            System.out.println("---------------");
            }
             Encoded2 = "";
        for(int j = 0 ; j < input2.length() ; j++){
            Encoded2 = Encoded2 + tree.get(input2.charAt(j));
        }
            System.out.println("Masukan Encoded 2 adalah : " + Encoded2);

            Decoded2 = "";
            Decoded2 = Encoded2 ;
            decode(Decoded2,n);
        }

        else {
            System.out.println("ERROR: \nAda karakter dalam input kedua yang tidak sesuai dengan karakter di yang pertama ");
        }




    }


    public static void settingFrequency(){
        input = JOptionPane.showInputDialog("Masukkan String untuk diencoded");
        //PUTING THE STRING INTO ARRAY OF CHARACTERS
        temp = input.toCharArray();
        //ADDING EACH CHARACTER TO ARRAYLIST "STRING" WITH NO REPETITIONS
        for(int i=0;i<temp.length;i++){
            String t = temp[i] + "";
            Node n = new Node (t);
            if(!contains(string,t)){
            string.add(n);
            characters.add(n);
            }
            else if(contains(string,t)) {
                int index = getIndex(string,t);
                string.get(index).freq++;

            }
        }
    }
    public static void settingFrequency2(){
        input2 = JOptionPane.showInputDialog("Masukkan String untuk diencoded");
        //PUTING THE STRING INTO ARRAY OF CHARACTERS
        temp2 = input2.toCharArray();
        //ADDING EACH CHARACTER TO ARRAYLIST "STRING" WITH NO REPETITIONS
        for(int i=0;i<temp2.length;i++){
            String t = temp2[i] + "";
            Node n = new Node (t);
            if(!contains(string2,t)){
            string2.add(n);
            characters2.add(n);
            }
            else if(contains(string2,t)) {
                int index = getIndex(string2,t);
                string2.get(index).freq++;

            }
        }
    }
    public static boolean checkTheSame(){
        boolean same = true ;
        for(int i=0;i<temp2.length;i++){
            if(!contains(characters,temp2[i]+"")){
                same = false;
                break;
            }
        }
        return same ;
    }
    public static void makingTree(){
        //tree
        //ADDING EACH TWO CHARACTERS TOGETHER UNTIL YOU END UP WITH ONLY ONE WORD
        Collections.sort(string);
        while(string.size() > 1){

            Node n = (Node) string.get(0) ;
            Node m = (Node) string.get(1);
            int tempFreq = n.freq + m.freq ;
            Node nw = new Node (n.data+m.data);
            nw.addChild(n, m);
            string.remove(0);
            string.remove(0);
            string.add(nw);
            nw.freq = tempFreq;
            Collections.sort(string);
                    }
    }
    //SEARCHING INSIDE THE TREE AND GETTING THE CODES OF THE CHARACTERS
    public static void Traverse(Node n ,String s){
        if(n.left != null){
            Traverse(n.left,s+"0");
        }
        if (n.right!=null){
            Traverse(n.right,s+"1");
        }
        else if ( n.right == null  && n.left==null)
        {
            tree.put(n.data.charAt(0), s);
            }
        }

        public static void Travers(Node n){
        if(n.left != null){
            Travers(n.left);
        }
         if ( n.right == null  && n.left==null)
        {
            System.out.println(n.data);
            }
        if (n.right!=null){
            Travers(n.right);
        }

        }
    //FUNCTION TO CHECK 
    public static boolean contains(ArrayList<Node> list, String name) {
    for (Node item : list) {
        if (item.getData().equals(name)) {
            return true;
        }
    }
    return false;
}
     public static int getIndex(ArrayList<Node> list, String name) {
    int c=0;
         for(int i =0 ; i <list.size();i++){
             if(list.get(i).data.equals(name)){
                 break;
             }
             c++;
    }
    return c;
    }
     public static void decode(String s,Node l){
         Decoded="";
          Node n = l;
         for(int i = 0;i<s.length();){
            Node node = n ;
            while(node.left!=null && node.right !=null && i < s.length()){
                if((s.charAt(i)) == '1')
                    node = node.right;
                else
                    node = node.left;
                i++;

            }
            Decoded += node.data;
         }
         System.out.println("Decoded Text Adalah = " + Decoded);
     }



}
