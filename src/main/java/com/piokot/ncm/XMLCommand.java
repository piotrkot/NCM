/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Joiner;
import com.piokot.ncm.api.Command;
import com.piokot.ncm.api.SentenceFormat;
import java.text.Collator;
import java.util.Iterator;
import java.util.Locale;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

/**
 * Command for executing XML command line option.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Slf4j
public final class XMLCommand implements Command {
    /**
     * Option for XML output.
     */
    private static final String OPT = "x";
    /**
     * Command line option.
     */
    private final transient Option optn;
    /**
     * Input stream cache.
     */
    private final transient InputStreamCache icache;
    /**
     * Output stream for the command.
     */
    private final transient Appendable output;
    /**
     * Output convert.
     */
    private final transient SentenceFormat format;

    /**
     * Class constructor.
     *
     * @param input Input stream cache.
     * @param ostream Output stream.
     */
    public XMLCommand(final InputStreamCache input, final Appendable ostream) {
        this.icache = input;
        this.format =
            new XMLSentence(Collator.getInstance(Locale.getDefault()));
        this.output = ostream;
        this.optn = new Option(OPT, false, "output in XML format");
    }

    @Override
    public boolean canRun(final CommandLine cline) {
        return cline.getArgList().isEmpty()
            && cline.getOptions().length == 1
            && cline.hasOption(OPT);
    }

    @SneakyThrows
    @Override
    public void run() {
        log.info("Running XML convert");
        final Iterator<Sentence> text = new Text(this.icache.reader());
        this.output.append(
            Joiner.on(System.lineSeparator()).join(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
                "<text>"
            )
        );
        while (text.hasNext()) {
            this.output.append(System.lineSeparator());
            this.output.append(this.format.convert(text.next()));
        }
        this.output.append(System.lineSeparator());
        this.output.append("</text>");
    }

    @Override
    public Option option() {
        return this.optn;
    }
}
