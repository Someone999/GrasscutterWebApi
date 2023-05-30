package com.hsman.web.handlers.command;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.hsman.utils.CommandUtils;
import com.hsman.web.annotations.Route;
import com.hsman.web.help.ArgumentDescription;
import com.hsman.web.help.ArgumentDescriptions;
import com.hsman.web.help.Help;
import com.hsman.web.requests.data.CommandRequestData;
import com.hsman.web.responses.ApiResponse;
import io.javalin.http.Context;
import kotlin.Suppress;

import java.util.ArrayList;

@Route(name = "multiCommand")
public class MultiCommandHandler implements CommandHandler {
    @Override
    @SuppressWarnings(value = {"unchecked"})
    public void handle(CommandRequestData request, Context context) {
        Gson gson = new Gson();
        var commands = request.getData().get("commands");
        var type = new TypeToken<ArrayList<MultiCommandModel>>(){}.getType();
        var models = (ArrayList<MultiCommandModel>) gson.fromJson(commands, type);
        JsonArray executedCommands = new JsonArray();
        for (var model : models) {
            var sourcePlayer = model.getSourcePlayer().getPlayer();
            var targetPlayer = model.getTargetPlayer().getPlayer();

            if(sourcePlayer == null) {
                sourcePlayer = request.getSourcePlayer().getPlayer();
            }

            if(targetPlayer == null) {
                targetPlayer = request.getTargetPlayer().getPlayer();
            }

            CommandUtils.Invoke(model.command, sourcePlayer, targetPlayer);

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("command", model.command);
            jsonObject.addProperty("sourcePlayer", sourcePlayer == null ? null : sourcePlayer.getUid());
            jsonObject.addProperty("targetPlayer", targetPlayer == null ? null : targetPlayer.getUid());
            executedCommands.add(jsonObject);
        }

        JsonObject data = new JsonObject();
        data.add("executedCommand", executedCommands);
        ApiResponse.createSuccess(data).send(context);



    }

    @Override
    public Help getHelp() {
        Help help = new Help();


        var commandDesc = ArgumentDescription
                .builder()
                .name("command")
                .type("String")
                .description("Command to execute")
                .optional(false)
                .build();

        var multiCommandModel  = ArgumentDescription.builder()
                .description("A Command structure")
                .name("command")
                .type("Object")
                .optional(true)
                .build();

        multiCommandModel.addSubArgumentDescription(ArgumentDescriptions.getPlayerDataArgumentDescription());
        multiCommandModel.addSubArgumentDescription(commandDesc);


        ArgumentDescription commandsDesc = ArgumentDescription
                .builder()
                .description("Command array")
                .name("commands")
                .type("array")
                .optional(false)
                .build();

        commandsDesc.addSubArgumentDescription(multiCommandModel);
        help.addArgumentDescription(commandsDesc);
        help.setCommand("multiCommand");
        help.addUsage(
                """
                        "type": "multiCommand",
                        "data": {
                           "commands":
                           [
                              {
                                 "playerData": {
                                     "sourcePlayer": "me",
                                     "targetPlayer": "10001"
                                 },
                                 "commands": "command"
                              }
                           ]
                        }
                """);

        return help;
    }
}

