import java.util.Arrays;

public class App {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        int result = helper(coins, amount, dp);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int helper(int[] coins, int amount, int[] dp) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return Integer.MAX_VALUE;
        }

        if (dp[amount] != -1) {
            return dp[amount];
        }

        int steps = Integer.MAX_VALUE;

        for (int coin : coins) {
            int ans = helper(coins, amount - coin, dp);

            if (ans != Integer.MAX_VALUE) {
                steps = Math.min(ans + 1, steps);
            }
        }

        dp[amount] = steps;
        return dp[amount];
    }
}
