package com.ginwithouta.algorithm.class08;

/**
 * @Package : com.ginwithouta.algorithm.class08
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周四
 * @Desc :
 */
public class Code09_MaxHappy {
    public static class Employee {
        public int happy;
        public Employee[] employees;
        public Employee(int happy, Employee[] employees) {
            this.happy = happy;
            this.employees = employees;
        }
    }
    public static class Info {
        public int yes;
        public int no;
        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }
    public static int maxHappy(Employee boss) {
        Info info = process(boss);
        return Math.max(info.yes, info.no);
    }
    public static Info process(Employee employee) {
        if (employee == null) {
            return new Info(0, 0);
        }
        int yes = employee.happy, no = 0;
        for (Employee e : employee.employees) {
            Info nextInfo = process(e);
            yes += nextInfo.no;
            no += Math.max(nextInfo.yes, nextInfo.no);
        }
        return new Info(yes, no);
    }
}
