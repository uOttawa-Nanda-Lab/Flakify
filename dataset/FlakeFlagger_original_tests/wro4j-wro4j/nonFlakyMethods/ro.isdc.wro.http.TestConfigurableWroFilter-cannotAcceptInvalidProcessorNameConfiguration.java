@Test(expected=WroRuntimeException.class) public void cannotAcceptInvalidProcessorNameConfiguration() throws Exception {
  genericProcessorNameConfigurationTest("INVALID_PROCESSOR_NAME");
}
