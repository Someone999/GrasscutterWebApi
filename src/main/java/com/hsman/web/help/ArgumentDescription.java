package com.hsman.web.help;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Builder
public class ArgumentDescription {
    @Getter @Setter @Builder.Default
    String name = null;

    @Getter @Setter @Builder.Default
    String type = null;

    @Getter @Setter @Builder.Default
    String description = null;

    @Getter @Setter @Builder.Default
    boolean optional = false;

    @Builder.Default
    ArrayList<ArgumentDescription> subArgumentDescriptions = new ArrayList<>();
    public ArgumentDescription addSubArgumentDescription(String name, String type, String description, boolean optional) {
        ArgumentDescription argumentDescription =
                ArgumentDescription.builder().name(name).type(type).description(description).optional(optional).build();

        subArgumentDescriptions.add(argumentDescription);
        return argumentDescription;
    }

    public ArgumentDescription addSubArgumentDescription(ArgumentDescription argumentDescription) {
        subArgumentDescriptions.add(argumentDescription);
        return argumentDescription;
    }

    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("type", type);
        jsonObject.addProperty("description", description);
        jsonObject.addProperty("optional", optional);
        if(subArgumentDescriptions.size() > 0) {
            JsonArray subArgDesc = new JsonArray();
            for (var argDesc :subArgumentDescriptions) {
                subArgDesc.add(argDesc.asJsonObject());
            }

            jsonObject.add("subArguments", subArgDesc);
        }

        return jsonObject;
    }
}
