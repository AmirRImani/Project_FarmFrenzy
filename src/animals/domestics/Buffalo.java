package animals.domestics;

import animals.Speed;
import animals.Values;
import products.Products;

public class Buffalo extends Domestic {
    public Buffalo() {
        super(Values.BUFFALO.getValue(), Products.MILK, TimeProducts.BUFFALO.getTime(), Speed.BUFFALO.getSpeed());
    }

}
