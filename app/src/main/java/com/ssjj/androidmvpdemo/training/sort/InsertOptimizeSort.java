/**
 *
 */
package com.ssjj.androidmvpdemo.training.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 插入排序优化实现
 *
 * @author weijielu
 */
public class InsertOptimizeSort implements ISort {

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int index = getInsertIndex(array, i, array[i]);

            if (i != index) {
                int j = i;
                int temp = array[i];
                while (j > index) {
                    array[j] = array[j - 1];
                    j--;
                }

                array[j] = temp;
            }
        }
    }

    /**
     * 使用二分查找法返回插入的位置
     *
     * @param array
     * @param value
     * @return
     */
    private int getInsertIndex(int[] array, int length, int value) {
        int low = 0;
        int high = length - 1;
        int middle = -1;

        while (low <= high) {
            middle = (low + high) / 2;

            if (array[middle] > value) {
                high = middle - 1;
            } else if (array[middle] < value) {
                low = middle + 1;
            } else {
                return middle;
            }
        }

        if (array[middle] <= value) {
            middle++;
        }

        return middle;
    }

    public Point[] kClosest(Point[] points, final Point origin, int s) {

        int size = points.length; //数组长度
        List<Point> list = new ArrayList<Point>();
        Point[] ps=new Point[s];
        for (int i = 0; i < size ; i++) {
            list.add(new Point(points[i].x,points[i].y));
        }

        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                        int result =(p1.x-origin.x)*(p1.x-origin.x)+(p1.y-origin.y)*(p1.y-origin.y)-((p2.x-origin.x)*(p2.x-origin.x)+(p2.y-origin.y)*(p2.y-origin.y));
                        if(result==0){
                           result =p1.x-p2.x;
                           if(result==0){
                               return  p1.y-p2.y;
                           }
                           return result;
                        }
                        return  result;

            }
        });
        for (int i = 0; i <s ; i++) {
            ps[i]=list.get(i);
        }

        return ps;
    }

    public static void main(String[] args) {
    }
     class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }
        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

}
