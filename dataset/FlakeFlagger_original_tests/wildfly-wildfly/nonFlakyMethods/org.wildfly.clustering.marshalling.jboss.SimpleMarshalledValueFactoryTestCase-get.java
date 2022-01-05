/** 
 * Test method for  {@link org.jboss.ha.framework.server.SimpleMarshalledValue#get()}.
 */
@Test public void get() throws Exception {
  UUID uuid=UUID.randomUUID();
  SimpleMarshalledValue<UUID> mv=this.factory.createMarshalledValue(uuid);
  assertNotNull(mv.peek());
  assertSame(uuid,mv.peek());
  assertSame(uuid,mv.get(this.context));
  SimpleMarshalledValue<UUID> copy=replicate(mv);
  assertNull(copy.peek());
  UUID uuid2=copy.get(this.context);
  assertNotSame(uuid,uuid2);
  assertEquals(uuid,uuid2);
  copy=replicate(copy);
  uuid2=copy.get(this.context);
  assertEquals(uuid,uuid2);
  mv=this.factory.createMarshalledValue(null);
  assertNull(mv.peek());
  assertNull(mv.getBytes());
  assertNull(mv.get(this.context));
}
