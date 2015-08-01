/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.github.piotrkot.cli.CommandLineArgs;
import com.google.common.collect.Lists;
import com.piokot.ncm.api.Command;
import com.piokot.ncm.api.SentenceFormat;
import java.text.Collator;
import java.util.Iterator;
import java.util.Locale;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

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
        this.format =
            new CSVSentence(Collator.getInstance(Locale.getDefault()));
        this.output = ostream;
    }

    @Override
    public boolean canRun(final CommandLineArgs cli) {
        return cli.getOptions().size() == 1
            && cli.findOption("c").iterator().hasNext();
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

    /**
     * Count of words comma separated. Number of words is of the sentence with
     * maximum words. For example for content "hello world. Bye."
     * header is ", Word 1, Word 2".
     *
     * @return Header in the CSV format.
     */
    public String header() {
        final SentenceFormat count = sentence -> String.valueOf(
            Lists.newArrayList(sentence.words()).size()
        );
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
