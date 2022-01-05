@Test public void shouldNotExceedNumberPeriods() throws Exception {
  Clock testingClock=new DefaultClockImpl();
  testingClock.setCurrentTime(parse("19700101-00:00:00"));
  DurationHelper dh=new DurationHelper("R2/1970-01-01T00:00:00/1970-01-01T00:00:10",testingClock);
  testingClock.setCurrentTime(parse("19700101-00:00:15"));
  assertEquals(parse("19700101-00:00:20"),dh.getDateAfter());
  testingClock.setCurrentTime(parse("19700101-00:00:30"));
  assertEquals(parse("19700101-00:00:30"),dh.getDateAfter());
}
