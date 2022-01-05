@Test public void testLoggerX(){
  Logger x=lc.getLogger("x");
  assertNotNull(x);
  assertEquals("x",x.getName());
  assertNull(x.getLevel());
  assertEquals(Level.DEBUG,x.getEffectiveLevel());
}
