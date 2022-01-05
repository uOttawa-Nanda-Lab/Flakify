@Test public void shouldNotExceedNumber() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentTime(new Date(0));
  DurationHelper dh=new DurationHelper("R2/PT10S",testingClock);
  testingClock.setCurrentTime(new Date(15000));
  assertEquals(20000,dh.getDateAfter().getTime());
  testingClock.setCurrentTime(new Date(30000));
  assertEquals(30000,dh.getDateAfter().getTime());
}
