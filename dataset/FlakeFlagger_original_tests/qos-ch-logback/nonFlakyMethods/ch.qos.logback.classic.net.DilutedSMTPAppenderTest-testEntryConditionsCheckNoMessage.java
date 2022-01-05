@Test public void testEntryConditionsCheckNoMessage(){
  appender.setMessage(null);
  appender.checkEntryConditions();
  assertEquals(1,appender.getContext().getStatusManager().getCount());
}
