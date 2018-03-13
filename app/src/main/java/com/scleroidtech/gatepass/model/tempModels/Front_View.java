package com.scleroidtech.gatepass.model.tempModels;

/**
 * Copyright (C) 3/13/18
 * Author ganesh
 */

public class Front_View {
    private int actionImageDrawable;
    private String title;

    public Front_View(int actionImageDrawable, String title) {
        this.actionImageDrawable = actionImageDrawable;
        this.title = title;
    }

    public int getActionImageDrawable() {
        return actionImageDrawable;
    }

    public void setActionImageDrawable(int actionImageDrawable) {
        this.actionImageDrawable = actionImageDrawable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
