import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static List<Integer> levelOrderTraversal(TreeNode<Integer> n) {
        LinkedList<Integer> nodes = new LinkedList<>();
        Queue<TreeNode<Integer>> q = new LinkedList<>();
        q.add(n);

        while(!q.isEmpty()){
            TreeNode<Integer> node = q.poll();
            if(node!=null) {
                q.add(node.leftChild);
                q.add(node.rightChild);
                nodes.add(node.key);
            }
        }

        return nodes;
    }
}
