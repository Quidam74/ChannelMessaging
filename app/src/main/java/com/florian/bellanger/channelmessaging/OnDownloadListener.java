package com.florian.bellanger.channelmessaging;

/**
 * Created by bellangf on 19/01/2018.
 */
public interface OnDownloadListener {

    public void onDownloadComplete(String downloadedContent);

    public void onDownloadError(String error);
}
