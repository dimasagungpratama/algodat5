/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work5;

/**
 *
 * @author Dimas
 */
public class Booster {

    //Data Members
    int degradeToLeaf, //Degradation to Leaf
            degradeFromParent;  //Degradation from Parent
    boolean boosterHere;    //True if Booster Placed Here

    //Methods
    public Booster(int fromParent) {
        degradeFromParent = fromParent;
    }

    @Override
    public String toString() {
        return boosterHere + " " + degradeToLeaf + " " + degradeFromParent;
    }

    public static void placeBoosters(BinaryTreeNode x) {
        //Compute Degradation at x. Place Booster
        //Here if Degradation Exceeds Tolerance

        //Initialize Degradation at x
        Booster elementX = (Booster) x.getElement();
        elementX.degradeToLeaf = 0;

        //Compute Degradation from Left Subtree of x and Place a Booster at The Left Child of x if Needed
        BinaryTreeNode y = x.getLeftChild();
        if(y != null)
        {
            //x Has a Nonempty Left Subtree
            Booster elementY = (Booster) y.getElement();
            int degradation = elementY.degradeToLeaf + elementY.degradeFromParent;
            if(degradation > tolerance)
            {
                //Place Booster at y
                elementY.boosterHere = true;
                elementX.degradeToLeaf = elementY.degradeFromParent;
            }
            else //No Booster Needed at y
                elementX.degradeToLeaf = degradation;
        }
        
        //Compute Degradation from Right Subtree of x and Place a Booster at The Right Child of x if Needed
        y = x.getRightChild();
        if(y != null)
        {
            //x Has a Nonempty Left Subtree
            Booster elementY = (Booster) y.getElement();
            int degradation = elementY.degradeToLeaf + elementY.degradeFromParent;
            if(degradation > tolerance)
            {
                //Place Booster at y
                elementY.boosterHere = true;
                degradation = elementY.degradeFromParent;
            }
            elementX.degradeToLeaf = Math.max(elementX.degradeFromParent, degradation);
        }
    }

}