import java.util.*;

/**
 * Author:Young
 * Date:2020/6/10-13:20
 */
public class Solution {

    public static void main(String[] args) {

        int n = properResult();
        if (n != 0) {
            System.out.println(n);
        } else {
            System.out.println("Impossible");
        }
    }

    /**
     * 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
     * 空间复杂度高，时间复杂度O（n*n）
     */
    public int majorityElement(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
                if ((nums.length / 2) < count) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    /**
     * 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public static int majorityElement2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int temp = map.get(nums[i]);
                map.put(nums[i], ++temp);
            } else {
                map.put(nums[i], 1);
            }
        }
        int mid = nums.length / 2;
        for (Integer key : map.keySet()) {
            System.out.println(map.get(key));
            if (map.get(key) > mid) {
                return key;
            }
        }

        return -1;
    }

    public static int majorityElement3(int[] nums) {
        int n = nums.length;
        if (n == 0) return -1;
        int num = nums[0], cnt = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] == num) {
                cnt++;
            } else cnt--;
            if (cnt == -1) {
                num = nums[i];
                cnt = 1;
            }
        }
        return cnt > 0 ? num : -1;
    }

    /**
     * 未完成，程序错误
     *
     * @param s
     * @return
     */
    public String[] permutation2(String s) {

        char[] str = s.toCharArray();
        String[] res;//返回结果
        Set<String> set = new HashSet<>();//临时存放结果
        int length = str.length;
        for (int i = 0; i < length; i++) {
            StringBuilder sb = new StringBuilder(str[i]);
            Random r = new Random();
            //while(!set.contains(sb)){
            while (sb.length() < length) {
                String temp = new String(sb);
                int random = r.nextInt() * (length + 1);

                if (!temp.contains(String.valueOf(str[random]))) {
                    sb.append(str[random]);
                }
            }
            String med = new String(sb);
            if (!set.contains(med)) {
                set.add(med);
            }
            //}

        }
        return null;
    }

    /**
     * 近期某商场由于周年庆，开启了“0元购”活动。活动中，消费者可以通过组合手中的代金券，实现0元购买指定商品。
     * 聪明的小团想要用算法来帮助他快速计算：对于指定价格的商品，使用代金券凑出其价格即可，但所使用的代金券总面额不可超过商品价格。由于代金券数量有限，使用较少的代金券张数则可以实现价值最大化，即最佳优惠。
     * 假设现有100元的商品，而代金券有50元、30元、20元、5元四种，则最佳优惠是两张50元面额的代金券；而如果现有65元的商品，则最佳优惠是两张30元代金券以及一张5元代金券。
     * 请你帮助小团使用一段代码来实现代金券计算。
     *
     * @return
     */
    public static int properResult() {

        int total;//总金额
        int number;//卷的种类
        int coupon[];//存放不同面额的优惠卷
        int count = 0;
        Scanner in = new Scanner(System.in);
        total = in.nextInt();
        number = in.nextInt();
        coupon = new int[number];
        for (int i = 0; i < number; i++) {
            coupon[i] = in.nextInt();
        }
        if (in.nextInt() == 0) {
            Arrays.sort(coupon);
            if (total < coupon[0]) {
                return -1;
            } else {
                for (int j = number - 1; j >= 0; j--) {
                    if (total % coupon[j] == 0) {
                        return total / coupon[j];
                    } else {
                        count += total / coupon[j];
                        total = total % coupon[j];
                    }
                }
                return count;
            }

        }
        return -1;
    }

    /**
     * 输入一个字符串，打印出该字符串中字符的所有排列。
     * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
     * 输入：s = "abc"
     * 输出：["abc","acb","bac","bca","cab","cba"]
     */
    List<String> res = new LinkedList<>();
    char[]       c;

    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }

    void dfs(int x) {
        if (x == c.length - 1) {//达到最大长度
            res.add(String.valueOf(c));//将排列方案添加进，加入结果集
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for (int i = x; i < c.length; i++) {
            if (set.contains(c[i]))
                continue;//重复，因此减枝
            set.add(c[i]);//Set集合是无序集合，存取顺序不一致，随机，不能
            //存储null值
            swap(i, x);//交换，将c[i]固定在第x位
            dfs(x + 1);//开始固定第x+1位字符
            swap(i, x);//恢复交换
        }
    }

    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }

    /**
     * 给定一个 N 叉树，返回其节点值的前序遍历。
     */
    // Definition for a Node.
    class Node {
        public int        val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    List<Integer> list = new LinkedList<>();

    public List<Integer> preorder(Node root) {
        if (root == null) {
            return list;
        } else {
            list.add(root.val);
            Iterator<Node> iterator = root.children.iterator();
            while (iterator.hasNext()) {
                preorder(iterator.next());
            }
        }
        return list;
    }

    /**
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * @param root
     * @return
     */
    /*public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        //左子树的最小深度
        int left = minDepth(root.left);
        //右子树的最小深度
        int right = minDepth(root.right);
        //如果left和right都为0，我们返回1即可，
        //如果left和right只有一个为0，说明他只有一个子结点，我们只需要返回它另一个子节点的最小深度+1即可。
        //如果left和right都不为0，说明他有两个子节点，我们只需要返回最小深度的+1即可。
        return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
    }
*/
    /**
     * 通过Queue方式实现找出最小深度
     *
     * @param root
     * @return
     */
   /* public int minDepth2(TreeNode root) {
        if (root == null)
            return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);//入队
        int level = 0;
        while (!queue.isEmpty()) {//队列不为空就继续循环
            level++;
            int levelCount = queue.size();
            for (int j = 0; j < levelCount; j++) {
                TreeNode node = queue.poll();//出队
                //如果当前node节点的左右子树都为空，直接返回level即可
                if (node.left == null && node.right == null)
                    return level;
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return -1;
    }*/

    /**
     * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
     *
     * @param words
     * @return
     */
    public String[] findWords(String[] words) {

        List<String> list = new ArrayList<>();
        String[] refs = new String[]{
                "qwertyuiopQWERTYUIOP",
                "asdfghjklASDFGHJKL",
                "zxcvbnmZXCVBNM"
        };

        for (int i = 0; i < words.length; i++) {//循环遍历输入字符串
            char[] temp = words[i].toCharArray();//将输入字符串转化为字符数组
            String line = null;
            boolean add = true;
            for (String ref : refs) {
                if (ref.indexOf(temp[0]) > -1) {
                    line = ref;
                    break;
                }
            }
            for (int j = 0; j < temp.length; j++) {//循环遍历字符数组
                if (line.indexOf(temp[j]) < 0) {
                    add = false;
                }
            }
            if (add) {
                list.add(words[i]);
            }
        }
        return list.toArray(new String[list.size()]);
    }


    //六种排序算法

    int ar[] = {5, 6, 72, 3, 1, 56,};

    /**
     * 冒泡排序算法
     * a、冒泡排序，是通过每一次遍历获取最大/最小值
     * <p>
     * 　　b、将最大值/最小值放在尾部/头部
     * <p>
     * 　　c、然后除开最大值/最小值，剩下的数据在进行遍历获取最大/最小值
     * <p>
     * 　　d、代码实现
     */
    public static int[] bubbling(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; ) {
                if (arr[j] > arr[j++]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序算法
     * a、将第一个值看成最小值
     * <p>
     * 　　b、然后和后续的比较找出最小值和下标
     * <p>
     * 　　c、交换本次遍历的起始值和最小值
     * <p>
     * 　　d、说明：每次遍历的时候，将前面找出的最小值，看成一个有序的列表，后面的看成无序的列表，然后每次遍历无序列表找出最小值。
     * <p>
     * 　　e、代码实现
     */
    public static int[] selection(int[] arr) {

        int temp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序算法
     * a、默认从第二个数据开始比较。
     * <p>
     * 　　b、如果第二个数据比第一个小，则交换。然后在用第三个数据比较，如果比前面小，则插入（狡猾）。否则，退出循环
     * <p>
     * 　　c、说明：默认将第一数据看成有序列表，后面无序的列表循环每一个数据，如果比前面的数据小则插入（交换）。否则退出。
     * <p>
     * 　　d、代码实现
     */
    public static int[] insert(int[] arr) {
        int temp;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 希尔排序算法
     * a、基本上和插入排序一样的道理
     * <p>
     * 　　b、不一样的地方在于，每次循环的步长，通过减半的方式来实现
     * <p>
     * 　　c、说明：基本原理和插入排序类似，不一样的地方在于。通过间隔多个数据来进行插入排序。
     * <p>
     * 　　d、代码实现
     */
    public static int[] Hill(int[] arr) {

        for (int step = arr.length / 2; step > 0; step /= 2) {
            for (int i = 0; i < arr.length && (i + step < arr.length); i++) {
                if (arr[i] > arr[i + step]) {
                    int temp = arr[i];
                    arr[i] = arr[i + step];
                    arr[i + step] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 快速排序算法
     * a、确认列表第一个数据为中间值，第一个值看成空缺（低指针空缺）。
     * <p>
     * 　　b、然后在剩下的队列中，看成有左右两个指针（高低）。
     * <p>
     * 　　c、开始高指针向左移动，如果遇到小于中间值的数据，则将这个数据赋值到低指针空缺，并且将高指针的数据看成空缺值（高指针空缺）。
     *      然后先向右移动一下低指针，并且切换低指针移动。
     * <p>
     * 　　d、当低指针移动到大于中间值的时候，赋值到高指针空缺的地方。然后先高指针向左移动，并且切换高指针移动。重复c、d操作。
     * <p>
     * 　　e、直到高指针和低指针相等时退出，并且将中间值赋值给对应指针位置。
     * <p>
     * 　　f、然后将中间值的左右两边看成行的列表，进行快速排序操作。
     * <p>
     * 　　g、代码实现
     */
    public static void Rapid(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while (array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 当i=j时while循环将终止，将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        Rapid(array, left, i - 1);
        Rapid(array, i + 1, right);
    }

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
        List<Integer> lists = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int lack = target - nums[i];
            for (int j = 0; j < nums.length; j++) {
                if (lack == nums[j] && !lists.contains(j)) {
                    lists.add(i);
                    lists.add(j);
                }
            }
        }
        int[] res = new int[lists.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = lists.get(i);
        }
        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
     * 并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    public class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {

        int max = 0;
        List<Integer> list = new ArrayList<>();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            list.add(Length(s, i));
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int temp = iterator.next();
            if (temp >= max) {
                max = temp;
            }
        }
        return max;
    }

    public static int Length(String s, int index) {
        List<Character> list = new ArrayList<>();
        char[] str = s.toCharArray();
        for (int i = index; i < str.length; i++) {
            if (!list.contains(str[i])) {
                list.add(str[i]);
            } else {
                break;
            }
        }
        return list.size();
    }

    /**
     * 官方版本
     */
    public int lengthOfLongestSubstring1(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
     * <p>
     * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * <p>
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * <p>
     * 循环中该怎么写，什么时候 A 数组后移，什么时候 B 数组后移。用 aStart 和 bStart 分别表示当前指向 A
     * 数组和 B 数组的位置。如果 aStart 还没有到最后并且此时 A 位置的数字小于 B 位置的数组，那么就可以后
     * 移了。也就是aStart＜m&&A[aStart]< B[bStart]。
     * <p>
     * 但如果 B 数组此刻已经没有数字了，继续取数字 B[ bStart ]，则会越界，所以判断下 bStart 是否大于数组
     * 长度了，这样 || 后边的就不会执行了，也就不会导致错误了，所以增加为
     * aStart＜m&&(bStart) >= n||A[aStart]<B[bStart]) 。
     */
    /*public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m&&(bStart >= n || nums1[aStart] < nums2[bStart])){
                right = nums1[aStart++];
            }else {
                right = nums2[bStart++];
            }
        }
        if (len % 2 == 0){
            return (left+right)/2.0;
        }else{
            return right;
        }
    }*/
    public double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        for (int i = 0; i <= len / 2; i++) {
            left = right;
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            return right;
    }


    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        int max = 1;
        int index = 0;
        char[] str = s.toCharArray();
        for (int i = 0; i <= str.length - 1; i++) {
            for (int j = i + 1; j < str.length; j++) {
                if (j - i + 1 > max && validPalindromic(str, i, j)) {
                    index = i;
                    max = j - i + 1;
                }
            }
        }
        return s.substring(index, max + index);
    }

    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right) {
            if (charArray[left] != charArray[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * <p>
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * <p>
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     */
    public String convert(String s, int numRows) {

        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        int i = 0;
        int flag = -1;
        for (int n = 0; n < numRows; n++) {
            list.add(new StringBuilder());
        }
        for (char c : s.toCharArray()) {
            list.get(i).append(c);
            if (i == 0 || i == numRows - 1)
                flag = -flag;
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : list) {
            res.append(row);
        }
        return res.toString();
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数
     * 附加另一位数字是否会导致溢出。
     * 示例 1:
     * <p>
     * 输入: 123
     * 输出: 321
     *  示例 2:
     * <p>
     * 输入: -123
     * 输出: -321
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        long temp = 0;
        while (x != 0) {
            int pop = x % 10;
            temp = temp * 10 + pop;
            if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE) {
                return 0;
            }
            x /= 10;
        }
        return (int) temp;
    }


    /**
     * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
     * <p>
     * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
     * <p>
     * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
     * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
     * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
     * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
     * <p>
     * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
     * <p>
     * 提示：
     * <p>
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
     */

    public static int myAtoi(String str) {
        if (str.trim().equals("")) {
            return 0;
        }
        char[] s = str.trim().toCharArray();
        StringBuilder sb = new StringBuilder();
        if (s[0] == '-' || s[0] == '+' || (s[0] >= 48 && s[0] < 58)) {
            sb.append(s[0]);
        } else {
            return 0;
        }
        for (int i = 1; i < s.length; i++) {
            if (s[i] >= 48 && s[i] < 58) {
                sb.append(s[i]);
                if (Long.valueOf(sb.toString()) <= Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
                if (Long.valueOf(sb.toString()) >= Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
            } else {
                break;
            }
        }

        return (!sb.toString().equals("-") && !sb.toString().equals("+")) ? Integer.valueOf(sb.toString()) : 0;
    }


    public boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }

        // 当数字长度为奇数时，我们可以通过 revertedNumber/10 去除处于中位的数字。
        // 例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertedNumber = 123，
        // 由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertedNumber || x == revertedNumber / 10;
    }


    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     * <p>
     * 说明:
     * <p>
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
     * 示例 1:
     * <p>
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     */

    public boolean isMatch(String s, String p) {
        //如果正则串p为空字符串s也为空这匹配成功，如果正则串p为空但是s不是空则说明匹配失败
        if (p.isEmpty()) return s.isEmpty();
        //判断s和p的首字符是否匹配，注意要先判断s不为空
        boolean headMatched = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {//如果p的第一个元素的下一个元素是*
            //则分别对两种情况进行判断
            return isMatch(s, p.substring(2)) ||
                    (headMatched && isMatch(s.substring(1), p));
        } else if (headMatched) {//否则，如果s和p的首字符相等
            return isMatch(s.substring(1), p.substring(1));
        } else {
            return false;
        }
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 
     * 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器，且 n 的值至少为 2。
     * <p>
     * 示例：
     * <p>
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0, high = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int temp = 0;
                if (height[j] > height[i])
                    high = height[i];
                else high = height[j];
                temp = high * (j - i);
                if (temp > max) {
                    max = temp;
                }
            }
        }
        return max;

    }

    public int maxArea2(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]) :
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    /**
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
     * 所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 3
     * 输出: "III"
     *
     * @param num
     */
    public static String intToRoman(String num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] res = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();//线程不安全，不能保证原子性，但是效率比StringBuffer高，StringBuffer线程安全
        int number = Integer.parseInt(num);
        for (int i = 0; i < 13; i++) {
            while (number >= nums[i]) {
                sb.append(res[i]);
                number -= nums[i];
            }

        }
        return sb.toString();
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     * <p>
     * 示例 1:
     * <p>
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || c != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }


    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;
        int[][] dp = new int[row][column];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < column; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int j = 1; j < row; j++) {
            dp[j][0] = dp[j - 1][0] + grid[j][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[row - 1][column - 1];
    }

    public int[] sorted0(int[] a) {
        int count = 0,other = 0;
        int len = a.length;
        int[] zero = new int[len];
        for (int i = 0; i < len; i++) {
            if (a[i] == 0) {
                zero[len-1 - other] = a[i];
                other++;
            }else {
                zero[count] = a[i];
                count++;
            }
        }
        return zero;
    }

    public void exclude5() {
        for (int i = 1; i < 1000; i++) {
           String temp = String.valueOf(i) ;
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == '5'){
                    break;
                }
                if (j == temp.length()-1){
                    System.out.println(i);
                }
            }
        }
    }

    public void judge(int m){
        if (m % 2 == 0){
            System.out.println(m + "是偶数！");
        }else {
            System.out.println(m + "是奇数！");
        }
    }

    /**
     * 希尔排序
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr){
        int tem;
        for (int gap = arr.length / 2; gap > 0; gap /= 2){
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]){
                        tem = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = tem;
                    }
                }
            }
        }
        return arr;
    }

    public  int[] MergeSort(int[] arr){
        if (arr.length < 2){
            return arr;
        }
        int mid = arr.length >>> 1;
        return merge(MergeSort(Arrays.copyOfRange(arr,0,mid)),MergeSort(Arrays.copyOfRange(arr,mid,arr.length)));
    }

    public int[] merge(int[] left,int[] right){
        int[] result = new int[left.length + right.length];
        int i = 0,j = 0;
        for (int index = 0; index < result.length; index++) {
            if (i >= left.length) //>=的=不能去除，少了程序为错
                result[index] = right[j++];
            else if (j >= right.length)//必须先判断i或者j是否超出范围，如果先判断left[i] > right[j]就会可能出现程序越界出错
                result[index] = left[i++];
            else if (left[i] > right[j])
                result[index] = right[j++];
            else
                result[index] = left[i++];
        }
        return result;
    }

}


