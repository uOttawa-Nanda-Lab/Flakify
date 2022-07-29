package org.wildfly.clustering.marshalling.jboss;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HashableMarshalledValueFactoryTestCase {
/** 
 * Test method for  {@link org.jboss.ha.framework.server.SimpleMarshalledValue#hashCode()}.
 */
@Test public void testHashCode() throws Exception {
  UUID uuid=UUID.randomUUID();
  SimpleMarshalledValue<UUID> mv=this.factory.createMarshalledValue(uuid);
  assertEquals(uuid.hashCode(),mv.hashCode());
  SimpleMarshalledValue<UUID> copy=replicate(mv);
  this.validateHashCode(uuid,copy);
  mv=this.factory.createMarshalledValue(null);
  assertEquals(0,mv.hashCode());
}

}