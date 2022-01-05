@Test public void test() throws Exception {
  LoadStatusChecker statusChecker=new LoadStatusChecker();
  Status status=statusChecker.check();
  assertThat(status,notNullValue());
  logger.info("load status level: " + status.getLevel());
  logger.info("load status message: " + status.getMessage());
}
