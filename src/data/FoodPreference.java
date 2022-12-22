package data;

public enum FoodPreference {
    dairy("乳制品", "那些诸如奶酪之类的奶制品，猫咪也是特别喜欢的"),
    fruit("水果", "猫咪它是杂食性动物，比如说一些比较甜的水果就是猫咪特别喜欢吃的"),
    meat("肉类", "它最喜欢吃的还是肉类，鱼肉就是它最喜欢吃的"),
    smallDriedFish("小鱼干", "宠主们让猫咪吃小鱼干的时候一定要注意，不要让它吃的太多");

    private int id;
    private final String name;
    private final String description;

    FoodPreference(String name) {
        this.id = ordinal();
        this.name = name;
        this.description = null;
    }

    FoodPreference(String name, String description) {
        this.id = ordinal();
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }
}
