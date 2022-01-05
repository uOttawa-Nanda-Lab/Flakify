@Test(expected=JobEventListenerConfigurationException.class) public void assertCreateJobEventListenerFailure() throws JobEventListenerConfigurationException {
  new JobEventRdbConfiguration(new BasicDataSource()).createJobEventListener();
}
