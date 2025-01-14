package cc.calliope.mini_v2.ui.editors;

import cc.calliope.mini_v2.R;

public enum Editor {

    MAKECODE(
            R.string.title_make_code,
            R.drawable.ic_editors_makecode,
            R.string.info_make_code,
            "https://makecode.calliope.cc/?androidapp=1"),
    ROBERTA(
            R.string.title_roberta,
            R.drawable.ic_editors_roberta,
            R.string.info_roberta,
            "https://lab.open-roberta.org/#loadSystem&&calliope2017"),
    LIBRARY(
            R.string.title_library,
            R.drawable.ic_editors_library,
            R.string.info_library,
            "https://calliope.cc/calliope-mini/25programme#25"),
    SCRIPTS(
            R.string.title_scripts,
            R.drawable.ic_scripts,
            R.string.info_scripts,
            null);

    private final int titleResId;
    private final int iconResId;
    private final int infoResId;
    private final String url;

    Editor(int titleResId, int iconResId, int infoResId, String url) {
        this.titleResId = titleResId;
        this.iconResId = iconResId;
        this.infoResId = infoResId;
        this.url = url;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public int getIconResId() {
        return iconResId;
    }

    public int getInfoResId() {
        return infoResId;
    }

    public String getUrl() {
        return url;
    }
}