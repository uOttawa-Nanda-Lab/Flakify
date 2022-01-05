@Test public void testLogThread() throws Exception {
  Log log=new Log();
  log.setLogThread("log-thread");
  assertThat(log.getLogThread(),equalTo("log-thread"));
}
