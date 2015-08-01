/**
 * Copyright (c) 2015, piotrkot
 */
package com.piokot.ncm;

import com.github.piotrkot.cli.CommandLineArgs;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for Help command triggering.
 *
 * @author Piotr Kotlicki (piotr.kotlicki@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class HELPCommandTest {
    /**
     * Can run if one help option given.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithOneOption() throws Exception {
        Assert.assertTrue(
            "not called with help option",
            new HELPCommand().canRun(new CommandLineArgs("-h"))
        );
    }
    /**
     * Can run if for two options given.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithTwoOptions() throws Exception {
        Assert.assertTrue(
            "not called with two options and no args",
            new HELPCommand().canRun(new CommandLineArgs("-d", "-s"))
        );
    }
    /**
     * Can run if no option given.
     *
     * @throws Exception If fails
     */
    @Test
    public void testCanRunWithNoOption() throws Exception {
        Assert.assertTrue(
            "not called with no option",
            new HELPCommand().canRun(new CommandLineArgs())
        );
    }
}
