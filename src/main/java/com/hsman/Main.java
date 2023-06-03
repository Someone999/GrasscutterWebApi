package com.hsman;

import com.google.gson.Gson;
import com.hsman.config.MainConfig;
import com.hsman.plugin.mails.GameItem;
import com.hsman.plugin.mails.MailTemplate;
import com.hsman.utils.ResourceUtils;
import com.hsman.web.objectmanager.AmbiguousMethodMatchedException;
import com.hsman.plugin.mails.DateParser;


import javax.xml.crypto.Data;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws AmbiguousMethodMatchedException, IOException {
        var x = MailTemplate.parseContent("123<url('https://123.com', 'webview', 'title')>456");
        return;
    }
}