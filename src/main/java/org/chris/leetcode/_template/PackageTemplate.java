package org.chris.leetcode._template;

import java.util.ArrayList;
import java.util.List;

public class PackageTemplate {

    /**
     * 0-1 背包问题二维动态规划解法
     * 思路 1：0-1 背包问题的基本思路
     * <p>
     * <p>
     * 1. 阶段划分
     * 以物品序号和当前背包剩余容量为阶段。
     * <p>
     * 2. 定义状态
     * 令 dp[i][w] 表示前 i 件物品，放入容量不超过 w 的背包时可获得的最大价值。
     * 其中 i 表示考虑前 i 件物品，w 表示当前背包容量。
     * <p>
     * 3. 状态转移方程
     * > 对于「将前 i 件物品放入容量为 w 的背包，能获得的最大价值」这个子问题，我们只需关注第 i−1 件物品的选择情况（即放或不放），即可将问题递归地转化为只与前 i−1 件物品相关的子问题：
     * - 不放第 i−1 件物品：此时最大价值为 dp[i−1][w]，即前 i−1 件物品放入容量为 w 的背包的最大价值。
     * - 放第 i−1 件物品：前提是背包剩余容量足够（w≥weight[i−1]），此时最大价值为 dp[i−1][w−weight[i−1]]+value[i−1]，即前 i−1 件物品放入剩余容量为 w−weight[i−1] 的背包的最大价值，再加上第 i−1 件物品的价值。
     * >因此，状态转移分两种情况：
     * - 当 w<weight[i−1] 时，第 i−1 件物品无法放入背包，dp[i][w]=dp[i−1][w]。
     * - 当 w≥weight[i−1] 时，第 i−1 件物品可选可不选，dp[i][w]=max{dp[i−1][w], dp[i−1][w−weight[i−1]]+value[i−1]}。
     * >综上，状态转移方程为：
     * <p>
     * - dp[i][w]=
     * ----1. dp[i−1][w], w<weight[i−1]时
     * ----2. max{dp[i−1][w], dp[i−1][w−weight[i−1]]+value[i−1]，w≥weight[i−1]时
     * ---- **注意：对 i,i-1的使用有些混乱，将前 i 件物品在DP里用dp[i]表示，但是在weight[]、value[]里，第i个元素因为下标从0开始都用weight[i-1]、value[i-1]表示**
     * 4. 初始条件
     * 当背包容量为 0 时，无论有多少物品，最大价值为 0，即 dp[i][0]=0，0≤i≤size。
     * 当没有物品时，无论背包容量多少，最大价值为 0，即 dp[0][w]=0，0≤w≤W。
     * 5. 最终结果
     * 最终答案为 dp[size][W]，即用前 size 件物品，背包容量为 W 时能获得的最大价值。
     * <p>
     * 时间复杂度：O(n×W)，其中 n 为物品数量，W 为背包的载重上限。
     * 空间复杂度：O(n×W)。
     * <p>
     * <p>
     * 基本思路理解：
     * 1、注意：对 i,i-1的使用有些混乱，将前 i 件物品在DP里用dp[i]表示，但是在weight[]、value[]里，第i个元素因为下标从0开始都用weight[i-1]、value[i-1]表示
     * 2、转移方程里当w > weight[i-1]时dp[i][w]=max{dp[i−1][w], dp[i−1][w−weight[i−1]]+value[i−1]},这里w表示背包总容量，当总容量能放下当前物品且放入时，剩余容量为w-weight[i-1],方程里的dp[i−1][w−weight[i−1]]表示在上个物品时，剩余的容量可以放多少价值❗️
     *
     * @param weight int[]，每件物品的重量
     * @param value  int[]，每件物品的价值
     * @param W      int，背包最大承重
     * @return int，最大可获得价值
     */
    public int zeroOnePackMethod1(int[] weight, int[] value, int W) {
        int size = weight.length;
        // dp[i][w] 表示前 i 件物品，容量不超过 W 时的最大价值
        int[][] dp = new int[size + 1][W + 1];

        // 遍历每一件物品
        for (int i = 1; i <= size; i++) {
            // 遍历每一种可能的背包容量
            for (int w = 0; w <= W; w++) {
                if (w < weight[i - 1]) {
                    // 当前物品放不下，继承上一个状态
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 当前物品可选，取放与不放的最大值
                    dp[i][w] = Math.max(
                            dp[i - 1][w], // 不放当前物品
                            dp[i - 1][w - weight[i - 1]] + value[i - 1] // 放当前物品
                    );
                }
            }
        }
        // 返回前 size 件物品、容量为 W 时的最大价值
        return dp[size][W];
    }


    /**
     * 0-1 背包问题的思路 2：动态规划 + 滚动数组优化
     * <p>
     * 通过前面的分析可以发现，在依次处理第 1∼n 件物品时，「前 i 件物品的状态」只依赖于「前 i−1 件物品的状态」，与更早之前的状态无关。
     * 换句话说，状态转移时只涉及当前行（第 i 行）的 dp[i][w] 和上一行（第 i−1 行）的 dp[i−1][w]、dp[i−1][w−weight[i−1]]。
     * 因此，我们无需保存所有阶段的状态，只需保留当前阶段和上一阶段的状态即可。可以用两个一维数组分别存储相邻两阶段的所有状态：dp[0][w] 存储 dp[i−1][w]，dp[1][w] 存储 dp[i][w]。
     * 进一步优化时，其实只需一个一维数组 dp[w]，利用「滚动数组」思想，将动态规划的第一维去掉，从而实现空间优化。
     * <p>
     * 思路 2：动态规划 + 滚动数组优化
     * 1. 阶段划分
     * 以当前背包的剩余容量 w 作为阶段。
     * <p>
     * 2. 定义状态
     * 令 dp[w] 表示：在背包容量不超过 w 的情况下，能够获得的最大总价值。
     * <p>
     * 3. 状态转移方程
     * 状态转移如下：
     * ----dp[w]=
     * ----- dp[w],        w<weight[i−1]
     * ----- max{dp[w], dp[w−weight[i−1]]+value[i−1]},     w≥weight[i−1]
     * 在处理第 i 件物品时，dp[w] 只依赖于上一阶段（即第 i−1 件物品处理完后）的 dp[w] 和 dp[w−weight[i−1]]。因此，为了避免状态被提前覆盖，必须对 w 采用从大到小（即从 W 到 0）的逆序遍历。这样可以确保每次转移用到的 dp[w−weight[i−1]] 仍然是上一阶段的值。（因为w每次是用减的）
     * 如果采用从小到大（正序）遍历，则 dp[w−weight[i−1]] 可能已经被本轮更新，导致状态转移错误。
     * 实际上，当 w<weight[i−1] 时，当前物品无法放入背包，dp[w] 保持不变，无需更新。因此逆序遍历时只需从 W 遍历到 weight[i−1]。
     * <p>
     * 4. 初始条件
     * 对于所有 0≤w≤W，dp[w]=0，表示背包容量为 w 时，尚未放入任何物品，最大价值为 0。
     * <p>
     * 5. 最终结果
     * 最终答案为 dp[W]，即背包容量为 W 时能够获得的最大总价值。
     * <p>
     * 0-1 背包问题的滚动数组优化解法思路：
     * 1、在依次处理第 1∼n 件物品时，「前 i 件物品的状态」只依赖于「前 i−1 件物品的状态」，与更早之前的状态无关。 换句话说，状态转移时只涉及当前行（第 i 行）的 dp[i][w] 和上一行（第 i−1 行）的 dp[i−1][w]、dp[i−1][w−weight[i−1]]。
     * 2、其实只需一个一维数组 dp[w]，利用「滚动数组」思想，将动态规划的第一维去掉，从而实现空间优化。
     *
     * @param weight int[]，每件物品的重量
     * @param value  int[]，每件物品的价值
     * @param W      int，背包最大承重
     * @return int，背包可获得的最大价值
     */
    public int zeroOnePackMethod2(int[] weight, int[] value, int W) {
        int size = weight.length;
        // dp[w] 表示容量为 w 时背包可获得的最大价值
        int[] dp = new int[W + 1];

        // 遍历每一件物品
        for (int i = 0; i < size; i++) {
            //TODO 必须逆序遍历容量，防止状态被提前覆盖
            for (int w = W; w >= weight[i]; w--) {
                // 状态转移：不选第 i 件物品 or 选第 i 件物品
                // dp[w] = max(不选, 选)
                dp[w] = Math.max(dp[w], dp[w - weight[i]] + value[i]);
                // 解释：
                // dp[w]：不选第 i 件物品，价值不变
                // dp[w - weight[i]] + value[i]：选第 i 件物品，容量减少，相应加上价值
            }
        }

        return dp[W];
    }

    // 思路 1：动态规划 + 二维基本思路

    /**
     * 完全背包问题二维动态规划解法
     * <p>
     * 思路 1：动态规划 + 二维数组基础解法
     * 1. 阶段划分
     * 以物品种类序号和当前背包剩余容量作为阶段。
     * <p>
     * 2. 定义状态
     * 设 dp[i][w] 表示前 i 种物品，放入容量不超过 w 的背包时可获得的最大价值。
     * 其中 i 表示考虑前 i 种物品，w 表示当前背包容量。
     * <p>
     * 3. 状态转移方程
     * 由于每种物品可以选取任意多次，dp[i][w] 可以通过枚举第 i−1 种物品的选取数量 k 得到：
     * <p>
     * ----选 0 件第 i−1 种物品：dp[i−1][w]
     * ----选 1 件第 i−1 种物品：dp[i−1][w−weight[i−1]]+value[i−1]
     * ----选 2 件第 i−1 种物品：dp[i−1][w−2×weight[i−1]]+2×value[i−1]
     * ----...
     * ----选 k 件第 i−1 种物品：dp[i−1][w−k×weight[i−1]]+k×value[i−1]
     * ----其中 0≤k≤w/weight[i−1]。
     * ----因此，状态转移方程为：dp[i][w]= max{dp[i−1][w−k×weight[i−1]]+k×value[i−1]},     0≤k≤w/weight[i−1]
     * <p>
     * 4. 初始条件
     * 对所有 0≤i≤size，dp[i][0]=0，即背包容量为 0 时最大价值为 0。
     * 对所有 0≤w≤W，dp[0][w]=0，即没有物品时最大价值为 0。
     * 5. 最终结果
     * 最终答案为 dp[size][W]，即用前 size 种物品、背包容量为 W 时能获得的最大价值。
     *
     * @param weight int[]，每件物品的重量
     * @param value  int[]，每件物品的价值
     * @param W      int，背包最大承重
     * @return int，最大可获得价值
     */
    public int completePackMethod1(int[] weight, int[] value, int W) {
        int size = weight.length;
        // dp[i][w] 表示前 i 件物品，容量不超过 W 时的最大价值
        int[][] dp = new int[size + 1][W + 1];

        // 枚举前 i 种物品
        for (int i = 1; i <= size; i++) {
            // 枚举背包装载重量
            for (int w = 0; w <= W; w++) {
                // 枚举第 i - 1 种物品能取个数
                // 注意：这里需要确保 w - k * weight[i - 1] >= 0
                for (int k = 0; k * weight[i - 1] <= w; k++) {
                    // dp[i][w] 取所有 dp[i - 1][w - k * weight[i - 1]] + k * value[i - 1] 中最大值
                    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - k * weight[i - 1]] + k * value[i - 1]);
                }
            }
        }

        return dp[size][W];
    }


    /**
     * 思路 2：动态规划 + 时间复杂度优化（去除 k 循环）
     * 完全背包问题 - 时间复杂度优化解法 (O(NW))
     * <p>
     * 思路 2：动态规划 + 状态转移方程优化
     * 1. 阶段划分
     * 按照物品种类的序号、当前背包的载重上限进行阶段划分。
     * 2. 定义状态
     * 定义状态 dp[i][w] 表示为：前 i 种物品放入一个最多能装重量为 w 的背包中，可以获得的最大价值。
     * 状态 dp[i][w] 是一个二维数组，其中第一维代表「当前正在考虑的物品种类」，第二维表示「当前背包的载重上限」，二维数组值表示「可以获得的最大价值」。
     * 3. 状态转移方程
     * dp[i][w]=
     * ----1、dp[i−1][w]，     w<weight[i−1]
     * ----2、max{dp[i−1][w],dp[i][w−weight[i−1]]+value[i−1]}，      w≥weight[i−1]
     * 4. 初始条件
     * 如果背包载重上限为 0，则无论选取什么物品，可以获得的最大价值一定是 0，即 dp[i][0]=0,0≤i≤size。
     * 无论背包载重上限是多少，前 0 种物品所能获得的最大价值一定为 0，即 dp[0][w]=0,0≤w≤W。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[i][w] 表示为：前 i 种物品放入一个最多能装重量为 w 的背包中，可以获得的最大价值。则最终结果为 dp[size][W]，其中 size 为物品的种类数，W 为背包的载重上限。
     * <p>
     * 利用推导公式：dp[i][w] = max(dp[i-1][w], dp[i][w-weight] + value)
     *
     * @param weight int[]，每件物品的重量
     * @param value  int[]，每件物品的价值
     * @param W      int，背包最大承重
     * @return int，最大可获得价值
     */
    public int completePackMethod2(int[] weight, int[] value, int W) {
        int size = weight.length;
        // dp[i][w] 表示前 i 件物品，容量不超过 W 时的最大价值
        int[][] dp = new int[size + 1][W + 1];

        // 遍历每一件物品
        for (int i = 1; i <= size; i++) {
            // 遍历每一种可能的背包容量
            for (int w = 0; w <= W; w++) {
                if (w < weight[i - 1]) {
                    // 装不下，只能继承前 i-1 个物品的状态
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // 可以装下，取最大值
                    // 注意第二个参数是 dp[i][...] 而不是 dp[i-1][...]
                    // 这代表我们可以基于"当前物品已经选过"的状态再次选择当前物品
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            dp[i][w - weight[i - 1]] + value[i - 1]
                    );
                }
            }
        }
        return dp[size][W];
    }


    /**
     * 思路 3：动态规划 + 滚动数组优化
     * 完全背包问题 - 空间复杂度优化解法
     * <p>
     * 1. 阶段划分
     * 按照当前背包的载重上限进行阶段划分。
     * 2. 定义状态
     * 定义状态 dp[w] 表示为：将物品装入最多能装重量为 w 的背包中，可以获得的最大价值。
     * 3. 状态转移方程
     * ---- dp[w]=
     * ---- 1、dp[w]，      w<weight[i−1]
     * ---- 2、max{dp[w],dp[w−weight[i−1]]+value[i−1]}，        w≥weight[i−1]
     * 注意：这里的 dp[w−weight[i−1]] 是第 i 轮计算之后的「第 i 阶段的状态值」。因为在计算dp[w] 时，我们需要用到第 i 轮计算之后的 dp[w−weight[i−1]]，所以我们需要按照「从 0∼W 正序递推的方式」递推 dp[w]，这样才能得到正确的结果。
     * 因为 w<weight[i−1] 时，dp[w] 只能取上一阶段的 dp[w]，其值相当于没有变化，这部分可以不做处理。所以我们在正序递推 dp[w] 时，只需从 weight[i−1] 开始遍历即可。
     * 4. 初始条件
     * 无论背包载重上限为多少，只要不选择物品，可以获得的最大价值一定是 0，即 dp[w]=0,0≤w≤W。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[w] 表示为：将物品装入最多能装重量为 w 的背包中，可以获得的最大价值。则最终结果为 dp[W]，其中 W 为背包的载重上限。
     *
     * @param weight int[]，每件物品的重量
     * @param value  int[]，每件物品的价值
     * @param W      int，背包最大承重
     * @return int，背包可获得的最大价值
     */
    public int completePackMethod3(int[] weight, int[] value, int W) {
        int size = weight.length;
        // dp[w] 表示容量为 w 时背包可获得的最大价值
        int[] dp = new int[W + 1];

        // 枚举前 i 种物品
        for (int i = 1; i <= size; i++) {
            // TODO 正序枚举背包装载重量
            // 注意：这里是从 weight[i-1] 开始，因为小于 weight[i-1] 的容量装不下该物品，dp 值保持不变
            for (int w = weight[i - 1]; w <= W; w++) {
                // dp[w] 取「前 i - 1 种物品装入载重为 w 的背包中的最大价值」
                // 与「前 i 种物品装入载重为 w - weight[i - 1] 的背包中，再装入 1 件第 i - 1 种物品所得的最大价值」两者中的最大值
                // 注意：因为是正序遍历，dp[w - weight[i - 1]] 已经是考虑过当前物品的情况了（即已经更新过），
                // 所以这实际上实现了 completePackMethod2 中的逻辑：dp[i][w] = max(dp[i-1][w], dp[i][w-weight] + value)
                dp[w] = Math.max(dp[w], dp[w - weight[i - 1]] + value[i - 1]);
            }
        }

        return dp[W];
    }


    /**
     * 多重背包问题 - 二维动态规划解法
     * 多重背包问题的基本思路
     * 我们可以参考「0-1 背包问题」的状态定义和基本思路，对于容量为 w 的背包，最多可以装 `min{count[i−1], w/weight[i−1]}` 件第 i−1 件物品。那么我们可以多加一层循环，枚举第 i−1 件物品可以选择的件数`（0∼min{count[i−1], w/weight[i−1]}）`，从而将「完全背包问题」转换为「0-1 背包问题」。
     * <p>
     * 思路 1：动态规划 + 二维基本思路
     * 1. 阶段划分
     * 按照物品种类的序号、当前背包的载重上限进行阶段划分。
     * 2. 定义状态
     * 定义状态 `dp[i][w]` 表示为：前 i 种物品放入一个最多能装重量为 w 的背包中，可以获得的最大价值。
     * 状态 `dp[i][w]` 是一个二维数组，其中第一维代表「当前正在考虑的物品种类」，第二维表示「当前背包的载重上限」，二维数组值表示「可以获得的最大价值」。
     * 3. 状态转移方程
     * ---- `dp[i][w] = max{dp[i−1][w − k * weight[i−1]] + k * value[i−1]},        0≤k≤min{count[i−1], w/weight[i−1]}。`
     * 4. 初始条件
     * 如果背包载重上限为 0，则无论选取什么物品，可以获得的最大价值一定是 0，即 `dp[i][0]=0,0≤i≤size`。
     * 无论背包载重上限是多少，前 0 种物品所能获得的最大价值一定为 0，即 `dp[0][w]=0,0≤w≤W`。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[i][w] 表示为：前 i 种物品放入一个最多能装重量为 w 的背包中，可以获得的最大价值。则最终结果为 dp[size][W]，其中 size 为物品的种类数，W 为背包的载重上限。
     * <p>
     * 时间复杂度：O(n×W×C)，其中 n 为物品种类数量，W 为背包的载重上限，C 是物品的数量数组长度。因为 n×C=∑count[i]，所以时间复杂度也可以写成 O(W×∑count[i])。
     * 空间复杂度：O(n×W)。
     *
     * @param weight int[]，每件物品的重量
     * @param value  int[]，每件物品的价值
     * @param count  int[]，每件物品的数量
     * @param W      int，背包最大承重
     * @return int，最大可获得价值
     */
    public int multiplePackMethod1(int[] weight, int[] value, int[] count, int W) {
        int size = weight.length;
        // dp[i][w] 表示前 i 件物品，容量不超过 W 时的最大价值
        int[][] dp = new int[size + 1][W + 1];

        // 枚举前 i 种物品
        for (int i = 1; i <= size; i++) {
            // 枚举背包装载重量
            for (int w = 0; w <= W; w++) {
                // 枚举第 i - 1 种物品能取个数
                // 个数限制：不能超过物品总数 count[i-1]，且总重量不能超过背包当前容量 w
                for (int k = 0; k <= count[i - 1] && k * weight[i - 1] <= w; k++) {
                    // dp[i][w] 取所有 dp[i - 1][w - k * weight[i - 1]] + k * value[i - 1] 中最大值
                    dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - k * weight[i - 1]] + k * value[i - 1]);
                }
            }
        }
        return dp[size][W];
    }

    /**
     * 多重背包问题 - 思路 2：动态规划 + 滚动数组优化
     * 多重背包问题的滚动数组优化
     * 在「完全背包问题」中，我们通过优化「状态转移方程」的方式，成功去除了对物品件数 k 的依赖，从而将时间复杂度下降了一个维度。
     * <p>
     * 而在「多重背包问题」中，我们在递推 dp[i][w] 时，是无法从 dp[i][w−weight[i−1]] 状态得知目前究竟已经使用了多个件第 i−1 种物品，也就无法判断第 i−1 种物品是否还有剩余数量可选。**这就导致了我们无法通过优化「状态转移方程」的方式将「多重背包问题」的时间复杂度降低。但是我们可以参考「完全背包问题」+「滚动数组优化」的方式，将算法的空间复杂度下降一个维度。**
     * <p>
     * 思路 2：动态规划 + 滚动数组优化
     * 1. 阶段划分
     * 按照当前背包的载重上限进行阶段划分。
     * 2. 定义状态
     * 定义状态 dp[w] 表示为：将物品装入最多能装重量为 w 的背包中，可以获得的最大价值。
     * 3. 状态转移方程
     * ---- `dp[w] = max{dp[w − k * weight[i−1]] + k * value[i−1]},      0≤k≤min{count[i−1], w/weight[i−1] }`
     * 4. 初始条件
     * 无论背包载重上限为多少，只要不选择物品，可以获得的最大价值一定是 0，即 dp[w]=0,0≤w≤W。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[w] 表示为：将物品装入最多能装重量为 w 的背包中，可以获得的最大价值。则最终结果为 dp[W]，其中 W 为背包的载重上限。
     * <p>
     * 时间复杂度：O(n×W×C)，其中 n 为物品种类数量，W 为背包的载重上限，C 是物品的数量数组长度。因为 n×C=∑count[i]，所以时间复杂度也可以写成 O(W×∑count[i])。
     * 空间复杂度：O(W)。
     *
     * @param weight int[]，每件物品的重量
     * @param value  int[]，每件物品的价值
     * @param count  int[]，每件物品的数量
     * @param W      int，背包最大承重
     * @return int，最大可获得价值
     */
    public int multiplePackMethod2(int[] weight, int[] value, int[] count, int W) {
        int size = weight.length;
        // dp[w] 表示容量为 w 时背包可获得的最大价值
        int[] dp = new int[W + 1];

        // 枚举前 i 种物品
        for (int i = 1; i <= size; i++) {
            // TODO 逆序枚举背包装载重量（避免状态值错误）
            for (int w = W; w >= weight[i - 1]; w--) {
                // 枚举第 i - 1 种物品能取个数
                // 注意：由于我们是逆序遍历 w，dp[w] 的初始值其实就是 dp[i-1][w]
                // 每次比较 dp[w - k*weight] + k*value 时，由于 w - k*weight < w，
                // 这些位置在当前 i 循环中尚未被更新，所以它们的值仍然是 dp[i-1][...]
                // 这保证了我们使用的是上一轮的状态，符合 0-1 背包/多重背包的逻辑
                for (int k = 1; k <= count[i - 1] && k * weight[i - 1] <= w; k++) {
                    dp[w] = Math.max(dp[w], dp[w - k * weight[i - 1]] + k * value[i - 1]);
                }
            }
        }

        return dp[W];
    }


    // 思路 3：动态规划 + 二进制优化

    /**
     * 多重背包问题 - 二进制优化解法
     * 将多重背包分解为 0-1 背包
     * 1. 阶段划分
     * 按照当前背包的载重上限进行阶段划分。
     * 2. 定义状态
     * 定义状态 dp[w] 表示为：将物品装入最多能装重量为 w 的背包中，可以获得的最大价值。
     * 3. 状态转移方程
     * dp[w]=max{dp[w−weightnew[i−1]]+valuenew[i−1]}
     * 4. 初始条件
     * 无论背包载重上限为多少，只要不选择物品，可以获得的最大价值一定是 0，即 dp[w]=0,0≤w≤W。
     * 5. 最终结果
     * 根据我们之前定义的状态，dp[w] 表示为：将物品装入最多能装重量为 w 的背包中，可以获得的最大价值。则最终结果为 dp[W]，其中 W 为背包的载重上限。
     * <p>
     * 时间复杂度：O(W×∑log2count[i])，其中 W 为背包的载重上限，count[i] 是第 i 种物品的数量。
     * 空间复杂度：O(W)。
     *
     * @param weight int[]，每件物品的重量
     * @param value  int[]，每件物品的价值
     * @param count  int[]，每件物品的数量
     * @param W      int，背包最大承重
     * @return int，最大可获得价值
     */
    public int multiplePackMethod3(int[] weight, int[] value, int[] count, int W) {
        List<Integer> weightNew = new ArrayList<>();
        List<Integer> valueNew = new ArrayList<>();
        // 二进制分解
        for (int i = 0; i < weight.length; i++) {
            int cnt = count[i];
            int k = 1;
            // 将 cnt 分解为 1, 2, 4, ... 2^n, remainder
            // 例如 cnt = 13, 分解为 1, 2, 4, 6 (1+2+4=7, 13-7=6)
            while (k <= cnt) {
                cnt -= k;
                weightNew.add(weight[i] * k);
                valueNew.add(value[i] * k);
                k *= 2;
            }
            if (cnt > 0) {
                weightNew.add(weight[i] * cnt);
                valueNew.add(value[i] * cnt);
            }
        }
        // 转化为 0-1 背包问题
        // dp[w] 表示容量为 w 时背包可获得的最大价值
        int[] dp = new int[W + 1];
        int size = weightNew.size();
        // 枚举前 i 种物品（这里的物品是分解后的新物品）
        for (int i = 0; i < size; i++) {
            int wVal = weightNew.get(i);
            int vVal = valueNew.get(i);
            // 逆序枚举背包装载重量（0-1 背包滚动数组优化）
            for (int w = W; w >= wVal; w--) {
                dp[w] = Math.max(dp[w], dp[w - wVal] + vVal);
            }
        }
        return dp[W];
    }


    /**
     * 混合背包问题
     * 物品分为三类：
     * 1. 多重背包 (count > 0)：有 count 件
     * 2. 0-1 背包 (count == -1)：只有 1 件
     * 3. 完全背包 (count == 0)：有无限件
     *
     * @param weight int[]，每件物品的重量
     * @param value  int[]，每件物品的价值
     * @param count  int[]，每件物品的数量（>0:多重, -1:0-1, 0:完全）
     * @param W      int，背包最大承重
     * @return int，最大可获得价值
     */
    public int mixedPackMethod1(int[] weight, int[] value, int[] count, int W) {
        List<Integer> weightNew = new ArrayList<>();
        List<Integer> valueNew = new ArrayList<>();
        List<Integer> countNew = new ArrayList<>(); // 1: 0-1背包, 0: 完全背包
        // 预处理物品
        for (int i = 0; i < weight.length; i++) {
            int cnt = count[i];
            if (cnt > 0) {
                // 多重背包问题，二进制分解转为 0-1 背包
                int k = 1;
                while (k <= cnt) {
                    cnt -= k;
                    weightNew.add(weight[i] * k);
                    valueNew.add(value[i] * k);
                    countNew.add(1); // 标记为 0-1 背包
                    k *= 2;
                }
                if (cnt > 0) {
                    weightNew.add(weight[i] * cnt);
                    valueNew.add(value[i] * cnt);
                    countNew.add(1); // 标记为 0-1 背包
                }
            } else if (cnt == -1) {
                // 0-1 背包问题，直接添加
                weightNew.add(weight[i]);
                valueNew.add(value[i]);
                countNew.add(1); // 标记为 0-1 背包
            } else {
                // 完全背包问题 (cnt == 0)，直接添加
                weightNew.add(weight[i]);
                valueNew.add(value[i]);
                countNew.add(0); // 标记为完全背包
            }
        }
        int[] dp = new int[W + 1];
        int size = weightNew.size();
        // 枚举前 i 种物品
        for (int i = 0; i < size; i++) {
            int wVal = weightNew.get(i);
            int vVal = valueNew.get(i);
            int type = countNew.get(i);
            if (type == 1) {
                // 0-1 背包问题：逆序枚举背包装载重量
                for (int w = W; w >= wVal; w--) {
                    dp[w] = Math.max(dp[w], dp[w - wVal] + vVal);
                }
            } else {
                // 完全背包问题：正序枚举背包装载重量
                for (int w = wVal; w <= W; w++) {
                    dp[w] = Math.max(dp[w], dp[w - wVal] + vVal);
                }
            }
        }
        return dp[W];
    }

    // 思路 1：动态规划 + 二维基本思路

    /**
     * 分组背包问题 - ç
     * 思路 1：动态规划 + 二维基本思路
     * 1. 阶段划分
     * 按照物品种类的序号、当前背包的载重上限进行阶段划分。
     * 2. 定义状态
     * 定义状态 `dp[i][w]` 表示为：前 i 组物品放入一个最多能装重量为 w 的背包中，可以获得的最大价值。
     * 状态 `dp[i][w]` 是一个二维数组，其中第一维代表「当前正在考虑的物品组数」，第二维表示「当前背包的载重上限」，二维数组值表示「可以获得的最大价值」。
     * 3. 状态转移方程
     * 由于我们可以不选择 i−1 组物品中的任何物品，也可以从第 i−1 组物品的第 `0∼group_count[i−1]−1` 件物品中随意选择 1 件物品，所以状态 `dp[i][w]` 可能从以下方案中选择最大值：
     * 不选择第 i−1 组中的任何物品：可以获得的最大价值为 `dp[i−1][w]`。
     * 选择第i−1 组物品中第 0 件：可以获得的最大价值为 `dp[i−1][w−weight[i−1][0]]+value[i−1][0]`。
     * 选择第 i−1 组物品中第 1 件：可以获得的最大价值为 `dp[i−1][w−weight[i−1][1]]+value[i−1][1]`。
     * ……
     * 选择第 i−1 组物品中最后 1 件：假设 k=group_count[i−1]−1，则可以获得的最大价值为 `dp[i−1][w−weight[i−1][k]]+value[i−1][k]`。
     * 则状态转移方程为：`dp[i][w]=max{dp[i−1][w],dp[i−1][w−weight[i−1][k]]+value[i−1][k]},         0≤k≤group_count[i−1]`
     * 4. 初始条件
     * 如果背包载重上限为 0，则无论选取什么物品，可以获得的最大价值一定是 0，即 `dp[i][0]=0,0≤i≤size`。
     * 无论背包载重上限是多少，前 0 组物品所能获得的最大价值一定为 0，即 `dp[0][w]=0,0≤w≤W`。
     * 5. 最终结果
     * 根据我们之前定义的状态，`dp[i][w]` 表示为：前 i 组物品放入一个最多能装重量为 w 的背包中，可以获得的最大价值。则最终结果为 `dp[size][W]`，其中 size 为物品的种类数，W 为背包的载重上限。
     * <p>
     * 时间复杂度：O(n×W×C)，其中 n 为物品分组数量，W 为背包的载重上限，C 是每组物品的数量。因为 n×C=∑group_count[i]，所以时间复杂度也可以写成 O(W×∑group_count[i])。
     * 空间复杂度：O(n×W)。
     *
     * @param groupCount int[]，每组物品的个数
     * @param weight     int[][]，每组物品的重量，weight[i][k] 表示第 i 组第 k 个物品的重量
     * @param value      int[][]，每组物品的价值，value[i][k] 表示第 i 组第 k 个物品的价值
     * @param W          int，背包最大承重
     * @return int，最大可获得价值
     */
    public int groupPackMethod1(int[] groupCount, int[][] weight, int[][] value, int W) {
        int size = groupCount.length;
        // dp[i][w] 表示前 i 组物品，容量不超过 W 时的最大价值
        int[][] dp = new int[size + 1][W + 1];

        // 枚举前 i 组物品
        for (int i = 1; i <= size; i++) {
            // 枚举背包装载重量
            for (int w = 0; w <= W; w++) {
                //TODO 不选当前组任何物品的情况,这一步的代码是需要保留的，这是一个二维数组
                // （还没有进行二维数组优化，dp[w]初始值还不是上一个i时的结果，是0）
                //因为有k=0这一个递进关系，所以 dp[i][w] = dp[i - 1][w]; 一定会被执行，但是其他没有这一层会破坏递进关系
                dp[i][w] = dp[i - 1][w];
                // 枚举第 i - 1 组物品能取个数
                for (int k = 0; k < groupCount[i - 1]; k++) {
                    if (w >= weight[i - 1][k]) {
                        // dp[i][w] 取所有 dp[i - 1][w - weight[i - 1][k]] + value[i - 1][k] 中最大值
                        dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weight[i - 1][k]] + value[i - 1][k]);
                    }
                }
            }
        }
        return dp[size][W];
    }

    /**
     * 不选择第 i−1 组中的任何物品：可以获得的最大价值为 `dp[i−1][w]`。 位置特殊处理
     *
     * @param groupCount
     * @param weight
     * @param value
     * @param W
     * @return
     */
    public int groupPackMethod1_spe(int[] groupCount, int[][] weight, int[][] value, int W) {
        int size = groupCount.length;
        // dp[i][w] 表示前 i 组物品，容量不超过 W 时的最大价值
        int[][] dp = new int[size + 1][W + 1];

        // 枚举前 i 组物品
        for (int i = 1; i <= size; i++) {
            // 枚举背包装载重量
            for (int w = 0; w <= W; w++) {
                // 枚举第 i - 1 组物品能取个数
                for (int k = 0; k < groupCount[i - 1]; k++) {
                    if (w >= weight[i - 1][k]) {
                        // dp[i][w] 取所有 dp[i - 1][w - weight[i - 1][k]] + value[i - 1][k] 中最大值
                        dp[i][w] = Math.max(dp[i][w], dp[i - 1][w - weight[i - 1][k]] + value[i - 1][k]);
                    } else {
                        //TODO 不选当前组任何物品的情况,这一步的代码是需要保留的，这是一个二维数组
                        // （还没有进行二维数组优化，dp[w]初始值还不是上一个i时的结果，是0）
                        //因为有k=0这一个递进关系，所以 dp[i][w] = dp[i - 1][w]; 一定会被执行，但是其他没有这一层会破坏递进关系
                        dp[i][w] = dp[i - 1][w];
                    }
                }
            }
        }
        return dp[size][W];
    }

    // 思路 2：动态规划 + 滚动数组优化

    /**
     * 分组背包问题 - 滚动数组优化解法
     * 1. 阶段划分
     * 按照当前背包的载重上限进行阶段划分。
     * 2. 定义状态
     * 定义状态 dp[w] 表示为：将物品装入最多能装重量为 w 的背包中，可以获得的最大价值。
     * 3. 状态转移方程
     * dp[w]=max{dp[w],dp[w−weight[i−1][k]]+value[i−1][k]},0≤k≤group_count[i−1]
     * 4. 初始条件
     * 无论背包载重上限为多少，只要不选择物品，可以获得的最大价值一定是 0，即 dp[w]=0,0≤w≤W。
     * 5. 最终结果
     * 根据我们之前定义的状态， dp[w] 表示为：将物品装入最多能装重量为 w 的背包中，可以获得的最大价值。则最终结果为 dp[W]，其中 W 为背包的载重上限。
     * <p>
     * 时间复杂度：O(n×W×C)，其中 n 为物品分组数量，W 为背包的载重上限，C 是每组物品的数量。因为 n×C=∑group_count[i]，所以时间复杂度也可以写成 O(W×∑group_count[i])。
     * 空间复杂度：O(W)。
     *
     * @param groupCount int[]，每组物品的个数
     * @param weight     int[][]，每组物品的重量，weight[i][k] 表示第 i 组第 k 个物品的重量
     * @param value      int[][]，每组物品的价值，value[i][k] 表示第 i 组第 k 个物品的价值
     * @param W          int，背包最大承重
     * @return int，最大可获得价值
     */
    public int groupPackMethod2(int[] groupCount, int[][] weight, int[][] value, int W) {
        int size = groupCount.length;
        // dp[w] 表示容量为 w 时背包可获得的最大价值
        int[] dp = new int[W + 1];

        // 枚举前 i 组物品
        for (int i = 1; i <= size; i++) {
            // 逆序枚举背包装载重量
            //确保在更新 dp[w] 时，使用的 dp[w - weight[i-1][k]] 是上一阶段的值，避免了状态覆盖问题
            for (int w = W; w >= 0; w--) {
                // 枚举第 i - 1 组物品能取个数
                for (int k = 0; k < groupCount[i - 1]; k++) {
                    if (w >= weight[i - 1][k]) {
                        // dp[w] 取所有 dp[w - weight[i - 1][k]] + value[i - 1][k] 中最大值
                        dp[w] = Math.max(dp[w], dp[w - weight[i - 1][k]] + value[i - 1][k]);
                    }
                }
            }
        }
        return dp[W];
    }


    // 思路 1：动态规划 + 三维基本思路

    /**
     * 二维费用的背包问题 - 三维动态规划解法
     * 我们可以参考「0-1 背包问题」的状态定义和基本思路，在「0-1 背包问题」基本思路的基础上，增加一个维度用于表示物品的容量。
     * <p>
     * 思路 1：动态规划 + 三维基本思路
     * 1. 阶段划分
     * 按照物品种类的序号、当前背包的载重上限、容量上限进行阶段划分
     * <p>
     * 2. 定义状态
     * 定义状态 `dp[i][w][v]` 为：前i 件物品放入一个最多能装重量为 w、容量为 v 的背包中，可以获得的最大价值。
     * <p>
     * 3. 状态转移方程
     * `dp[i][w][v]=max(dp[i−1][w][v],dp[i−1][w−weight[i−1]][v−volume[i−1]]+value[i−1]),        0≤weight[i−1]≤w,0≤volume[i−1]≤v`
     * <p>
     * 注意：**采用这种「状态定义」和「状态转移方程」，往往会导致内存超出要求限制，所以一般我们会采用「滚动数组」对算法的空间复杂度进行优化**。
     * <p>
     * 4. 初始条件
     * ----- 如果背包载重上限为 0 或者容量上限为 0，则无论选取什么物品，可以获得的最大价值一定是 0，即：
     * ---------- 1. `dp[i][w][0]=0,0≤i≤size,0≤w≤W`
     * ---------- 2. `dp[i][0][v]=0,0≤i≤size,0≤v≤V`
     * ----- 无论背包载重上限是多少，前 0 种物品所能获得的最大价值一定为 0，即：`dp[0][w][v]=0,0≤w≤W,0≤v≤V`
     * 5. 最终结果
     * 根据我们之前定义的状态，`dp[i][w][v]`表示为：前 i 件物品放入一个最多能装重量为 w、容量为 v 的背包中，可以获得的最大价值。则最终结果为`dp[size][W][V]`，其中 size 为物品的种类数，W 为背包的载重上限，V 为背包的容量上限。
     * <p>
     * 时间复杂度：O(n×W×V)，其中 n 为物品分组数量，W 为背包的载重上限，V 为背包的容量上限。
     * 空间复杂度：O(n×W×V)。
     *
     * @param weight int[]，每件物品的重量
     * @param volume int[]，每件物品的体积
     * @param value  int[]，每件物品的价值
     * @param W      int，背包最大承重
     * @param V      int，背包最大容量
     * @return int，最大可获得价值
     */
    public int twoDCostPackMethod1(int[] weight, int[] volume, int[] value, int W, int V) {
        int size = weight.length;
        // dp[i][w][v] 表示前 i 件物品，重量不超过 W，体积不超过 V 时的最大价值
        int[][][] dp = new int[size + 1][W + 1][V + 1];

        // 枚举前 i 组物品
        for (int i = 1; i <= size; i++) {
            // 枚举背包装载重量
            // w不能从weight[i-1]开始，是为了推荐状态，滚动数组优化后可以
            for (int w = 0; w <= W; w++) {
                // 枚举背包装载容量
                // v不能从volume[i-1]开始，是为了推荐状态，滚动数组优化后可以
                for (int v = 0; v <= V; v++) {
                    // 第 i - 1 件物品装不下
                    if (w < weight[i - 1] || v < volume[i - 1]) {
                        // dp[i][w][v] 取「前 i - 1 件物品装入装载重量为 w、装载容量为 v 的背包中的最大价值」
                        dp[i][w][v] = dp[i - 1][w][v];
                    } else {
                        // dp[i][w][v] 取所有 dp[w - weight[i - 1]][v - volume[i - 1]] + value[i - 1] 中最大值
                        dp[i][w][v] = Math.max(
                                dp[i - 1][w][v],
                                dp[i - 1][w - weight[i - 1]][v - volume[i - 1]] + value[i - 1]);
                    }
                }
            }
        }
        return dp[size][W][V];
    }

    // 思路 2：动态规划 + 滚动数组优化

    /**
     * 二维费用的背包问题 - 滚动数组优化解法
     * 思路 2：动态规划 + 滚动数组优化
     * 1. 阶段划分
     * 按照当前背包的载重上限、容量上限进行阶段划分。
     * 2. 定义状态
     * 定义状态 `dp[w][v]` 表示为：将物品装入最多能装重量为 w、容量为 v 的背包中，可以获得的最大价值。
     * 3. 状态转移方程
     * `dp[w][v]=max{dp[w][v],dp[w−weight[i−1]][v−volume[i−1]]+value[i−1]},         0≤weight[i−1]≤w,0≤volume[i−1]≤v`
     * 4. 初始条件
     * 如果背包载重上限为 0 或者容量上限为 0，则无论选取什么物品，可以获得的最大价值一定是 0，即：
     * `dp[w][0]=0,0≤w≤W`
     * `dp[0][v]=0,0≤v≤V`
     * 5. 最终结果
     * 根据我们之前定义的状态，
     * `dp[w][v]` 表示为：将物品装入最多能装重量为 w、容量为 v 的背包中，可以获得的最大价值。则最终结果为 `dp[W][V]`，其中 W 为背包的载重上限，V 为背包的容量上限。
     *
     * @param weight int[]，每件物品的重量
     * @param volume int[]，每件物品的体积
     * @param value  int[]，每件物品的价值
     * @param W      int，背包最大承重
     * @param V      int，背包最大容量
     * @return int，最大可获得价值
     */
    public int twoDCostPackMethod2(int[] weight, int[] volume, int[] value, int W, int V) {
        int size = weight.length;
        // dp[w][v] 表示重量不超过 w，体积不超过 v 时的最大价值
        int[][] dp = new int[W + 1][V + 1];

        // 枚举前 i 组物品
        for (int i = 1; i <= size; i++) {
            // 逆序枚举背包装载重量，w >= 0 改为 w >= weight[i - 1] 优化时间复杂度
            for (int w = W; w >= weight[i - 1]; w--) {
                // 逆序枚举背包装载容量，v >= 0 改为 volume[i - 1] 优化时间复杂度
                for (int v = V; v >= volume[i - 1]; v--) {
                    // dp[w][v] 取所有 dp[w - weight[i - 1]][v - volume[i - 1]] + value[i - 1] 中最大值
                    dp[w][v] = Math.max(dp[w][v], dp[w - weight[i - 1]][v - volume[i - 1]] + value[i - 1]);
                }
            }
        }

        return dp[W][V];
    }
}
