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

  @Test public void testTimeUUIDAsByteBuffer(){UUID expectedUuid=TimeUUIDUtils.getUniqueTimeUUIDinMillis();UUID actualUuid=TimeUUIDUtils.uuid(TimeUUIDUtils.asByteBuffer(expectedUuid));assertEquals(expectedUuid,actualUuid);}



}
