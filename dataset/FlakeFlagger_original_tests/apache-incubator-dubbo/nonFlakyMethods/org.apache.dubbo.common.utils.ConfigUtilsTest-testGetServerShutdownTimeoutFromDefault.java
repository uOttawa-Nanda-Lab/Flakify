@Test public void testGetServerShutdownTimeoutFromDefault() throws Exception {
  System.clearProperty(Constants.SHUTDOWN_WAIT_KEY);
  System.clearProperty(Constants.SHUTDOWN_WAIT_SECONDS_KEY);
  assertThat(ConfigUtils.getServerShutdownTimeout(),equalTo(Constants.DEFAULT_SERVER_SHUTDOWN_TIMEOUT));
}
