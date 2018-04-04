package com.ssjj.androidmvpdemo.training;

/**
 * Created by songyu on 2018/4/3.
 */

public class LeetCode {

    private void add(LinkedNodeDemo.Node a , LinkedNodeDemo.Node b){
        int m = getNum(a);
        int n = getNum(b);
        int s = m+n ;


    }

    private int getNum(LinkedNodeDemo.Node a ){
        if(a == null){
            return  0 ;
        }
        return (int)a.value+10*getNum(a.next);

    }

    private LinkedNodeDemo.Node getNode(int a ){
        char[] chars = String.valueOf(a).toCharArray();
        LinkedNodeDemo.Node node =new LinkedNodeDemo.Node();

        for (int i = chars.length-1; i >=0 ; i--) {
            node.value = i ;
            node.next = node ;
        }

        return  node ;
    }






}
