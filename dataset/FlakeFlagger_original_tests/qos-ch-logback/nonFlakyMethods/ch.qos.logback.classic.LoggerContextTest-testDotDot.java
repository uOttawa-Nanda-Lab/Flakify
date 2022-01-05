@Test public void testDotDot(){
  Logger dotdot=lc.getLogger("..");
  assertEquals(4,lc.getLoggerList().size());
  LoggerTestHelper.assertNameEquals(dotdot,"..");
}
