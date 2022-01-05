@Test public void shouldInvokeMinimizePreProcessorWhenMinimizeIsRequired() throws Exception {
  final ResourcePreProcessor processor=Mockito.spy(new JSMinProcessor());
  victim=new MinimizeAwareProcessorDecorator(processor,true);
  initVictim();
  victim.process(mockReader,mockWriter);
  Mockito.verify(processor,Mockito.atLeastOnce()).process(Mockito.any(Resource.class),Mockito.any(Reader.class),Mockito.any(Writer.class));
}
