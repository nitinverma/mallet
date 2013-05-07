/* Copyright (C) 2002 Univ. of Massachusetts Amherst, Computer Science Dept.
   This file is part of "MALLET" (MAchine Learning for LanguagE Toolkit).
   http://www.cs.umass.edu/~mccallum/mallet
   This software is provided under the terms of the Common Public License,
   version 1.0, as published by http://www.opensource.org.  For further
   information, see the file `LICENSE' included with this distribution. */


/**
 @author Andrew McCallum <a href="mailto:mccallum@cs.umass.edu">mccallum@cs.umass.edu</a>
 */

package cc.mallet.pipe.tests;

import cc.mallet.pipe.*;
import cc.mallet.pipe.iterator.FileIterator;
import cc.mallet.types.Instance;
import cc.mallet.types.InstanceList;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.util.Iterator;
import java.util.regex.Pattern;


public class TestRainbowStyle extends TestCase {
    private final static File dir = new File("foo/bar");

    public TestRainbowStyle(String name) {
        super(name);
    }

    protected void setUp() {
        dir.mkdirs();
    }

    protected void tearDown() {
        dir.delete();
    }

    public void testThree() {
        InstanceList il = new InstanceList(
                new SerialPipes(new Pipe[]{
                        new Target2Label(),
                        new CharSequence2TokenSequence(),
                        new TokenSequenceLowercase(),
                        new TokenSequenceRemoveStopwords(),
                        new TokenSequence2FeatureSequence(),
                        new FeatureSequence2FeatureVector()
                }));
        Iterator<Instance> pi = new FileIterator(dir, null, Pattern.compile("^([^/]*)/"));
        il.addThruPipe(pi);
    }

    public static Test suite() {
        return new TestSuite(TestRainbowStyle.class);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

}
