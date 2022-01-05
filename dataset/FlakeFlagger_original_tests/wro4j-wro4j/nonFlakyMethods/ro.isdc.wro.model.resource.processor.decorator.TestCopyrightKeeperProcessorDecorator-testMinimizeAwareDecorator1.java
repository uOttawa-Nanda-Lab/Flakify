@Test public void testMinimizeAwareDecorator1(){
  final ResourcePreProcessor decoratedProcessor=new JSMinProcessor();
  final ResourcePreProcessor processor=CopyrightKeeperProcessorDecorator.decorate(decoratedProcessor);
  Assert.assertTrue(new ProcessorDecorator(processor).isMinimize());
}
