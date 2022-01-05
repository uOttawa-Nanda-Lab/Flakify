@Test public void testLogMessage() throws Exception {
  Log log=new Log();
  log.setLogMessage("log-message");
  assertThat(log.getLogMessage(),equalTo("log-message"));
}
