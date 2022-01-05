@Test public void testProcessorsExecutionOrder(){
  final Properties props=createProperties(PARAM_PRE_PROCESSORS,JSMinProcessor.ALIAS + "," + CssImportPreProcessor.ALIAS+ ","+ CssVariablesProcessor.ALIAS);
  initFactory(mockFilterConfig,props);
  final Collection<ResourcePreProcessor> list=processorsFactory.getPreProcessors();
  Assert.assertFalse(list.isEmpty());
  final Iterator<ResourcePreProcessor> iterator=list.iterator();
  assertEquals(JSMinProcessor.class,getProcessor(iterator.next()).getClass());
  assertEquals(CssImportPreProcessor.class,getProcessor(iterator.next()).getClass());
  assertEquals(CssVariablesProcessor.class,getProcessor(iterator.next()).getClass());
}
