package org.chris.leetcode._template;

import java.util.Arrays;
import java.util.Random;

public class SortArrayTemplate_912 {
    /**
     * 1、选择排序 selectionSort: 选择排序是最简单朴素的排序算法，但是时间复杂度较高，且不是稳定排序。其他基础排序算法都是基于选择排序的优化。
     * 2、冒泡排序 bubbleSort: 冒泡算法是对选择排序 的一种优化，通过交换 nums[sortedIndex] 右侧的逆序对完成排序，是一种稳定排序算法。
     * 3、插入排序 insertionSort: 插入排序是基于选择排序 的一种优化，将 nums[sortedIndex] 插入到左侧的有序数组中。对于有序度较高的数组，插入排序的效率比较高。
     * 4、希尔排序 shellSort: 希尔排序是基于插入排序 的简单改进，通过预处理增加数组的局部有序性，突破了插入排序的O(N2)时间复杂度。
     * 希尔排序的基本思想是：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
     * 5、归并排序 mergeSort: 归并排序的核心思路需要结合二叉树的后序遍历 来理解：先利用递归把左右两半子数组排好序，然后在二叉树的后序位置合并两个有序数组。
     * 6、快速排序 quickSort: 快速排序的核心思路需要结合二叉树的前序遍历 来理解：在二叉树遍历的前序位置将一个元素排好位置，然后递归地将剩下的元素排好位置。
     * 7、堆排序 heapSort: 堆排序是从二叉堆结构衍生出来的排序算法，复杂度为 O(NlogN)。堆排序主要分两步，第一步是在待排序数组上原地创建二叉堆（Heapify），然后进行原地排序（Sort）。
     * 8、计数排序 countingSort: 计数排序的原理比较简单：统计每种元素出现的次数，进而推算出每个元素在排序后数组中的索引位置，最终完成排序。计数排序的时间和空间复杂度都是 O(n+max−min)，其中 n 是待排序数组长度，max−min 是待排序数组的元素范围。
     * 9、桶排序 bucketSort: 桶排序算法的核心思想分三步：1、将待排序数组中的元素使用映射函数分配到若干个「桶」中。2、对每个桶中的元素进行排序。3、最后将这些排好序的桶进行合并，得到排序结果。
     * 10、基数排序 radixSort: 基数排序是计数排序算法的扩展，它的主要思路是对待排序元素的每一位依次进行计数排序。由于计数排序是稳定的，所以对每一位完成计数排序后，所有元素就完成了排序。
     */
    public static void main(String[] args) {
        SortArrayTemplate_912 sortArrayTemplate912 = new SortArrayTemplate_912();
        int[] nums = {64, 34, 25, 12, 22, 11, 90, 88, 76, 50, 42, 35, 17, 8, 99, 23, 67, 45, 78, 31, 56, 89, 14, 92, 37, 61, 28, 73, 49, 5, 81, 39, 72, 26, 63, 19, 94, 48, 77, 33, 68, 21, 55, 83, 16, 95, 41, 79, 29, 66, 38, 85, 13, 52, 87, 30, 69, 24, 59, 91, 46, 75, 20, 57, 84, 32, 65, 18, 97, 44, 71, 27, 58, 86, 15, 100, 43, 74, 36, 62, 93, 47, 70, 2, 53, 82, 9, 54, 80, 1, 98, 40, 60, 3, 51, 7, 96, 4, 6, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 50, 50, 50, 25, 25, 75, 75, 12, 12, 88, 88, 37, 37, 63, 63, 91, 91};
//        sortArrayTemplate.optimizedBubbleSort(nums);
//        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(sortArrayTemplate912.heapSort(nums)));
    }

    /**
     * 7、堆排序 heapSort: 堆排序是从二叉堆结构衍生出来的排序算法，复杂度为 O(NlogN)。堆排序主要分两步，第一步是在待排序数组上原地创建二叉堆（Heapify），然后进行原地排序（Sort）。
     * 堆排序（Heap Sort）是一种基于堆数据结构的高效排序算法，通过构建最大堆（升序）或最小堆（降序）实现排序。其核心思想是利用堆的根节点始终为极值的特性，反复提取极值并调整堆结构，最终得到有序序列。以下是详细原理、Java实现及优化分析。
     * <p>
     * 最大堆：每个父节点的值 ≥ 子节点的值（根节点为最大值）。
     * 最小堆：每个父节点的值 ≤ 子节点的值（根节点为最小值）。
     * 堆是完全二叉树，可用数组高效存储（索引关系：父节点 i，左子节点 2i+1，右子节点 2i+2）
     * <p>
     * 2. 根节点编号为 0（常见于编程实现，如 C++、Java）
     * · 节点总数为 n。
     * · 最后一个非叶子节点的索引为 [n/2] - 1。
     * · 推导：节点 i 的左孩子为 2i+1，右孩子为 2i+2。最后一个节点索引为 n-1，其父节点索引为 [(n-1-1)/2] = [(n-2)/2] = [n/2]- 1。
     * <p>
     * <p>
     * 排序步骤：
     * 构建最大堆：从最后一个非叶子节点（n/2 - 1）开始，自底向上调整子树为最大堆。
     * 交换与调整：
     * 交换堆顶（最大值）与数组末尾元素；
     * 缩小堆范围（排除已排序元素）；
     * 对堆顶元素执行下沉操作（Heapify），恢复最大堆性质。
     * <p>
     * <p>
     * 重复交换与调整，直到堆仅剩一个元素。
     *
     * @param nums
     * @return
     */
    public int[] heapSort(int[] nums) {
        int len = nums.length;
        buildMaxHeap(nums, len);
        for (int i = len - 1; i > 0; i--) {
            swap(nums, i, 0);
            len--;
            //每次取位置0的排到堆顶
            maxHeapify(nums, len, 0);
        }
        return nums;
    }

    public void buildMaxHeap(int[] nums, int len) {
        //从最后一个非叶子节点（n/2 - 1）开始，自底向上调整子树为最大堆。
        for (int i = len / 2 - 1; i >= 0; i--) {
            maxHeapify(nums, len, i);
        }
    }

    /**
     * 堆调整（下沉操作）
     *
     * @param nums
     * @param len
     * @param i
     */
    private void maxHeapify(int[] nums, int len, int i) {
        for (; i * 2 + 1 < len; ) {
            int lson = i * 2 + 1;
            int rson = i * 2 + 2;
            int lagest = i;
            if (lson < len && nums[lson] > nums[lagest]) {
                lagest = lson;
            }
            if (rson < len && nums[rson] > nums[lagest]) {
                lagest = rson;
            }
            if (lagest == i) {
                break;
            } else {
                swap(nums, lagest, i);
                i = lagest;
            }
        }
    }

    /**
     * 6、快速排序 quickSort: 快速排序的核心思路需要结合二叉树的前序遍历 来理解：在二叉树遍历的前序位置将一个元素排好位置，然后递归地将剩下的元素排好位置。
     * 快速排序的核心思想是分治。它通过一趟排序将待排记录分隔成独立的两部分，使得其中一部分的所有数据都比另一部分的所有数据小，然后再分别对这两部分记录继续进行排序，以达到整个序列有序的目的。
     * 这个过程中，分区操作是整个算法的灵魂，其目标是选择一个元素作为“基准”，并围绕它重新排列数组
     * <p>
     * <p>
     * <p>为了避免最坏情况下的性能退化，常见的优化策略是随机选择基准 。这能极大降低每次都选到最值作为基准的概率，使得算法在平均情况下保持高效。
     * <p>
     *
     * @param nums
     * @return
     */
    public int[] quickSort(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        randomQuickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void randomQuickSort(int[] nums, int left, int right) {
        if (left < right) {
            int pos = randomPartition(nums, left, right);
            randomQuickSort(nums, left, pos - 1);
            randomQuickSort(nums, pos + 1, right);
        }
    }

    /**
     * 为了避免最坏情况下的性能退化，常见的优化策略是随机选择基准 。这能极大降低每次都选到最值作为基准的概率，使得算法在平均情况下保持高效。
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private int randomPartition(int[] nums, int left, int right) {
        int random = left + new Random().nextInt(right - left + 1);
        swap(nums, random, right);
        return partition(nums, left, right);
    }

    /**
     * 整个划分函数 partition 主要涉及两个指针 i 和 j，一开始 i = low - 1，j = low。我们需要实时维护两个指针使得任意时候，对于任意数组下标 k，我们有如下条件成立：
     * 1、low≤k≤i 时，nums[k]≤pivot。
     * 2、i+1≤k≤j−1 时，nums[k]>pivot。
     * 3、k==r 时，nums[k]=pivot。
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left - 1;
        for (int j = left; j <= right - 1; ++j) {
            if (nums[j] < pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        //放置基准：遍历完成后，将基准（arr[high]）与 i+1 位置的元素交换。此时，基准就位于其最终的正确位置上。分区操作返回基准的最终位置索引。
        swap(nums, i + 1, right);
        return i + 1;
    }


    /**
     * 5、归并排序 mergeSort: 归并排序的核心思路需要结合二叉树的后序遍历 来理解：先利用递归把左右两半子数组排好序，然后在二叉树的后序位置合并两个有序数组。
     * 归并排序（Merge sort）是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
     * 作为一种典型的分而治之思想的算法应用，归并排序的实现由两种方法：
     * 1、自上而下的递归（所有递归的方法都可以用迭代重写，所以就有了第 2 种方法）；
     * 2、自下而上的迭代；
     * <p>
     * 递归法实现 (自顶向下)
     *
     * @param nums
     * @return
     */
    public int[] mergeSort(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int mid = nums.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(nums, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(nums, mid, nums.length));

        return merge(left, right);
    }

    public int[] merge(int[] left, int[] right) {
        int[] res = new int[left.length + right.length];
        int i = 0;
        int li = 0, ri = 0;
        //俩都不为空时，取小值
        while (li < left.length && ri < right.length) {
            if (left[li] < right[ri]) {
                res[i++] = left[li++];
            } else {
                res[i++] = right[ri++];
            }
        }
        while (li < left.length) {
            res[i++] = left[li++];
        }

        while (ri < right.length) {
            res[i++] = right[ri++];
        }
        return res;
    }

    /**
     * 4、希尔排序是基于
     * 插入排序 的简单改进，通过预处理增加数组的局部有序性，突破了插入排序的时间复杂度。
     * 希尔排序的基本思想是：先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序，待整个序列中的记录“基本有序”时，再对全体记录进行依次直接插入排序。
     * 1. 预排序	让数据宏观上基本有序	选定一个增量 gap，将数组分为 gap 组，分别对每组进行插入排序。	“大跨步”调整：让小的元素和大的元素都能快速地向正确方向移动一大步。
     * 2. 缩小增量	让数据越来越接近有序	逐渐减小 gap 的值（如 gap = gap / 2），重复分组和排序的过程。	“小步快跑”精调：随着步长变小，调整越来越精细，数组也越来越有序。
     * 3. 最终插入排序	完成精确排序	当 gap 缩小至 1 时，整个数组被视为一组，进行最后一次标准的插入排序。	“微调收尾”：因为数组已基本有序，这次插入排序的效率会非常高。
     *
     * @param nums
     * @return
     */
    public int[] shellSort(int[] nums) {
        int n = nums.length;

        // 初始间隔设为数组长度的一半，然后逐渐减小间隔
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 对每个间隔进行插入排序
            for (int i = gap; i < n; i++) {
                // 保存当前元素
                int temp = nums[i];
                // 将间隔距离的元素向前移动，直到找到正确位置
                int j = i;

                while (j >= gap && nums[j - gap] > temp) {
                    //@see 优化了 swap 方法,之所以可以优化是因为在往前移动的一直是temp，比较大小的时候要用temp
                    nums[j] = nums[j - gap];
                    j -= gap;
                }

                // 将临时元素放到正确位置
                nums[j] = temp;
            }
        }

        return nums;
    }


    public int[] shellSort2(int[] nums) {
        int n = nums.length;

        // 初始间隔设为数组长度的一半，然后逐渐减小间隔
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // 对每个间隔进行插入排序
            for (int i = gap; i < n; i++) {
                // 将间隔距离的元素向前移动，直到找到正确位置
                for (int j = i; j >= gap && nums[j - gap] > nums[j]; j -= gap) {
                    swap(nums, j, j - gap);
                }
            }
        }

        return nums;
    }

    /**
     * 3、插入排序 insertionSort
     * 插入排序是基于
     * 选择排序 的一种优化，将 nums[sortedIndex] 插入到左侧的有序数组中。对于有序度较高的数组，插入排序的效率比较高。
     * <p>
     * {@link SortArrayTemplate_912#selectionSortMove(int[])}上面的算法思路是：在 nums[sortedIndex..] 中找到最小值，然后将其插入到 nums[sortedIndex] 的位置。
     * 那么我们能不能反过来想，在 nums[0..sortedIndex-1] 这个部分有序的数组中，找到 nums[sortedIndex] 应该插入的位置，然后进行插入呢？
     *
     * @param nums
     * @return
     */
    public int[] insertionSort(int[] nums) {
        int n = nums.length;
        // 维护 [0, sortedIndex) 是有序数组
        int sortedIndex = 0;
        while (sortedIndex < n) {
            //在 nums[0..sortedIndex-1] 这个部分有序的数组中，找到 nums[sortedIndex] 应该插入的位置
            for (int i = sortedIndex; i > 0; i--) {
                if (nums[i] < nums[i - 1]) {
                    swap(nums, i, i - 1);
                } else {
                    break;
                }
            }
            sortedIndex++;
        }

        return nums;
    }

    /**
     * 优化了 swap 方法,之所以可以优化是因为在往前移动的一直是temp，比较大小的时候要用temp
     *
     * @param nums
     * @return
     */
    public int[] optimizedInsertionSort(int[] nums) {
        int n = nums.length;
        // 维护 [0, sortedIndex) 是有序数组
        int sortedIndex = 0;
        while (sortedIndex < n) {
            //在 nums[0..sortedIndex-1] 这个部分有序的数组中，找到 nums[sortedIndex] 应该插入的位置
            int tmp = nums[sortedIndex];
            int moveTo = sortedIndex;
            for (int i = sortedIndex; i > 0; i--) {
                if (tmp < nums[i - 1]) {
                    nums[i] = nums[i - 1];
                    moveTo = i - 1;
                } else {
                    break;
                }
            }
            nums[moveTo] = tmp;
            sortedIndex++;
        }

        return nums;
    }

    /**
     * 2、冒泡排序 bubbleSort
     * Bubble Sort - Back to Front (Classic Approach)
     *
     * @param nums
     */
    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    /**
     * 2、冒泡排序 bubbleSort
     * Bubble Sort - Back to Front (Classic Approach)
     * 提前终止算法
     * 上面说到选择排序的一个问题是，其时间复杂度和初始数据的有序度完全没有关系，即便输入的数组已经有序，选择排序依然会执行
     *
     * @param nums
     */
    public void optimizedBubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            // 加一个布尔变量，记录是否进行过交换操作
            boolean swapped = false;
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                    swapped = true;
                }
            }
            // 如果一次交换操作都没有进行，说明数组已经有序，可以提前终止算法
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * 2、冒泡排序 bubbleSort
     * Bubble Sort - Front to Back (Alternative Approach)
     *
     * @param nums
     */
    public void bubbleSortFTB(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }


    /**
     * 1、选择排序 selectionSort优化
     * <p>
     * 是一种为了不破坏数组稳定性，在选择排序的直接 swap 的基础上模拟数组插入的搬移操作
     */
    public int[] selectionSortMove(int[] nums) {
        int n = nums.length;
        int sortedIndex = 0;
        while (sortedIndex < n) {
            int minIndex = sortedIndex;
            for (int i = sortedIndex + 1; i < n; i++) {
                if (nums[minIndex] > nums[i]) {
                    minIndex = i;
                }
            }


            int minVal = nums[minIndex];
            for (int i = minIndex; i > sortedIndex; i--) {
                nums[i] = nums[i - 1];
            }
            nums[sortedIndex] = minVal;
            sortedIndex++;
        }

        return nums;
    }

    /**
     * 1、选择排序 selectionSort
     * 选择排序是最简单朴素的排序算法，但是时间复杂度较高，且不是稳定排序。其他基础排序算法都是基于选择排序的优化。
     * 先遍历一遍数组，找到数组中的最小值，然后把它和数组的第一个元素交换位置；接着再遍历一遍数组，找到第二小的元素，和数组的第二个元素交换位置；以此类推，直到整个数组有序。
     *
     * @param nums
     */
    public void selectionSort(int[] nums) {
        int n = nums.length;
        // sortedIndex 是一个分割线
        // 索引 < sortedIndex 的元素都是已排序的
        // 索引 >= sortedIndex 的元素都是未排序的
        // 初始化为 0，表示整个数组都是未排序的
        int sortedIndex = 0;
        while (sortedIndex < n) {
            // 找到未排序部分 [sortedIndex, n) 中的最小值
            int minIndex = sortedIndex;
            for (int i = sortedIndex + 1; i < n; i++) {
                if (nums[i] < nums[minIndex]) {
                    minIndex = i;
                }
            }
            swap(nums, sortedIndex, minIndex);
            // sortedIndex 后移一位
            sortedIndex++;
        }
    }


    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }


    public void bubbleSort_err(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                //不应该用nums[i]，这么写没有冒泡的过程，还是相当于选择排序
                if (nums[i] > nums[j]) {
                    swap(nums, i, j);
                }
            }
        }
    }
}
