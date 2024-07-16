package bstmap;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class BSTNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public BSTNode<K, V> left;
        public BSTNode<K, V> right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private class BSTMapIterator implements Iterator<K> {

        private List<K> bstMapList;

        public BSTMapIterator() {
            bstMapList = new ArrayList<>();
            iteratorHelper(root, bstMapList);
        }

        private void iteratorHelper(BSTNode<K, V> node, List<K> list) {
            if (node != null) {
                iteratorHelper(node.left, list);
                list.add(node.key);
                iteratorHelper(node.right, list);
            }
        }

        @Override
        public boolean hasNext() {
            return !bstMapList.isEmpty();
        }

        @Override
        public K next() {
            return bstMapList.remove(0);
        }
    }

    private BSTNode<K, V> root;
    private int size;

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return find(root,  key) != null;
    }

    @Override
    public V get(K key) {
        BSTNode<K, V> result = find(root, key);
        if (result == null) {
            return null;
        } else {
            return result.value;
        }
    }

    private BSTNode<K, V> find(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        } else {
            if (node.key.compareTo(key) > 0) {
                return find(node.left, key);
            } else if (node.key.compareTo(key) < 0) {
                return find(node.right, key);
            } else {
                return node;
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper( key, value, root);
        size++;
    }

    private BSTNode<K, V> putHelper(K key, V value, BSTNode<K, V> node) {
        if (node == null) {
            return new BSTNode<>(key, value);
        } else {
            if (node.key.compareTo(key) < 0) {
                node.right = putHelper(key, value, node.right);
            } else if (node.key.compareTo(key) > 0) {
                node.left = putHelper(key, value, node.left);
            } else {
                node.value = value;
                return node;
            }
            return node;
        }
    }

    @Override
    public Set<K> keySet() {
        return new HashSet(new BSTMapIterator().bstMapList);
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        } else {
            V value = find(root, key).value;
            root = removeHelper(key, root);
            size--;
            return value;
        }
    }

    @Override
    public V remove(K key, V value) {
        BSTNode<K, V> node = find(root, key);
        if (node == null || !node.value.equals(value)) {
            return null;
        } else {
            return remove(key);
        }
    }

    private BSTNode<K, V> findBiggestInLeft(BSTNode<K, V> node) {
        if (node.right == null) {
            return node;
        } else {
            return findBiggestInLeft(node.right);
        }
    }

    private BSTNode<K, V> findSmallestInRight(BSTNode<K, V> node) {
        if (node.left == null) {
            return node;
        } else {
            return findSmallestInRight(node.left);
        }
    }

    private BSTNode<K, V> removeHelper(K key, BSTNode<K, V> node) {
        if (node.key.compareTo(key) < 0) {
            node.right = removeHelper(key, node.right);
        } else if (node.key.compareTo(key) > 0) {
            node.left = removeHelper(key, node.left);
        } else {
            BSTNode<K, V> newNode;
            BSTNode<K, V> parentNode;
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                newNode = findSmallestInRight(node.right);
                if (node.right.key == newNode.key) {
                    parentNode = node;
                    parentNode.right = newNode.right;
                } else {
                    parentNode = findParentInLeft(newNode.key, node.right);
                    parentNode.left = newNode.right;
                }
            } else {
                newNode = findBiggestInLeft(node.left);
                if (node.left.key == newNode.key) {
                    parentNode = node;
                    parentNode.left = newNode.left;
                } else {
                    parentNode = findParentInRight(newNode.key, node.left);
                    parentNode.right = newNode.left;
                }
            }
            node.key = newNode.key;
            node.value = newNode.value;
        }
        return node;
    }

    private BSTNode<K,V> findParentInLeft(K key, BSTNode<K,V> node) {
        if (node.left.key == key) {
            return node;
        } else {
            return findParentInLeft(key, node.left);
        }
    }

    private BSTNode<K, V> findParentInRight(K key, BSTNode<K, V> node) {
        if (node.right.key == key) {
            return node;
        } else {
            return findParentInRight(key, node.right);
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    public void printInOrder() {
        printHelper(root);
    }

    private void printHelper(BSTNode<K, V> node) {
        if (node != null) {
            printHelper(node.left);
            System.out.println("Key: " + node.key + " Value: " + node.value + "\n");
            printHelper(node.right);
        }
    }
}
