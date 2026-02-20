package org.yellowgrass.utils;

import com.google.gson.JsonObject;

public class AttachmentInfo {
    private JsonObject meta;
    private String path;
    private utils.File file;

    public AttachmentInfo(JsonObject meta, String path, utils.File file) {
        this.meta = meta;
        this.path = path;
        this.file = file;
    }

    public JsonObject getMeta() {
        return meta;
    }

    public String getPath() {
        return path;
    }

    public utils.File getFile() {
        return file;
    }
}
