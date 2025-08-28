package in.creations.sapphire.DataModels;

public enum TileType {
    SMALL_TILE(0),
    NORMAL_TILE(1),
    WIDE_TILE(2);

    private final int value;

    TileType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static TileType enumFromValue(int value) {
        for (TileType type : TileType.values()) {
            if (type.getValue() == value) return type;
        }
        return null;
    }
}
