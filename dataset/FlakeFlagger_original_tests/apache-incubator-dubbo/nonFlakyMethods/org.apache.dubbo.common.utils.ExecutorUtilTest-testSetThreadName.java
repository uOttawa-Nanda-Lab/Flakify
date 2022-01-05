@Test public void testSetThreadName() throws Exception {
  URL url=new URL("dubbo","localhost",1234).addParameter(Constants.THREAD_NAME_KEY,"custom-thread");
  url=ExecutorUtil.setThreadName(url,"default-name");
  assertThat(url.getParameter(Constants.THREAD_NAME_KEY),equalTo("custom-thread-localhost:1234"));
}
