package com.hsman.web.help;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Help {
    ArrayList<ArgumentDescription> argumentDescriptions = new ArrayList<>();
    ArrayList<String> usages = new ArrayList<>();
    @Getter @Setter
    String description;

    @Getter @Setter
    String command;


    public ArgumentDescription addArgumentDescription(String name, String type, String description, boolean optional) {
        ArgumentDescription argumentDescription =
                ArgumentDescription.builder().name(name).type(type).description(description).optional(optional).build();

        argumentDescriptions.add(argumentDescription);
        return argumentDescription;
    }

    public ArgumentDescription addArgumentDescription(ArgumentDescription argumentDescription) {
        argumentDescriptions.add(argumentDescription);
        return argumentDescription;
    }

    public void addUsage(String usage) {
        usages.add(usage);
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("command", command);
        jsonObject.addProperty("description", description);
        JsonArray argDescArray = new JsonArray();
        for (var argDesc : argumentDescriptions) {
            argDescArray.add(argDesc.asJsonObject());
        }

        JsonArray usagesArray = new JsonArray();
        for(var usage : usages) {
            usagesArray.add(usage);
        }

        jsonObject.add("arguments", argDescArray);
        jsonObject.add("usages", usagesArray);
        return jsonObject;
    }

}
