import javax.swing.tree.TreeNode;
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
    public int minDepth(TreeNode root) {
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

    /**
     * 通过Queue方式实现找出最小深度
     *
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
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
    }

    /**
     * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
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
            for(String ref : refs){
                if(ref.indexOf(temp[0]) > -1){
                    line = ref;
                    break;
                }
            }
            for (int j = 0; j < temp.length; j++) {//循环遍历字符数组
                if(line.indexOf(temp[j]) < 0){
                    add = false;
                }
            }
            if (add){
                list.add(words[i]);
            }
        }
        return list.toArray(new String[list.size()]);
    }
}


