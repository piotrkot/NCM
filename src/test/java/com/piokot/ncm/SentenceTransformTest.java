/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.piokot.ncm.api.TextFormat;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link SentenceTransform} class.
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class SentenceTransformTest {
    /**
     * Header.
     */
    private static final String HEAD = "header";
    /**
     * Footer.
     */
    private static final String FOOT = "footer";
    /**
     * Newline.
     */
    private final transient String nline = System.getProperty("line.separator");
    /**
     * Can convert one sentence with end.
     * @throws Exception If fails.
     */
    @Test
    public void convertOneSentenceWithEnd() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        new SentenceTransform<>(
            new TestTextFormat(HEAD, FOOT),
            new SentenceStream(out)
        ).convert(
            new ByteArrayInputStream("Hello world.".getBytes(Charsets.UTF_8))
        );
        Assert.assertEquals(
            "not converted with end",
            Joiner.on(this.nline).join(HEAD, "Hello world", FOOT, ""),
            out.toString()
        );
    }
    /**
     * Can convert one sentence without end.
     * @throws Exception If fails.
     */
    @Test
    public void convertOneSentenceWithoutEnd() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final String input = "one two";
        new SentenceTransform<>(
            new TestTextFormat(HEAD, FOOT),
            new SentenceStream(out)
        ).convert(
            new ByteArrayInputStream(input.getBytes(Charsets.UTF_8))
        );
        Assert.assertEquals(
            "not converted without end",
            Joiner.on(this.nline).join(HEAD, input, FOOT, ""),
            out.toString()
        );
    }
    /**
     * Can convert two sentences.
     * @throws Exception If fails.
     */
    @Test
    public void convertTwoSentences() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        new SentenceTransform<>(
            new TestTextFormat(HEAD, FOOT),
            new SentenceStream(out)
        ).convert(
            new ByteArrayInputStream(
                "one sent. second".getBytes(Charsets.UTF_8)
            )
        );
        Assert.assertEquals(
            "not converted sentences",
            Joiner.on(this.nline).join(HEAD, "one sent", "second", FOOT, ""),
            out.toString()
        );
    }
    /**
     * Can convert sentence with spaces.
     * @throws Exception If fails.
     */
    @Test
    public void convertSentenceWithSpaces() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        new SentenceTransform<>(
            new TestTextFormat(HEAD, FOOT),
            new SentenceStream(out)
        ).convert(
            new ByteArrayInputStream(
                " .. with ,,  spaces.. ".getBytes(Charsets.UTF_8)
            )
        );
        Assert.assertEquals(
            "not converted with spaces",
            Joiner.on(this.nline).join(HEAD, "with spaces", FOOT, ""),
            out.toString()
        );
    }
    /**
     * Can convert sentence with newline.
     * @throws Exception If fails.
     */
    @Test
    public void convertSentenceWithNewline() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        new SentenceTransform<>(
            new TestTextFormat(HEAD, FOOT),
            new SentenceStream(out)
        ).convert(
            new ByteArrayInputStream(
                "with\n\nnewline".getBytes(Charsets.UTF_8)
            )
        );
        Assert.assertEquals(
            "not converted with newline",
            Joiner.on(this.nline).join(HEAD, "with newline", FOOT, ""),
            out.toString()
        );
    }
    /**
     * Can convert sentence with special chars.
     * @throws Exception If fails.
     */
    @Test
    public void convertSentenceWithSpecChars() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        new SentenceTransform<>(
            new TestTextFormat(HEAD, FOOT),
            new SentenceStream(out)
        ).convert(
            new ByteArrayInputStream(
                "ściółka śpi\n ze źdźbła".getBytes(Charsets.UTF_8)
            )
        );
        Assert.assertEquals(
            "not converted with spec chars",
            Joiner.on(this.nline).join(HEAD, "ściółka śpi ze źdźbła", FOOT, ""),
            out.toString()
        );
    }
    class TestTextFormat implements TextFormat<String> {
        /**
         * Header.
         */
        private final transient String hdr;
        /**
         * Footer.
         */
        private final transient String ftr;
        /**
         * Class constructor.
         * @param head Format header.
         * @param foot Format footer.
         */
        public TestTextFormat(final String head, final String foot) {
            this.hdr = head;
            this.ftr = foot;
        }
        @Override
        public Iterable<String> header() {
            return Collections.singletonList(this.hdr);
        }
        @Override
        public Iterable<String> footer() {
            return Collections.singletonList(this.ftr);
        }
        @Override
        public Iterable<String> sentence(final List<String> words,
            final int num) {
            return Collections.singletonList(
                Joiner.on(" ").join(words)
            );
        }
    }
}
