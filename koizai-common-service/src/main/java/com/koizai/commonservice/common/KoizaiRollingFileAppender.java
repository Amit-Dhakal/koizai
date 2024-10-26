package com.koizai.commonservice.common;

import ch.qos.logback.core.rolling.RollingFileAppender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KoizaiRollingFileAppender<E> extends RollingFileAppender<E> {
    @Override
    public void writeOut(E event) throws IOException {
        if (!Files.exists(Paths.get(getFile()))) {
            super.openFile(getFile()); // recreates file
        }
        super.writeOut(event);
    }
}
