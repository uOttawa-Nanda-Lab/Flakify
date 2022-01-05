@Test public void testGetSummaryStatus2() throws Exception {
  Status status1=new Status(Status.Level.WARN);
  Status status2=new Status(Status.Level.OK);
  Map<String,Status> statuses=new HashMap<String,Status>();
  statuses.put("status1",status1);
  statuses.put("status2",status2);
  Status status=StatusUtils.getSummaryStatus(statuses);
  assertThat(status.getLevel(),is(Status.Level.WARN));
  assertThat(status.getMessage(),containsString("status1"));
  assertThat(status.getMessage(),not(containsString("status2")));
}
