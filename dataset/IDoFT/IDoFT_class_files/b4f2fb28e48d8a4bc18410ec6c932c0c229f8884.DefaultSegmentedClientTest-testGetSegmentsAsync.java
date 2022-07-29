/*
 * jndn-utils
 * Copyright (c) 2015, Intel Corporation.
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms and conditions of the GNU Lesser General Public License,
 * version 3, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 */
package com.intel.jndn.utils.client.impl;

import com.intel.jndn.mock.MeasurableFace;
import com.intel.jndn.mock.MockFace;
import com.intel.jndn.mock.MockForwarder;
import com.intel.jndn.utils.TestHelper;
import com.intel.jndn.utils.TestHelper.TestCounter;
import com.intel.jndn.utils.client.DataStream;
import net.named_data.jndn.Data;
import net.named_data.jndn.Face;
import net.named_data.jndn.Interest;
import net.named_data.jndn.Name;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test DefaultSegmentedClient
 *
 * @author Andrew Brown, andrew.brown@intel.com
 */
public class DefaultSegmentedClientTest {

  private final DefaultSegmentedClient instance = new DefaultSegmentedClient();

  @Test
  public void testGetSegmentsAsync() throws Exception {
    MockFace face = new MockFace();
    Name name = new Name("/test/segmented/client");
    Interest interest = new Interest(name);
    DataStream stream = instance.getSegmentsAsync(face, interest);

    TestCounter counter = new TestCounter();
    stream.observe((i, d) -> counter.count++);

    for (Data segment : TestHelper.buildSegments(name, 0, 5)) {
      stream.onData(interest, segment);
    }

    assertEquals(5, counter.count);
    assertEquals("01234", stream.assemble().getContent().toString());
  }

  @Test
  public void testReplacingFinalComponents() throws Exception {
    long segmentNumber = 99;

    Name name1 = new Name("/a/b/c");
    Interest interest1 = new Interest(name1);
    Interest copied1 = instance.replaceFinalComponent(interest1, segmentNumber, (byte) 0x00);
    assertEquals(segmentNumber, copied1.getName().get(-1).toSegment());

    Name name2 = new Name("/a/b/c").appendSegment(17);
    Interest interest2 = new Interest(name2);
    Interest copied2 = instance.replaceFinalComponent(interest2, segmentNumber, (byte) 0x00);
    assertEquals(segmentNumber, copied2.getName().get(-1).toSegment());

    assertEquals(copied1.toUri(), copied2.toUri());
  }

  @Test
  public void verifyThatSegmentsAreRetrievedOnlyOnce() throws Exception {
    MockForwarder forwarder = new MockForwarder();
    Face face = forwarder.connect();
    Name name = new Name("/test/segmented/client");
    Interest interest = new Interest(name);
    DataStream stream = instance.getSegmentsAsync(face, interest);

    TestCounter counter = new TestCounter();
    stream.observe((i, d) -> counter.count++);

    for (Data segment : TestHelper.buildSegments(name, 0, 5)) {
      face.putData(segment);
      face.processEvents();
    }

    assertEquals(5, counter.count);
    assertEquals(5, ((MeasurableFace) face).sentInterests().size());
    assertEquals("01234", stream.assemble().getContent().toString());
  }
}
