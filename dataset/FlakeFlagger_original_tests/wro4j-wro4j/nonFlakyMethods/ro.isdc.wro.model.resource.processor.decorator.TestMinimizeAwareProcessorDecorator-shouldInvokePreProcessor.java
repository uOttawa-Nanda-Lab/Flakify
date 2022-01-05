@Test public void shouldInvokePreProcessor() throws Exception {
  victim=new MinimizeAwareProcessorDecorator(mockPreProcessor);
  initVictim();
  victim.process(null,mockReader,mockWriter);
  Mockito.verify(mockPreProcessor,Mockito.atLeastOnce()).process(Mockito.any(Resource.class),Mockito.any(Reader.class),Mockito.any(Writer.class));
}
