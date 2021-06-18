package animals.helpers;

import animals.Animal;

public class Helper extends Animal {
    private String name;
    private int value;

    public String getName() { return name; }

    public Helper(Helpers helper) {
        super(helper.getSpeed());//TODO
        this.name = helper.name();
        this.value = helper.getValue();
    }

}
