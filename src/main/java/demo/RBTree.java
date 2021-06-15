package demo;

public class RBTree {
    public class Node{
        private Node father = null;
        private Node left = null;
        private Node right = null;
        private boolean color = false;
        public int data;

        public Node(int data){
            this.data = data;
            this.color = true;
        }
    }

    private Node root = null;

    public boolean isExistData(Node root,int data){
        if (root == null){
            return false;
        }
        if (root.data == data){
            return true;
        }else if (root.data > data){
            return null != root.left && isExistData(root.left,data);
        }else {
            return null != root.right && isExistData(root.right,data);
        }
    }

    public boolean isRed(Node node){
        return null != node && node.color;
    }

    public void insert(Node root,int data){
        if (isExistData(root,data)){
            return;
        }

        Node node = new Node(data);

        if (null == root){
            this.root = node;
            this.root.color = false;
            return;
        }
        else if(root.data > data){
            if (null == root.left){
                root.left = node;
                node.father = root;
            }
            else {
                insert(root.left,data);
            }
        }else {
            if (null == root.right){
                root.right = node;
                node.father = node;
            }else {
                insert(root.right,data);
            }
        }

        fixAfterInsert(node);
    }

    public void fixAfterInsert(Node node){
        while (null != node.father && node.father.color){
            Node father = node.father;
            Node grandpa = node.father.father;
            Node uncle;
            if (father == grandpa.left){
                uncle = grandpa.right;
                if (isRed(uncle)){
                    uncle.color = false;
                    father.color = false;
                    grandpa.color = true;
                    node = grandpa;
                    continue;
                }

                if (node == father.right){
                    node = father;
                    leftRotation(node);
                    Node temp = father;
                    father = node;
                    node = temp;
                }

                father.color = false;
                grandpa.color = true;
                rightRotation(grandpa);
            }
            else {
                uncle = grandpa.left;
                if (isRed(uncle)){
                    uncle.color = false;
                    father.color = false;
                    grandpa.color = true;
                    node = grandpa;
                    continue;
                }
                if (node == father.left){
                    rightRotation(father);
                    Node temp = father;
                    father = node;
                    node = temp;
                }
                father.color = false;
                grandpa.color = true;
                leftRotation(grandpa);
            }
        }
        this.root.color=false;
    }

    public void leftRotation(Node node){
        if (null != node){
            Node right = node.right;
            node.right = right.left;
            if (null != right.left){
                right.left.father = node;
            }
            right.left = node;
            right.father = node.father;
            if (null == node.father){
                this.root = right;
            }else if (node.father.left == node){
                node.father.left = right;
            }else{
                node.father.right = right;
            }
            node.father = right;
        }
    }

    public void rightRotation(Node node){
        if (null != node){
            Node left = node.left;
            node.left = left.right;
            if (null != left.right){
                left.right.father = node;
            }
            left.right = node;
            left.father = node.father;
            if (null == left.father){
                this.root = left;
                left.color = false;
            }
            else if (node.father.left == node){
                node.father.left = left;
            }else {
                node.father.right = left;
            }
            node.father = left;
        }
    }

}
