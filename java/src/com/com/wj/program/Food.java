package com.com.wj.program;

/**
 * 构造器参数过多
 *      方式1：使用JavaBean
 *          想设置什么就通过set设置 不太好
 *      方式2：使用Builder模式  见FoodBuilder  采用链式调用
 */
public class Food {


    //required

    private final String foodname;

    private final int realing;


    //option

    private int dbz;
    private int zf;
    private int tangfen;


    //全参数

    public Food(String foodname, int realing, int dbz, int zf, int tangfen) {
        this.foodname = foodname;
        this.realing = realing;
        this.dbz = dbz;
        this.zf = zf;
        this.tangfen = tangfen;
    }

    //2个参数
    public Food(String foodname,int realing) {
        this(foodname,realing,0,0,0);
    }

    //3个参数...5个参数


    public static class Builder {
        //required

        private final String foodname;

        private final int realing;


        //option

        private int dbz;
        private int zf;
        private int tangfen;

        public Builder(String foodname, int realing) {
            this.foodname = foodname;
            this.realing = realing;
        }

        public Builder dbz(int var1) {
            this.dbz = var1;
            return this;
        }

        public Builder zf(int zf) {
            this.zf = zf;
            return this;
        }

        public Builder tangfen(int tangfen) {
            this.tangfen = tangfen;
            return this;
        }

        public Food build() {
            return new Food(this);
        }


    }

    private Food(Builder builder) {
        foodname = builder.foodname;
        realing = builder.realing;
        dbz = builder.dbz;
    }

    public static void main(String[] args) {
        //上面这种方式，不太好传
//        Food food = new Food();

        Food food = new Food.Builder("food1",1000).dbz(100).build();
        System.out.println(food);
    }
}
