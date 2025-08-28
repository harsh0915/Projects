package in.creations.sapphire.DataModels;

import android.content.pm.ResolveInfo;

public class Tile {

    private final TileType type;
    private final ResolveInfo resolve_info;

    public Tile(TileType type, ResolveInfo resolve_info) {
        this.type = type;
        this.resolve_info = resolve_info;
    }

    public int getType() {
        return type.getValue();
    }

    public ResolveInfo getResolve_info() {
        return resolve_info;
    }

    public int getTileDimension(int typeVal) {
        TileType type = TileType.enumFromValue(typeVal);
        int NORMAL_TILE_SPAN = 2;
        int SMALL_TILE_SPAN = 1;
        int WIDE_TILE_SPAN = 4;
        if (type != null) {
            switch (type) {

                case SMALL_TILE:
                    return SMALL_TILE_SPAN;


                case WIDE_TILE:
                    return WIDE_TILE_SPAN;


                case NORMAL_TILE:
                default:
                    return NORMAL_TILE_SPAN;
            }
        } else {
            return NORMAL_TILE_SPAN;
        }
    }

}
