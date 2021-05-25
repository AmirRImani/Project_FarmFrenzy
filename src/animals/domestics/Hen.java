package animals.domestics;

import animals.Speed;
import animals.Values;
import products.Products;

public class Hen extends Domestic {
    public Hen() {
        super(Values.HEN.getValue(), Products.EGG, TimeProducts.HEN.getTime(), Speed.HEN.getSpeed());
    }
}
