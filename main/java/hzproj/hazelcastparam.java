package hzproj;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.util.Random;
public class hazelcastparam {
    public static void main(String[] args){
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        IMap<Integer, Integer> map = hazelcastInstance.getMap("randomNumbers");

        int[] counts = {20000, 100000};

        for(int count : counts){
            long startTime = System.currentTimeMillis();
            putRandomNumbers(map, count);
            long endTime = System.currentTimeMillis();
            System.out.println(count + " sayı ekleme süresi: " + (endTime - startTime) + " ms");
        }
    }
    public static void putRandomNumbers(IMap<Integer, Integer> map,int count){
        Random rand = new Random();
        for(int i =0;i<count;i++){
            map.put(i,rand.nextInt(100000));
        }
    }
}
