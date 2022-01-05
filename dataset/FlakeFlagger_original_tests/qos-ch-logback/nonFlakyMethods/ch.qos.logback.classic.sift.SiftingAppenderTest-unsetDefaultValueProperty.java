@Test public void unsetDefaultValueProperty() throws JoranException {
  configure(SIFT_FOLDER_PREFIX + "unsetDefaultValueProperty.xml");
  logger.debug("hello");
  SiftingAppender sa=(SiftingAppender)root.getAppender("SIFT");
  assertFalse(sa.isStarted());
}
