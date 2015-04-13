/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.piokot.ncm.api.Command;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

/**
 * Command for executing CSV command line option.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Slf4j
public final class CSVCommand implements Command {
    /**
     * Option for CSV output.
     */
    private static final String OPT = "c";
    /**
     * Command line option.
     */
    private final transient Option optn;
    /**
     * Input stream for the command.
     */
    private final transient InputStream istrm;
    /**
     * Output stream for the command.
     */
    private final transient OutputStream ostrm;

    /**
     * Class constructor.
     *
     * @param istream Input stream.
     * @param ostream Output stream.
     */
    public CSVCommand(final InputStream istream, final OutputStream ostream) {
        this.istrm = istream;
        this.ostrm = ostream;
        this.optn = new Option(OPT, false, "output in CSV format");
    }

    @Override
    public boolean canRun(final CommandLine cline) {
        return cline.getArgList().isEmpty()
            && cline.getOptions().length == 1
            && cline.hasOption(OPT);
    }

    @Override
    @SneakyThrows
    public void run() {
        log.info("Running CSV convert");
        final InputStreamSplit split = new InputStreamSplit(this.istrm);
        final MaxNumberStream numerics = new MaxNumberStream();
        new SentenceTransform<>(new SumFormat(), numerics).convert(
            split.streamCopy()
        );
        new SentenceTransform<>(
            new CSVFormat(numerics.maximum()),
            new SentenceStream(this.ostrm)
        ).convert(split.streamCopy());
    }

    @Override
    public Option option() {
        return this.optn;
    }
}
