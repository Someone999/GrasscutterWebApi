package com.hsman.web.handlers.command;

import com.hsman.web.help.Help;
import com.hsman.web.requests.data.CommandRequestData;
import io.javalin.http.Context;

public interface CommandHandler {
    void handle(CommandRequestData request, Context context);
    Help getHelp();
}
