@Test public void testMinimizeAwareDecorator2(){
  final ResourcePreProcessor decoratedProcessor=new CssUrlRewritingProcessor();
  final ResourcePreProcessor processor=CopyrightKeeperProcessorDecorator.decorate(decoratedProcessor);
  Assert.assertFalse(new ProcessorDecorator(processor).isMinimize());
}
