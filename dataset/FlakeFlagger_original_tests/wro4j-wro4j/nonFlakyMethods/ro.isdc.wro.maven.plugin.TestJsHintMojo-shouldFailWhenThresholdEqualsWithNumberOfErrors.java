@Test(expected=MojoExecutionException.class) public void shouldFailWhenThresholdEqualsWithNumberOfErrors() throws Exception {
  final JsHintMojo jsHintMojo=(JsHintMojo)getMojo();
  jsHintMojo.setFailThreshold(5);
  executeResourcesWithErrors();
}
