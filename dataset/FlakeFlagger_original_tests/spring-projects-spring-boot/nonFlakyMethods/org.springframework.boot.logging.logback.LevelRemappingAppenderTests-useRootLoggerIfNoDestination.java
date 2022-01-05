@Test public void useRootLoggerIfNoDestination() throws Exception {
  this.appender.append(mockLogEvent(Level.INFO));
  verify(this.appender).getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
}
