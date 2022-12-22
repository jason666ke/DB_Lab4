package data;

public enum Features {
    common("中庸的", "相比其他性格的猫咪，TA们需要更多的时间来确认周围的环境是否足够安全"),
    outgoing("外向的", "这种性格的猫有着强烈的探索心，对任何陌生的事物都很好奇，总是迫不及待地想要去“历险”。"),
    quiet("安静的", "这种性格的猫非常需要自己的隐私空间，TA们对肢体接触很敏感，不喜欢主人的抚摸");

    private int id;
    private final String name;
    private final String description;

    Features(String name) {
        this.id = ordinal();
        this.name = name;
        this.description = null;
    }

    Features(String name, String description) {
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
