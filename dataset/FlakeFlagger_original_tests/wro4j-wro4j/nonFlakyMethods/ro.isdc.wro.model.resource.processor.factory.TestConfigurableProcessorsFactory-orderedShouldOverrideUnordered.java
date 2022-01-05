@Test public void orderedShouldOverrideUnordered(){
  final Properties props=new Properties();
  props.setProperty(ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS,OrderedProcessorProvider.ALIAS);
  victim.setProperties(props);
  assertSame(victim.getPreProcessors().iterator().next(),OrderedProcessorProvider.CUSTOM);
}
