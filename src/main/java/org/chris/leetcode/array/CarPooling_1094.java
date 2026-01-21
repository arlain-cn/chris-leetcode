package org.chris.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向）
 * <p>
 * 给定整数 capacity 和一个数组 trips ,  trips[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有 numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。
 * <p>
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 * 示例 2：
 * <p>
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 */
public class CarPooling_1094 {


    public static void main(String[] args) {
        CarPooling_1094 carPooling1094 = new CarPooling_1094();
//        System.out.println(carPooling1094.carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 4));
//        System.out.println(carPooling1094.carPooling(new int[][]{{2, 1, 5}, {3, 3, 7}}, 5));
//        System.out.println(carPooling1094.carPooling(new int[][]{{3, 2, 7}, {3, 7, 9}, {8, 3, 9}}, 11));
        System.out.println(carPooling1094.carPooling(new int[][]{{9, 0, 1}, {3, 3, 7}}, 4));
    }

    /**
     *
     * @param trips
     * @param capacity
     * @return
     * @see 差分数组
     * 这样一来，当我们枚举完整个数组 trips 后，数组 count 中的最大值即为需要的座位数量，我们将其与 capacity 进行比较，即可得到答案。
     * 然而这样做的时间复杂度较高（虽仍然可以通过本题），我们可以使用差分数组来进行优化。
     * 差分数组是前缀和的逆运算。我们用数组 diff 表示数组 count 的差分数组，那么：
     * <p>
     * count[0]=diff[0]
     * count[1]=diff[0]+diff[1]
     * count[2]=diff[0]+diff[1]+diff[2]
     * ⋯
     * count[k]=diff[0]+diff[1]+diff[2]+⋯+diff[k]
     * ⋯
     * 也就是：
     * diff[k]={
     * count[0],   k=0
     * count[k]−count[k−1],    k>0
     * <p>
     * 1. 当 k<fromi时，diff[k] 的值不变；
     * 2. 当 k=fromi时，diff[k] 的值增加 numi；
     * 3. 当 fromi<k<toi时，diff[k] 的值不变；
     * 4. 当 k=toi时，diff[k] 的值减少 numi；
     * 5. 当 k>toi时，diff[k] 的值不变。
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/car-pooling/solutions/2546591/pin-che-by-leetcode-solution-scp6/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean carPooling(int[][] trips, int capacity) {
        int maxMiles = 0;
        for (int i = 0; i < trips.length; i++) {
            maxMiles = Math.max(maxMiles, trips[i][2]);
        }

        int diff[] = new int[maxMiles + 1];
        for (int i = 0; i < trips.length; i++) {
            diff[trips[i][1]] += trips[i][0];
            diff[trips[i][2]] -= trips[i][0];
        }
        int count = 0;
        for (int i = 0; i <= maxMiles; i++) {
            count += diff[i];
            if (count > capacity) {
                return false;
            }
        }
        return true;
    }

    public boolean carPooling_2(int[][] trips, int capacity) {
        int maxMiles = 0;
        for (int i = 0; i < trips.length; i++) {
            int mile = trips[i][2];
            if (maxMiles < mile) {
                maxMiles = mile;
            }
        }

        int[] allMiles = new int[maxMiles + 1];
        for (int i = 0; i < trips.length; i++) {
            int[] trip = trips[i];
            int curLeft = trip[1];
            int curRight = trip[2];
            for (; curLeft < curRight; curLeft++) {
                allMiles[curLeft] += trip[0];
                if (allMiles[curLeft] > capacity) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling_err(int[][] trips, int capacity) {
        for (int i = 0; i < trips.length; i++) {
            int[] trip = trips[i];
            int numPassengers = trip[0];
            int start = trip[1];
            for (int j = 0; j < i; j++) {
                int[] element = trips[j];
                if (element[2] > start) {
                    if (numPassengers + element[0] > capacity) {
                        return false;
                    }
                    numPassengers += element[0];
                }
            }
        }
        return true;
    }
}
