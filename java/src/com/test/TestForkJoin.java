package com.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class TestForkJoin {


    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(5);
        String[] str = new String[]{"test1","job1","java","C++","C","python","test","test2","test3","test4","test5","test6"};
        Task task = new Task(Arrays.asList(str),0,str.length);
        Future<Map<String,String>> future = pool.submit(task);
        try {
            Map<String, String> stringStringMap = future.get();
            System.out.println(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(2);
    }



    static class Task extends RecursiveTask<Map<String,String>> {


        private static final int THREAD_HOLD = 3;

        private List<String> list;
        private int start;
        private int end;

        public Task(List<String> list,int start,int end){
            this.list = list;
            this.start = start;
            this.end = end;
        }


        @Override
        protected Map<String, String> compute() {
            Map<String,String> map = new HashMap<>();
            if (end - start > THREAD_HOLD) {
                for (int i = start; i < end; i++) {
                    for(int j = 0; j < 2;j++) {
                        map.put(list.get(i) + j,"j");
                    }
                }
            } else {
                int middle = (end - start) / 2;
                Task leftTask = new Task(list,start,middle + 1);
                Task rightTask = new Task(list,middle + 1,end);
                invokeAll(leftTask,rightTask);
                try {
                    map.putAll(leftTask.get());
                    map.putAll(rightTask.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            return map;
        }
    }


}
