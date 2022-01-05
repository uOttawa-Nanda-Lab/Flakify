@Test public void includeScanViaInputStreamSuppliedConfigFile() throws IOException, JoranException, InterruptedException {
  String configurationStr="<configuration scan=\"true\" scanPeriod=\"50 millisecond\"><include resource=\"asResource/inner1.xml\"/></configuration>";
  configure(new ByteArrayInputStream(configurationStr.getBytes("UTF-8")));
  ConfigurationWatchList configurationWatchList=ConfigurationWatchListUtil.getConfigurationWatchList(loggerContext);
  assertNull(configurationWatchList.getMainURL());
  ReconfigureOnChangeFilter reconfigureOnChangeFilter=(ReconfigureOnChangeFilter)getFirstTurboFilter();
  assertFalse(reconfigureOnChangeFilter.isStarted());
}
