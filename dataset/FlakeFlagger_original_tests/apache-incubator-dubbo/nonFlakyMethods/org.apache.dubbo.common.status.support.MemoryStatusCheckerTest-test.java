@Test public void test() throws Exception {
  MemoryStatusChecker statusChecker=new MemoryStatusChecker();
  Status status=statusChecker.check();
  assertThat(status.getLevel(),anyOf(is(OK),is(WARN)));
  logger.info("memory status level: " + status.getLevel());
  logger.info("memory status message: " + status.getMessage());
}
