@Test public void testGetSummaryStatus3() throws Exception {
  Status status1=new Status(Status.Level.OK);
  Map<String,Status> statuses=new HashMap<String,Status>();
  statuses.put("status1",status1);
  Status status=StatusUtils.getSummaryStatus(statuses);
  assertThat(status.getLevel(),is(Status.Level.OK));
  assertThat(status.getMessage(),isEmptyOrNullString());
}
