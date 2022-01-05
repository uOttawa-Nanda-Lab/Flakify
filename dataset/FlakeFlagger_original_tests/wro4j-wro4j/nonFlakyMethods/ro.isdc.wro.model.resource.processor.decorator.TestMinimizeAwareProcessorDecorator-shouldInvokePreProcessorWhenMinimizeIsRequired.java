@Test public void shouldInvokePreProcessorWhenMinimizeIsRequired() throws Exception {
  victim=new MinimizeAwareProcessorDecorator(mockPreProcessor,true);
  initVictim();
  victim.process(mockReader,mockWriter);
  Mockito.verify(mockPreProcessor,Mockito.atLeastOnce()).process(Mockito.any(Resource.class),Mockito.any(Reader.class),Mockito.any(Writer.class));
}
