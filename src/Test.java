package src;

import java.util.*;

public class Test {

    public class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            // int[] result = new int[numCourses];
            // int[] index = new int[numCourses];

            // if(!canFinish(numCourses,prerequisites))
            // return new int[0];

            // for(int i = 0; i < numCourses; i ++){
            // result[i] = i;
            // index[i] = i;
            // }

            // boolean flag = false;

            // do{
            // flag = false;
            // for(int i = 0; i < prerequisites.length; i ++){
            // int pre = prerequisites[i][1];
            // int aft = prerequisites[i][0];

            // if(index[pre]>index[aft]){
            // flag = true;
            // int t = result[index[pre]];
            // result[index[pre]] = result[index[aft]];
            // result[index[aft]] = t;

            // t = index[pre];
            // index[pre] = index[aft];
            // index[aft]= t;
            // }
            // }
            // }while(flag);

            // return result;

            // 随机交换顺序不对的序列直到所有的顺序都正确，但是Compile time limit exceeded.

            List<Set<Integer>> g = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                g.add(new HashSet<Integer>());
            }

            for (int i = 0; i < prerequisites.length; i++) {
                g.get(prerequisites[i][1]).add(prerequisites[i][0]);
            }

            int[] inDegree = new int[numCourses];

            for (int i = 0; i < numCourses; i++) {
                Set<Integer> set = g.get(i);
                Iterator<Integer> it = set.iterator();
                while (it.hasNext()) {
                    inDegree[it.next()]++;
                }
            }

            List<Integer> result = new ArrayList<Integer>();

            for (int i = 0; i < numCourses; i++) {
                int j = 0;
                for (; j < numCourses; j++) {
                    if (inDegree[j] == 0) {
                        result.add(j);
                        break;
                    }
                }

                if (j == numCourses)
                    return new int[0];

                inDegree[j] = -1;

                Set<Integer> set = g.get(j);
                Iterator<Integer> it = set.iterator();
                while (it.hasNext()) {
                    inDegree[it.next()]--;
                }
            }

            int[] ret = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                ret[i] = result.get(i);
            }

            return ret;
        }
    }
}