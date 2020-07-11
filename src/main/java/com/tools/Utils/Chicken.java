package com.tools.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Chicken {
    private String money;
    private String inChicken;
    private String outChicken;
    private List<Count> countList;


    public Chicken(String money, String inChicken) {
        this.money = money;
        this.inChicken = inChicken;
        this.countList = init();
        this.outChicken = "5000";
    }

    public static void main(String[] args) {
        Chicken chicken = new Chicken("100000", "100000");
        System.out.println("【本金】:" + chicken);
        chicken.account();
        System.out.println(chicken);

    }

    public void account() {
        int k = 0;
        for (; k < countList.size(); k++) {
            Count count = this.countList.get(k);
            if (count.getPoint().equals("up")) {
                inChicken = multiply(inChicken, add("1", count.getRate()));
                inChicken = subtract(inChicken, this.outChicken);
                money = add(money, multiply(this.outChicken, subtract("1", "0.015")));
                System.out.println("\n第" + k + "天：【余额】=" + money + ",【基金】=" + inChicken);
            } else {
                inChicken = multiply(inChicken, subtract("1", count.getRate()));
                money = subtract(money, this.outChicken);//追加3000
                inChicken = add(inChicken, this.outChicken);
                System.out.println("\n第" + k + "天：【余额】=" + money + ",【基金】=" + inChicken);
            }
        }
    }

    public String add(String a, String b) {
        BigDecimal bignum1 = new BigDecimal(a);
        BigDecimal bignum2 = new BigDecimal(b);
        return bignum1.add(bignum2).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String multiply(String a, String b) {
        BigDecimal bignum1 = new BigDecimal(a);
        BigDecimal bignum2 = new BigDecimal(b);
        return bignum1.multiply(bignum2).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String divide(String a, String b) {
        BigDecimal bignum1 = new BigDecimal(a);
        BigDecimal bignum2 = new BigDecimal(b);
        return bignum1.divide(bignum2).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
    }

    public String subtract(String a, String b) {
        BigDecimal bignum1 = new BigDecimal(a);
        BigDecimal bignum2 = new BigDecimal(b);
        return bignum1.subtract(bignum2).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
    }


    public List<Count> init() {
        List<Count> counts = new ArrayList<>();
        Count count;
        for (int k = 0; k < 10; k++) {
            if (k % 2 == 0) {
                count = new Count(k, "0.02", "up");
            } else {
                count = new Count(k, "0.02", "down");
            }
            counts.add(count);
        }
        return counts;

    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<Count> getCountList() {
        return countList;
    }

    public void setCountList(List<Count> countList) {
        this.countList = countList;
    }

    public String getInChicken() {
        return inChicken;
    }

    public void setInChicken(String inChicken) {
        this.inChicken = inChicken;
    }

    public String getOutChicken() {
        return outChicken;
    }

    public void setOutChicken(String outChicken) {
        this.outChicken = outChicken;
    }
}
