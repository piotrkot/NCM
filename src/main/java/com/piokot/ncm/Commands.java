/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.piokot.ncm.api.Command;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Commands for the Application.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Slf4j
public final class Commands {
    /**
     * Commands that can handle command line arguments.
     */
    private final transient Command[] cmnds;
    /**
     * Parsed command line arguments with options.
     */
    private final transient CommandLine cline;

    /**
     * Class constructor.
     *
     * @param arguments Command line arguments.
     * @param commands Commands handling arguments.
     */
    @SneakyThrows
    public Commands(final String[] arguments, final Command... commands) {
        log.info("Read args: {}", arguments);
        this.cmnds = commands;
        final Options optns = new Options();
        for (final Command command : this.cmnds) {
            optns.addOption(command.option());
        }
        this.cline = new BasicParser().parse(optns, arguments);
        log.info("Command line args: {}", this.cline.getArgList());
    }

    /**
     * Route commands, find the one which can be run and run it.
     * <p/>
     * It is assured that only one command is run or none if none cannot be run.
     * When more that one command is satisfied, it cannot be determined which
     * one will get executed.
     */
    public void dispatch() {
        for (final Command command : this.cmnds) {
            if (command.canRun(this.cline)) {
                command.run();
                break;
            }
        }
    }
}
