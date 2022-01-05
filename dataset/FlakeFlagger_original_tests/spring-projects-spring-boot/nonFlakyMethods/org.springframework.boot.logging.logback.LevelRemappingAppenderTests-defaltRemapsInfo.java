@Test public void defaltRemapsInfo() throws Exception {
  this.appender.append(mockLogEvent(Level.INFO));
  verify(this.logger).callAppenders(this.logCaptor.capture());
  assertThat(this.logCaptor.getValue().getLevel(),equalTo(Level.DEBUG));
}
