@Test public void testTriggeringPolicy(){
  appender.setEvaluator(null);
  appender.checkEntryConditions();
  assertEquals(1,appender.getContext().getStatusManager().getCount());
}
