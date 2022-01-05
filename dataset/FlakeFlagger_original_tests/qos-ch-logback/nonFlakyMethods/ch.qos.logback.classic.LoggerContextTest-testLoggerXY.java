@Test public void testLoggerXY(){
  assertEquals(1,lc.getLoggerList().size());
  Logger xy=lc.getLogger("x.y");
  assertEquals(3,instanceCount());
  LoggerTestHelper.assertNameEquals(xy,"x.y");
  LoggerTestHelper.assertLevels(null,xy,Level.DEBUG);
  Logger x=lc.getLogger("x");
  assertEquals(3,instanceCount());
  Logger xy2=lc.getLogger("x.y");
  assertEquals(xy,xy2);
  Logger x2=lc.getLogger("x");
  assertEquals(x,x2);
  assertEquals(3,instanceCount());
}
