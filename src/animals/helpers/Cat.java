package animals.helpers;
;
import animals.Speed;
import animals.Values;
import products.Product;

import java.util.HashSet;

public class Cat extends Helper {

    public Cat() {
        super(Values.CAT.getValue(), Speed.CAT.getSpeed());
    }

    public void findProducts(HashSet<Product> productsOnGround){

    }

}
