@Test public void shouldApplyProcessorOnlyOnResourcesWithExtensionJs() throws Exception {
  final ResourcePreProcessor decoratedProcessor=new JSMinProcessor();
  final ResourcePreProcessor processor=ExtensionsAwareProcessorDecorator.decorate(decoratedProcessor).addExtension("js");
  WroTestUtils.createInjector().inject(processor);
  final URL url=ResourcePreProcessor.class.getResource("extensionAware");
  final File testFolder=new File(url.getFile(),"test");
  final File expectedFolder=new File(url.getFile(),"expected");
  WroTestUtils.compareFromDifferentFolders(testFolder,expectedFolder,processor);
}
