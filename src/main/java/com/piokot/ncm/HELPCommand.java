/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Joiner;
import com.piokot.ncm.api.Command;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * Command for showing help.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class HELPCommand implements Command {
    /**
     * Option for HELP.
     */
    private static final String OPT = "h";
    /**
     * Command line option.
     */
    private final transient Option optn;

    /**
     * Class constructor.
     */
    public HELPCommand() {
        this.optn = new Option(OPT, false, "help information");
    }

    @Override
    public boolean canRun(final CommandLine cline) {
        return cline.getOptions().length > 1
            || cline.hasOption(OPT)
            || cline.getOptions().length == 0
            || !cline.getArgList().isEmpty();
    }

    @Override
    public void run() {
        new HelpFormatter().printHelp(
            "java -jar target/ncm-task-1.0-SNAPSHOT.jar [-x] [-c] < input",
            Joiner.on('\n')
                .join(
                    "",
                    "Converts character stream with UTF_8 charset",
                    "to sentences with sorted words.",
                    "Output is put to the stdout.",
                    "Options:",
                    "",
                    "-x output is in XML form",
                    "-c output is in CSV form",
                    "-h this help is displayed."
                ),
            new Options(),
            ""
        );
    }

    @Override
    public Option option() {
        return this.optn;
    }
}
