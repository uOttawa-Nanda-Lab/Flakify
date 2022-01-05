@Test public void testLogName() throws Exception {
  Log log=new Log();
  log.setLogName("log-name");
  assertThat(log.getLogName(),equalTo("log-name"));
}
