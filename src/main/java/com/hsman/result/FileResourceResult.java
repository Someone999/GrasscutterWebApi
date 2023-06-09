package com.hsman.result;

import java.io.File;
import java.io.IOException;

public class FileResourceResult extends ResourceResult<File> {

    private boolean created;

    public FileResourceResult(File result) {
        super(result);
    }
    public FileResourceResult(File result, Exception exception) {
        super(result, exception);
    }

    @Override
    public boolean isCreated() {
        if (isExists()) {
            return false;
        }

        return created;
    }

    @Override
    public boolean isExists() {
        return result.exists();
    }

    @Override
    public boolean create() {
        if(isExists()) {
            return false;
        }

        try {
            return created = result.createNewFile();
        } catch (IOException e) {
            return created = false;
        }
    }
}
