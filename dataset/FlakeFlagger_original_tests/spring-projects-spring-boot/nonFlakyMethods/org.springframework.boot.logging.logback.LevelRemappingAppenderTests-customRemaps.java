@Test public void customRemaps() throws Exception {
  this.appender.setRemapLevels("DEBUG->TRACE,ERROR->WARN");
  this.appender.append(mockLogEvent(Level.DEBUG));
  this.appender.append(mockLogEvent(Level.ERROR));
  verify(this.logger,times(2)).callAppenders(this.logCaptor.capture());
  assertThat(this.logCaptor.getAllValues().get(0).getLevel(),equalTo(Level.TRACE));
  assertThat(this.logCaptor.getAllValues().get(1).getLevel(),equalTo(Level.WARN));
}
