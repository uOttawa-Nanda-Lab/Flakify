@Test public void testGetLogFile(){
  LoggerFactory.setLoggerAdapter("slf4j");
  File file=LoggerFactory.getFile();
  assertThat(file,is(nullValue()));
}
