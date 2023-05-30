package com.hsman.web.handlers.command;

import com.google.gson.JsonObject;
import com.hsman.web.annotations.Route;
import com.hsman.web.help.ArgumentDescription;
import com.hsman.web.help.ArgumentDescriptions;
import com.hsman.web.help.Help;
import com.hsman.web.requests.data.CommandRequestData;
import com.hsman.web.responses.ApiResponse;
import emu.grasscutter.Grasscutter;
import io.javalin.http.Context;

@Route(name = "singleCommand")
public class SingleCommandHandler implements CommandHandler {
    @Override
    public void handle(CommandRequestData request, Context context) {
        var jsonData = request.getData();
        var commandString = jsonData.get("command").getAsString();
        var sourcePlayer = request.getSourcePlayer().getPlayer();
        var targetPlayer = request.getTargetPlayer().getPlayer();

        Grasscutter.getCommandMap().invoke(sourcePlayer, targetPlayer, commandString);

        JsonObject data = new JsonObject();
        data.addProperty("command", commandString);
        data.addProperty("sourcePlayer", sourcePlayer == null ? null : sourcePlayer.getUid());
        data.addProperty("targetPlayer", sourcePlayer == null ? null : targetPlayer.getUid());
        ApiResponse.createSuccess(data).send(context);
    }

    @Override
    public Help getHelp() {
        Help help = new Help();
        help.addArgumentDescription("command", "String", "Command to execute.", false);
        help.addArgumentDescription(ArgumentDescriptions.getPlayerDataArgumentDescription());
        return help;
    }


}
