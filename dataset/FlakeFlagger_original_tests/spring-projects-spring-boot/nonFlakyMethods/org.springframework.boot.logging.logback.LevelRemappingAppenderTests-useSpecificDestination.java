@Test public void useSpecificDestination() throws Exception {
  this.appender.setDestinationLogger("org.mine");
  this.appender.append(mockLogEvent(Level.INFO));
  verify(this.appender).getLogger("org.mine");
}
