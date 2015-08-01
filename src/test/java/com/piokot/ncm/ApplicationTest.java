/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Application} class.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class ApplicationTest {
    /**
     * Output stream.
     */
    private transient ByteArrayOutputStream out;
    /**
     * Error stream.
     */
    private transient ByteArrayOutputStream err;

    /**
     * Set up for each test.
     */
    @Before
    public void setUp() {
        System.setIn(
            new ByteArrayInputStream("content".getBytes(Charsets.UTF_8))
        );
        this.out = new ByteArrayOutputStream();
        this.err = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.out));
        System.setErr(new PrintStream(this.err));
    }

    /**
     * Can output correct XML document.
     *
     * @throws Exception If it fails.
     */
    @Test
    public void outputXML() throws Exception {
        Application.main("-x");
        Assert.assertEquals(
            "XML content not same",
            Joiner.on(System.lineSeparator()).join(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
                "<text>",
                "<sentence>",
                "<word>content</word>",
                "</sentence>",
                "</text>"
            ),
            this.out.toString()
        );
    }

    /**
     * Can output correct CSV document.
     *
     * @throws Exception If it fails.
     */
    @Test
    public void outputCSV() throws Exception {
        //@checkstyle MultipleStringLiterals (1 line)
        Application.main("-c");
        Assert.assertEquals(
            "CSV content not same",
            Joiner.on(System.lineSeparator()).join(
                ", Word 1",
                "Sentence 1, content"
            ),
            this.out.toString()
        );
    }

    /**
     * Can output correct Help message.
     *
     * @throws Exception If it fails.
     */
    @Test
    public void outputHelp() throws Exception {
        Application.main("-h");
        // @checkstyle LineLength (4 lines)
        Assert.assertEquals(
            "Help content not same",
            Joiner.on(System.lineSeparator()).join(
                "usage: java -jar target/ncm-task-1.0-SNAPSHOT.jar [-x | -c] < input",
                "",
                "Converts character stream with UTF_8 charset",
                "to sentences with sorted words.",
                "Output is put to the stdout.",
                "Options:",
                "-x output is in XML form",
                "-c output is in CSV form",
                "-h this help is displayed.",
                ""
            ),
            this.err.toString()
        );
    }
    /**
     * Can output correct CSV document with PL locale.
     *
     * @throws Exception If it fails.
     */
    @Test
    public void outputLocaleCSV() throws Exception {
        System.setIn(
            new ByteArrayInputStream("moo łoo".getBytes(Charsets.UTF_8))
        );
        Locale.setDefault(Locale.forLanguageTag("PL"));
        Application.main("-c");
        Assert.assertEquals(
            "CSV locale content not same",
            Joiner.on(System.lineSeparator()).join(
                ", Word 1, Word 2",
                "Sentence 1, łoo, moo"
            ),
            this.out.toString()
        );
    }
}
