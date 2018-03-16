package algorithms.leetcode;

/**
 * Leetcode : 122. Best Time to Buy and Sell Stock II
 * 股票买卖
 * 只允许买一只股票 然后在某天卖出 交易次数不限 计算能达到的最大收益值
 * @author MelloChan
 * @date 2018/3/15
 */
public class BestTimetoBuyandSellStock {

    public static void main(String[] args) {
        int[] prices={1,1,2,4,2,3,8};
        System.out.println(maxProfit(prices));
    }

    /**
     * 利用贪心算法 局部最优到达全局最优
     * @param prices 元素表示股票在第i天的价格
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 0) {
            return 0;
        }

        int maxProfit = 0;
        int length = prices.length;
        int profit;
        for (int i = 1; i <length; i++) {
            // 第i-1买入 第i天卖出 如果收益为正 则可执行
            if((profit=prices[i]-prices[i-1])>0){
                maxProfit+=profit;
            }
        }
        return  maxProfit;
    }
}
