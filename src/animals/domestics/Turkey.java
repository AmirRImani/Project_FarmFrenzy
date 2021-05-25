package animals.domestics;

import animals.Speed;
import animals.Values;
import products.Products;

public class Turkey extends Domestic {
    public Turkey() {
        super(Values.TURKEY.getValue(), Products.FEATHER, TimeProducts.TURKEY.getTime(), Speed.TURKEY.getSpeed());

    }
}
