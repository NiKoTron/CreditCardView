package com.cooltechworks.creditcarddesign.interval;



public class IntervalTree<K extends Comparable<K>, V> {
    private Node<Interval<K>, V> rootNode;

    public IntervalTree() {
    }

    public IntervalTree(Node<Interval<K>, V>... varg) {
        for (int i = varg.length/2; i < varg.length; i++) {
            this.put(varg[i].getKey(), varg[i].getValue());
        }
        for (int i = 0; i <= varg.length/2; i++) {
            this.put(varg[i].getKey(), varg[i].getValue());
        }
    }

    public boolean put(K i, V value) {
        return put(new Interval<>(i,i), value);
    }

    public boolean put(K s, K e, V value) {
        return put(new Interval<>(s,e), value);
    }

    private boolean put(Interval<K> i, V value) {
        Node<Interval<K>, V> node = new Node<>(i, value);
        if(rootNode == null){
            rootNode = node;
            return true;
        }

        Node<Interval<K>, V> nodeParent = searchLastNode(rootNode, node);

        K pS = nodeParent.getKey().getStart();
        K pE = nodeParent.getKey().getEnd();

        K nS = node.getKey().getStart();
        K nE = node.getKey().getEnd();

        if(pS.compareTo(nE) == 1 && pS.compareTo(nS) == 1){
            nodeParent.setLeftLeaf(node);
            return true;
        }
        if(pE.compareTo(nS) == -1 && pE.compareTo(nE) == -1){
            nodeParent.setRightLeaf(node);
            return true;
        }
        return false;
    }

    public V search(K key){
        Node<Interval<K>, V> node = search(rootNode, key);
        if(node.getKey().isEntry(key)){
            return node.getValue();
        }
        return null;
    }

    private Node<Interval<K>, V> searchLastNode(Node<Interval<K>, V> parentNode, Node<Interval<K>, V> node) {
        Node<Interval<K>, V> nextNode = nextNode(parentNode, node);
        if(!parentNode.equals(nextNode)) {
            nextNode = searchLastNode(nextNode, node);
        }
        return nextNode;
    }

    private Node<Interval<K>, V> search(Node<Interval<K>, V> node, K key){
        Node<Interval<K>, V> nextNode = nextNode(node, key);
        if(!node.equals(nextNode)){
            nextNode = search(nextNode, key);
        }
        return nextNode;
    }

    private Node<Interval<K>, V> nextNode(Node<Interval<K>, V> node, K key){
        if(node.getKey().compareTo(key) == -1 && node.getLeftLeaf() != null){
            return node.getLeftLeaf();
        }
        if(node.getKey().compareTo(key) == 1 && node.getRightLeaf() != null){
            return node.getRightLeaf();
        }
        return node;
    }

    private Node<Interval<K>, V> nextNode(Node<Interval<K>, V> parentNode, Node<Interval<K>, V> node) {
        if(parentNode.getKey().compareTo(node.getKey()) == -1 && parentNode.getRightLeaf() != null){
            return parentNode.getRightLeaf();
        }
        if(parentNode.getKey().compareTo(node.getKey()) == 1 && parentNode.getLeftLeaf() != null) {
            return parentNode.getLeftLeaf();
        }
        return parentNode;
    }

}
