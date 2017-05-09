/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

/**
 *
 * @author okesip
 */
import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable {
    
    public Node left ;
    public Node right ;
    public String data ;
    public int freq = 0 ;
 
    public Node(String data) {
        this.data 
                = data;
        freq = 1;
    }

    public Node(String data, int freq) {
        this.data = data;
        this.freq = freq;
        
    }

    

    public void addChild(Node left,Node right) {
        if(left.freq < right.freq){
            this.left=left;
            this.right=right;
        }
        else{
            this.right=left;
            this.left=right;
        }
    }

    public String getData() {
        return this.data;
    }
    
    public Node getNode() {
        return this;
    }
    
    public int getFreq() {
        return this.freq;
    }

    public void setData(String data) {
        this.data = data;
    }
    
     public void setFreq (int data) {
        this.freq = data;
    }

   

    @Override
    public int compareTo(Object o) {
    int compareage=((Node)o).getFreq();
        /* For Ascending order*/
        return this.freq-compareage;    
    
    }
    
}
