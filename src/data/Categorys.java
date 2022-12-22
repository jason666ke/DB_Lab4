package data;

public enum Categorys {
    americaShortHair("美国短毛猫", "美国短毛猫天性良善、随和、在家庭中很受欢迎。"),
    britishShortHair("英国短毛猫", "英国短毛猫很常见，它们友好、友爱，不是特别粘人。"),
    doll("布偶猫", "布偶猫放松、开心、就像小孩子的玩偶一样。"),
    garFiled("加菲猫", "异国短毛猫友爱、温顺、有着波斯猫类似的安静特质。"),
    persian("波斯猫", "波斯猫甜美、温柔，它们喜欢安定的环境，并且喜欢被温柔的对待。");

    private final int id;
    private final String name;
    private final String description;

    Categorys(String name, String description) {
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
