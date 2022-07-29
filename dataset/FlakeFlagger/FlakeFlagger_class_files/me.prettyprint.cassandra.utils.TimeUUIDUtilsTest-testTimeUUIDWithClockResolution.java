package me.prettyprint.cassandra.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import me.prettyprint.cassandra.service.clock.MicrosecondsClockResolution;
import me.prettyprint.cassandra.service.clock.MicrosecondsSyncClockResolution;
import me.prettyprint.hector.api.ClockResolution;

import org.junit.Test;

import com.eaio.uuid.UUIDGen;

/**
 * Test @link {@link TimeUUIDUtils}
 *
 * @author Patricio Echague (pechague@gmail.com)
 *
 */
public class TimeUUIDUtilsTest {

  /**
 * This test must be placed FIRST. Please don't change the order.
 * @throws Exception
 */@Test public void testTimeUUIDWithClockResolution() throws Exception{ClockResolution clock=new MicrosecondsClockResolution();long time=clock.createClock();assertEquals(TimeUUIDUtils.getTimeUUID(time),java.util.UUID.fromString(new com.eaio.uuid.UUID(UUIDGen.createTime(time),UUIDGen.getClockSeqAndNode()).toString()));clock=new MicrosecondsSyncClockResolution();java.util.UUID first=TimeUUIDUtils.getTimeUUID(clock);java.util.UUID second=TimeUUIDUtils.getTimeUUID(clock);assertTrue(second.compareTo(first) > 0);}



}
