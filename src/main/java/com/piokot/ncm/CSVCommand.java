/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.collect.Lists;
import com.piokot.ncm.api.Command;
import com.piokot.ncm.api.SentenceFormat;
import java.util.Iterator;
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
     * Output stream for the command.
     */
    private final transient Appendable output;
    /**
     * Output convert.
     */
    private final transient SentenceFormat format;
    /**
     * Input stream cache.
     */
    private final transient InputStreamCache icache;

    /**
     * Class constructor.
     *
     * @param input Input stream cache.
     * @param ostream Output stream.
     */
    public CSVCommand(final InputStreamCache input, final Appendable ostream) {
        this.icache = input;
        this.format = new CSVSentence();
        this.output = ostream;
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
        this.output.append(this.header());
        final Iterator<Sentence> text = new Text(this.icache.reader());
        while (text.hasNext()) {
            this.output.append(System.lineSeparator());
            this.output.append(this.format.convert(text.next()));
        }
    }

    @Override
    public Option option() {
        return this.optn;
    }

    /**
     * Count of words comma separated. Number of words is of the sentence with
     * maximum words. For example for content "hello world. Bye."
     * header is ", Word 1, Word 2".
     *
     * @return Header in the CSV format.
     */
    public String header() {
        final SentenceFormat count = new SentenceFormat() {
            @Override
            public String convert(final Sentence sentence) {
                return String.valueOf(
                    Lists.newArrayList(sentence.words()).size()
                );
            }
        };
        final Iterator<Sentence> text = new Text(this.icache.reader());
        int max = 0;
        while (text.hasNext()) {
            final int sum = Integer.parseInt(count.convert(text.next()));
            if (sum > max) {
                max = sum;
            }
        }
        final StringBuilder head = new StringBuilder(0);
        //@checkstyle IllegalTokenCheck (1 line)
        for (int ind = 0; ind < max; ind++) {
            head.append(", Word ").append(ind + 1);
        }
        return head.toString();
    }
}
