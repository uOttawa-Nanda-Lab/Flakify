@Test public void shouldNotExceedNumberNegative() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentTime(parse("19700101-00:00:00"));
  DurationHelper dh=new DurationHelper("R2/PT10S/1970-01-01T00:00:50",testingClock);
  testingClock.setCurrentTime(parse("19700101-00:00:20"));
  assertEquals(parse("19700101-00:00:30"),dh.getDateAfter());
  testingClock.setCurrentTime(parse("19700101-00:00:35"));
  assertEquals(parse("19700101-00:00:35"),dh.getDateAfter());
}
