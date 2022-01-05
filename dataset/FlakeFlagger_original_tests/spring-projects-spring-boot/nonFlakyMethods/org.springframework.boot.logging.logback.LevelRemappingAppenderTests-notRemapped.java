@Test public void notRemapped() throws Exception {
  this.appender.append(mockLogEvent(Level.TRACE));
  verify(this.logger).callAppenders(this.logCaptor.capture());
  assertThat(this.logCaptor.getAllValues().get(0).getLevel(),equalTo(Level.TRACE));
}
