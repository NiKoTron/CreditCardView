package com.cooltechworks.creditcarddesign.interval;


public class Node<K, D> {

    private final D value;
    private final K key;

    private Node<K, D> leftLeaf;
    private Node<K, D> rightLeaf;

    public Node(K key, D value) {
        this.key = key;
        this.value = value;
    }


    public K getKey() {
        return key;
    }
    public D getValue() {
        return value;
    }


    public Node<K, D> getLeftLeaf() {
        return leftLeaf;
    }

    public Node<K, D> getRightLeaf() {
        return rightLeaf;
    }

    public void setLeftLeaf(Node<K, D> leftLeaf) {
        this.leftLeaf = leftLeaf;
    }

    public void setRightLeaf(Node<K, D> rightLeaf) {
        this.rightLeaf = rightLeaf;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node<?, ?> bNode = (Node<?, ?>) o;

        if (getValue() != null ? !getValue().equals(bNode.getValue()) : bNode.getValue() != null) return false;
        if (getKey() != null ? !getKey().equals(bNode.getKey()) : bNode.getKey() != null) return false;
        if (getLeftLeaf() != null ? !getLeftLeaf().equals(bNode.getLeftLeaf()) : bNode.getLeftLeaf() != null)
            return false;
        return getRightLeaf() != null ? getRightLeaf().equals(bNode.getRightLeaf()) : bNode.getRightLeaf() == null;
    }

    @Override
    public int hashCode() {
        int result = getValue() != null ? getValue().hashCode() : 0;
        result = 31 * result + (getKey() != null ? getKey().hashCode() : 0);
        result = 31 * result + (getLeftLeaf() != null ? getLeftLeaf().hashCode() : 0);
        result = 31 * result + (getRightLeaf() != null ? getRightLeaf().hashCode() : 0);
        return result;
    }
}
