from typing import List


def coinChange(self, coins: List[int], amount: int) -> int:
        dp = [-1] * (amount + 1)
        result = self.helper(coins, amount, dp)
        return result if result != float('inf') else -1

def helper(self, coins: List[int], amount: int, dp: List[int]) -> int:
        if amount == 0:
            return 0
        if amount < 0:
            return float('inf')

        if dp[amount] != -1:
            return dp[amount]

        steps = float('inf')

        for coin in coins:
            ans = self.helper(coins, amount - coin, dp)

            if ans != float('inf'):
                steps = min(ans + 1, steps)

        dp[amount] = steps
        return dp[amount]