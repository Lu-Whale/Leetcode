package Top_Interview_150.Binary_Tree_General;


import java.util.HashMap;
import java.util.Map;

public class _105_Construct_Binary_Tree_from_Preorder_and_Inorder_Traversal {
    private HashMap<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode dfs(int[] preorder, int preLeft, int preRight, int inLeft, int inRight, HashMap<Integer, Integer> map) {
        if(preLeft > preRight || inLeft > inRight) {
            return null;
        }

        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = map.get(rootVal);



        root.left = dfs(preorder, preLeft + 1, pIndex - inLeft + preLeft, inLeft, pIndex - 1, map);
        root.right = dfs(preorder, (pIndex - inLeft + preLeft) + 1, preRight, pIndex + 1, inRight, map);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return dfs(preorder, 0, preorder.length - 1, 0, inorder.length - 1, indexMap);
//        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
}
