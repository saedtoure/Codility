// you can also use imports, for example:
 import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int[] solution(int K, int M, int[] A) {
        // write your code in Java SE 8
        int N = A.length;
        List<Integer> arl = new ArrayList<Integer>();
        int[] count = new int[M+2];
        for(int i =0; i<K; i++){ // add 1 to the first K values
            A[i]++;
        }
        //set the count for each value
        for(int a:A){
            count[a]++;
        }
        for(int i =1; i<M+2; i++){//values start from 1<-->M+1
            if(count[i]>N/2){
                arl.add(i);//i is a leader
                break; //there is only one leader
            }
        }
        for(int i =0; i<N-K; i++){//here we subtract one from the start of the K sequence and add one after the end "-kkkkkkkkkk+" and update the counter
            count[A[i]]--;//we are removing one so decrease the counter 
            A[i]--;//remove one so we have new value
            count[A[i]]++;// update the new value counter 
            
            //the same for the new element on the end of the K
            count[A[i+K]]--;
            A[i+K]++;
            count[A[i+K]]++;
            
            //now we check if that update gave us a new leader
            //a new leader could only be one of the two values the start or the end of the K sequence because they are the only two that have changed
            if(count[A[i]]>N/2){
                arl.add(A[i]);
            }
            if(count[A[i+K]]>N/2){
                arl.add(A[i+K]);
            }
        }
        //remove duplicates 
        Set<Integer> set = new LinkedHashSet<>();
        set.addAll(arl);
        arl.clear();
        arl.addAll(set);
        //sort the arl and create an int array
        int[] ret = new int[arl.size()];
        Collections.sort(arl);
        for(int i =0; i<ret.length; i++){
            ret[i]=arl.get(i);
        }
        return ret;
    }
}
