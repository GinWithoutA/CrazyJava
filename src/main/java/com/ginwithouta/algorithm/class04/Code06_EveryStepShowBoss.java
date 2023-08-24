package com.ginwithouta.algorithm.class04;
import java.util.*;

/**
 * @Package : com.ginwithouta.algorithm.class04
 * @Author : NONO Wang
 * @Date : 2023 - 8月 - 周三
 * @Desc : 假设有一个得奖的场景，存在两个地区，一个候选区，一个得奖区。得奖区的大小是指定的，只有K。会给你两个数组，每一个位置代表一个时间戳，
 *          两两一一对应，第一个数组arr表示进行操作的customer，第二个位置表示它具体的操作（买货或者退货）。有几个判断是否得奖的标准，首先没有
 *          购买商品的用户不能参与竞奖，只要获奖区没有满，用户的购买数量大于0的就都可以进入获奖区。如果获奖区满了，判断候选区是否有用户可以进
 *          入获奖区，如果购买的数量大于获奖区购买数量最少的，进入。如果获奖区存在多个购买数量相同的用户，那么进入时间最早的优先被替换，如果
 *          候选区存在多个购买数量相同且想要进入获奖区的用户，则购买时间早的优先进入。如果用户没有购买，但是发生了退货操作，则跳过不做处理。
 *          请你给出每个时间步骤结束后，获奖的所有人员的名单。
 */
public class Code06_EveryStepShowBoss {
    public static class Customer {
        public int id;
        public int buy;
        public int enterTime;
        /**
         *
         * @param id            当前消费者ID
         * @param buy           已经购买的数量
         * @param enterTime     进入获奖区或者候选区的时间
         */
        public Customer(int id, int buy, int enterTime) {
            this.id = id;
            this.buy = buy;
            this.enterTime = enterTime;
        }
    }
    public static class WhoIsYourDaddy {
        private HashMap<Integer, Customer> customers;
        /**
         * 大根堆，谁牛逼谁在上面
         */
        private Code05_HeapGreater<Customer> candidates;
        /**
         * 小根堆，谁垃圾谁在上面
         */
        private Code05_HeapGreater<Customer> daddy;
        private final int daddyLimit;
        public WhoIsYourDaddy(int limit) {
            customers = new HashMap<>();
            candidates = new Code05_HeapGreater<>(new CandidateComparator());
            daddy = new Code05_HeapGreater<>(new DaddyComparator());
            daddyLimit = limit;
        }
        /**
         * 主要的处理流程
         * @param curTime       当前时间
         * @param buyOrRefund
         * @param id
         */
        public void operator(int curTime, boolean buyOrRefund, int id) {
            if (!buyOrRefund && !customers.containsKey(id)) {
                return ;
            }
            if (!customers.containsKey(id)) {
                customers.put(id, new Customer(id, 0, 0));
            }
            Customer customer = customers.get(id);
            if (buyOrRefund) {
                customer.buy++;
            } else {
                customer.buy--;
            }
            if (customer.buy == 0) {
                customers.remove(customer.id);
            }
            // 开始优化，这一块if else if else 用来判断是否需要删除
            if (!candidates.contains(customer) && !daddy.contains(customer)) {
                customer.enterTime = curTime;
                if (daddy.size() < daddyLimit) {
                    daddy.push(customer);
                } else {
                    candidates.push(customer);
                }
            } else if (candidates.contains(customer)) {
                // 在候选区
                if (customer.buy == 0) {
                    candidates.remove(customer);
                } else {
                    candidates.push(customer);
                }
            } else {
                // 在得奖区
                if (customer.buy == 0) {
                    daddy.remove(customer);
                } else {
                    // 当前customer的购买数不是0，那么就更新它的位置，重新找到当前daddy中最垃圾的那个
                    daddy.resign(customer);
                }
            }
            // 这一步骤用来判断当前的候选区和得奖区是否有customer需要替换
            daddyMove(curTime);
        }
        private void daddyMove(int curTime) {
            if (candidates.isEmpty()) {
                return ;
            }
            if (daddy.size() < daddyLimit) {
                Customer customer = candidates.pop();
                customer.enterTime = curTime;
                daddy.push(customer);
            } else {
                if (daddy.peek().buy < candidates.peek().buy) {
                    Customer oldDaddy = daddy.pop(), newDaddy = candidates.pop();
                    oldDaddy.enterTime = curTime;
                    newDaddy.enterTime = curTime;
                    candidates.push(oldDaddy);
                    daddy.push(newDaddy);
                }
            }
        }
        public List<Integer> getDaddies() {
            List<Integer> result = new ArrayList<>();
            for (Customer customer: daddy.getAllElements()) {
                result.add(customer.id);
            }
            return result;
        }
    }
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        WhoIsYourDaddy whoIsYourDaddy = new WhoIsYourDaddy(k);
        for (int i = 0; i < arr.length; i++) {
            whoIsYourDaddy.operator(i, op[i], arr[i]);
            ans.add(whoIsYourDaddy.getDaddies());
        }
        return ans;
    }
    /**
     * 最基础的解法，没有任何优化
     * @param arr   消费者数组
     * @param op    消费者对应的操作数组
     * @param k     获奖区中的个数
     * @return      获奖区中每个获奖人的List集合
     */
    public static List<List<Integer>> compare(int[] arr, boolean[] op, int k) {
        // 存储消费者和ID的匹配关系，如果用户购买数为0则删除
        HashMap<Integer, Customer> map = new HashMap<>(arr.length);
        ArrayList<Customer> candidates = new ArrayList<>();
        ArrayList<Customer> daddy = new ArrayList<>();
        // 返回的是每个时间点获奖的customer
        List<List<Integer>> ans = new ArrayList<>();
        // 循环每个时间点
        for (int i = 0; i < arr.length; i++) {
            int id = arr[i];
            boolean buyOrRefund = op[i];
            // 如果当前发生了退货操作，并且此时map中没有对应的用户
            if (!buyOrRefund && !map.containsKey(id)) {
                ans.add(getCurAns(daddy));
                continue;
            }
            // 到这里的话，就是没有发生用户不存在然后又进行了退货操作
            // 此时有三种情况，用户之前没买过，此时买货
            // 用户买过的个数 > 0，此时买货
            // 用户买过的个数 > 0，此时卖货
            if (!map.containsKey(id)) {
                // 先放进去，初始化一个Customer对象
                map.put(id, new Customer(id, 0, 0));
            }
            Customer customer = map.get(id);
            // 此时再判断是买货还是卖货
            if (buyOrRefund) {
                customer.buy++;
            } else {
                customer.buy--;
            }
            // 此时就可以判断用户的买货数量了，如果购买的数量是0，先删除map里面的数据，然后再说
            if (customer.buy == 0) {
                map.remove(id);
            }
            // 删除这两个区域中所有用户购买数为0的用户
            cleanZeroBuy(candidates);
            cleanZeroBuy(daddy);
            // 如果这个用户是个新用户，判断它要进哪一个区域
            if (!candidates.contains(customer) && !daddy.contains(customer)) {
                if (daddy.size() < k) {
                    daddy.add(customer);
                } else {
                    candidates.add(customer);
                }
            }
            // 先进行一次排序，让候选区和获奖区的顺序以想要的方式排列好
            daddy.sort(new DaddyComparator());
            candidates.sort(new CandidateComparator());
            // 前面的任务都完成了，现在考虑候选区和获奖区是否要进行customer变换位置的问题
            move(candidates, daddy, k, i);
            ans.add(getCurAns(daddy));
        }
        return ans;
    }
    public static void move(ArrayList<Customer> candidates, ArrayList<Customer> daddy, int k, int curTime) {
        // 如果候选区为空，不牵扯替换的问题
        if (candidates.isEmpty()) {
            return ;
        }
        if (daddy.size() < k) {
            Customer customer = candidates.get(0);
            customer.enterTime = curTime;
            daddy.add(customer);
            candidates.remove(customer);
        } else {
            // 替换逻辑来了，只有真的候选区买的数量大于得奖区的第一个元素，才把他替换
            if (candidates.get(0).buy > daddy.get(0).buy) {
                Customer oldDaddy = daddy.get(0);
                daddy.remove(oldDaddy);
                Customer newDaddy = candidates.get(0);
                oldDaddy.enterTime = curTime;
                newDaddy.enterTime = curTime;
                candidates.remove(newDaddy);
                candidates.add(oldDaddy);
                daddy.add(newDaddy);
            }

        }
    }
    /**
     * 候选区的排序策略，买得多的排前面，如果买的数量相同，买的早的放前面，主要是为了将第一个放到获奖区
     */
    public static class CandidateComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
        }
    }
    /**
     * 获奖区的排序策略，买的少的放前面，如果相同，谁进的早谁放前面，主要是为了将第一个丢掉或者放到候选区
     */
    public static class DaddyComparator implements Comparator<Customer> {
        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buy != o2.buy ? (o1.buy - o2.buy) : (o1.enterTime - o2.enterTime);
        }
    }
    public static void cleanZeroBuy(ArrayList<Customer> area) {
        List<Customer> noZero = new ArrayList<Customer>();
        for (Customer customer : area) {
            if (customer.buy != 0) {
                noZero.add(customer);
            }
        }
        area.clear();
        area.addAll(noZero);
    }
    public static List<Integer> getCurAns(ArrayList<Customer> daddy) {
        List<Integer> ans = new ArrayList<>();
        for (Customer customer : daddy) {
            ans.add(customer.id);
        }
        return ans;
    }
}
