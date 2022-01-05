@Test public void resetTest(){
  Logger root=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  Logger a=lc.getLogger("a");
  Logger ab=lc.getLogger("a.b");
  ab.setLevel(Level.WARN);
  root.setLevel(Level.INFO);
  lc.reset();
  assertEquals(Level.DEBUG,root.getEffectiveLevel());
  assertTrue(root.isDebugEnabled());
  assertEquals(Level.DEBUG,a.getEffectiveLevel());
  assertEquals(Level.DEBUG,ab.getEffectiveLevel());
  assertEquals(Level.DEBUG,root.getLevel());
  assertNull(a.getLevel());
  assertNull(ab.getLevel());
}
