@Test public void testGetSummaryStatus1() throws Exception {
  Status status1=new Status(Status.Level.ERROR);
  Status status2=new Status(Status.Level.WARN);
  Status status3=new Status(Status.Level.OK);
  Map<String,Status> statuses=new HashMap<String,Status>();
  statuses.put("status1",status1);
  statuses.put("status2",status2);
  statuses.put("status3",status3);
  Status status=StatusUtils.getSummaryStatus(statuses);
  assertThat(status.getLevel(),is(Status.Level.ERROR));
  assertThat(status.getMessage(),containsString("status1"));
  assertThat(status.getMessage(),containsString("status2"));
  assertThat(status.getMessage(),not(containsString("status3")));
}
