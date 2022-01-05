@Test public void shouldNotFailWhenThresholdIsGreaterThanNumberOfErrors() throws Exception {
  final JsHintMojo jsHintMojo=(JsHintMojo)getMojo();
  jsHintMojo.setFailThreshold(6);
  executeResourcesWithErrors();
}
