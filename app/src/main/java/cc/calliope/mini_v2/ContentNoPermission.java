package cc.calliope.mini_v2;

import cc.calliope.mini_v2.utils.Permission;

public enum ContentNoPermission {
    BLUETOOTH(R.drawable.ic_bluetooth_disabled, R.string.title_bluetooth_permission, R.string.info_bluetooth_permission),
    LOCATION(R.drawable.ic_location_disabled, R.string.title_location_permission, R.string.info_location_permission);

    private final int icResId;
    private final int titleResId;
    private final int infoResId;

    ContentNoPermission(int icResId, int titleResId, int infoResId) {
        this.icResId = icResId;
        this.titleResId = titleResId;
        this.infoResId = infoResId;
    }

    public static ContentNoPermission getContent(@Permission.RequestType int requestType){
        if(requestType == Permission.LOCATION)
            return LOCATION;
        return BLUETOOTH;
    }

    public int getIcResId() {
        return icResId;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public int getInfoResId() {
        return infoResId;
    }
}
