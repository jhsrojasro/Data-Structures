/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

/**
 *
 * @author Sebastian Rojas
 */
public class AVLTree {
    private static class AVLNode{
        int data;
        AVLNode left;
        AVLNode right;
        int height;

        public AVLNode(int servicio, AVLNode left, AVLNode right, int height) {
            this.data = servicio;
            this.left = left;
            this.right = right;
            this.height = height;
        }

        public AVLNode(int dato, int height) {
            this.data = dato;
            this.height = height;
            this.left = null;
            this.right = null;
        }
    }
    
    private AVLNode root;
    
    //Metodos:

    public AVLTree() {
        this.root = null;
    }
    
    
    public AVLTree(int root) {
        this.root = new AVLNode(root,0);
    }
    
    public void insertar(int n){
        this.root  = insertar(this.root, n);
    }
    
    private AVLNode insertar(AVLNode root, int n){
        if(root == null){ root = new AVLNode(n, 0); return root;}
        if(n == root.data) return root;
        if(root.data > n){ 
            root.left = insertar(root.left,n);
            if(height(root.left) - height(root.right) == 2){
                if(root.left.data > n){
                    root = rotacionSimpleDerecha(root);
                }else{
                    root = rotacionDobleIzqDer(root);
                }
            }
        }else if(root.data < n){
            root.right = insertar(root.right, n);
            if(root.right.height - (root.left != null ? root.left.height : -1) == 2){
                if(root.right.data < n) root = rotacionSimpleIzquierda(root);
                else root  = rotacionDobleDerIzq(root);
            }
        }
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }
    
    private int findMin(AVLNode root){
        if(root != null){
            AVLNode aux = root;
            while(aux.left != null) aux = aux.left;
            return aux.data;
        }else{
            return -1;
        }
    }
    
    private int findMax(AVLNode root){
        if(root != null){
            AVLNode aux = root;
            while(aux.right != null) aux = aux.right;
            return aux.data;
        }else{
            return -1;
        }
    }
    
    public void borrar(int n){
        this.root = borrar(this.root, n);
    }
    
    private AVLNode borrar(AVLNode root, int n){
        if(root == null) return null;
        if(root.data  == n){
            if(root.right != null){
                root.right = borrar(root.right, findMin(root.right));
            }else if(root.left != null){
                root.left = borrar(root.left, findMax(root.left));
            }else{
                root = null;
            }
            return root;
        }else if(root.data > n){
            root.left = borrar(root.left, n);
            if(root.left == null && root.right != null && root.right.height == 1){
                root = rotacionSimpleIzquierda(root);
                root.height++;
            }
        }else{
            root.right = borrar(root.right, n);
            if(root.right == null && root.left != null && root.left.height == 1){
                root = rotacionSimpleDerecha(root);
                root.height++;
            }
        }
        return root;
    }
    
    public void print(){
        printPreOrder(this.root);
        System.out.println();
    }
    
    public void printHeight(){
        printPreOrderHeight(this.root);
        System.out.println();
    }
    
    private int height(AVLNode x){
        return (x == null ? -1 : x.height);
    }
    
    private void printPreOrderHeight(AVLNode root){
       if(root != null){
            if(root.left != null) printPreOrderHeight(root.left);
            System.out.print(root.height+" ");
            if(root.right != null) printPreOrderHeight(root.right);
        } 
    }
    
    private void printPreOrder(AVLNode root){
        if(root != null){
            if(root.left != null) printPreOrder(root.left);
            System.out.print(root.data+" ");
            if(root.right != null) printPreOrder(root.right);
        }
    }
    
    public boolean isEmpty(){
        return this.root == null;
    }
    
    private AVLNode rotacionSimpleDerecha(AVLNode root){
        root.height--;
        AVLNode temp = root.left;
        root.left = temp.right;
        temp.right = root;
        root = temp;
        return root;
    }
    
    private AVLNode rotacionSimpleIzquierda(AVLNode root){
        root.height--;
        AVLNode temp = root.right;
        root.right = temp.left;
        temp.left = root;
        root = temp;
        return root;
    }
    
    private AVLNode rotacionDobleIzqDer(AVLNode root){
        root.left = rotacionSimpleIzquierda(root.left);
        root = rotacionSimpleDerecha(root);
        return root;
        
    }
    
    private AVLNode rotacionDobleDerIzq(AVLNode root){
        root.right = rotacionSimpleDerecha(root.right);
        root = rotacionSimpleIzquierda(root);
        return root;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AVLTree arbolito = new AVLTree();
        arbolito.insertar(20);
        arbolito.insertar(10);
        arbolito.insertar(30);
        arbolito.insertar(5);
        arbolito.insertar(25);
        arbolito.insertar(40);
        arbolito.insertar(35);
        arbolito.insertar(45);
        arbolito.print();
        arbolito.printHeight();
        arbolito.insertar(34);
        arbolito.print();
        arbolito.printHeight();
    }
    
}
