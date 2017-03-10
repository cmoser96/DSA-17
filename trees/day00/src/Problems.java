import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        Collections.sort(values);
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        if(values.size()==1){
            tree.add(values.get(0));
            return tree;
        }
        TreeFromList(tree, values, 0, values.size()-1);
        return tree;
    }

    private static void TreeFromList(BinarySearchTree<Integer> tree, List<Integer> values, int lower, int upper){
        if(lower>upper){
            return;
        }
        int middle = (lower+upper)/2;
        tree.add(values.get(middle));

        TreeFromList(tree, values, lower, middle-1);
        TreeFromList(tree, values, middle+1, upper);
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        if(n1 == null && n2 == null){
            return true;
        }
        if(n1 == null || n2 == null){
            return false;
        }
        if(n1.key != n2.key){
            return false;
        }
        return (isIsomorphic(n1.leftChild, n2.leftChild) && isIsomorphic(n1.rightChild, n2.rightChild)) ||
                (isIsomorphic(n1.leftChild, n2.rightChild) && isIsomorphic(n1.rightChild, n2.leftChild));
    }
}
