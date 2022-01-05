@Test public void shouldSupportCorrectResourceTypes(){
  WroTestUtils.assertProcessorSupportResourceTypes(new RhinoTypeScriptProcessor(),ResourceType.JS);
}
