package animals.helpers;
;
import products.Product;

import java.util.HashSet;

public class Cat extends Helper {
    public Cat() {
        super(Helpers.CAT);
    }


    public void findWalk(HashSet<Product> products) {
        Product nearProduct = nearProduct(products);
        if(nearProduct == null) {
            this.walk();
            return;
        } else
            this.findWalk(nearProduct.getX(), nearProduct.getY());
    }

    private Product nearProduct(HashSet<Product> products) {
        double min = 100;
        double distance;
        Product nearProduct = null;
        if(products.isEmpty())
            return null;
        for (Product product : products) {
            distance = Math.sqrt(Math.pow((this.getX() - product.getX()),2) + Math.pow((this.getY() - product.getY()),2));
            if(distance < min){
                min = distance;
                nearProduct = product;
            }
        }
        return nearProduct;
    }
}
