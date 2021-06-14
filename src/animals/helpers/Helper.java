package animals.helpers;

import animals.Animal;

public class Helper extends Animal {
    private int value;


    public Helper(Helpers helper) {
        super(helper.getSpeed());//TODO
        this.value = helper.getValue();
    }
}
