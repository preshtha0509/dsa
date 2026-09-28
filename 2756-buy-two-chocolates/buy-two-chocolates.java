class Solution {
    public int buyChoco(int[] prices, int money) {
        Arrays.sort(prices);
        for(int i=0;i<prices.length-1;i++){
            for(int j=i+1;j<prices.length;j++){
                if(prices[i]+prices[j] == money){
                    return 0;
                }
                else if(prices[i]+prices[j] < money){
                    return (money-(prices[i]+prices[j]));
                }
            }
        }
        return money;
    }
}