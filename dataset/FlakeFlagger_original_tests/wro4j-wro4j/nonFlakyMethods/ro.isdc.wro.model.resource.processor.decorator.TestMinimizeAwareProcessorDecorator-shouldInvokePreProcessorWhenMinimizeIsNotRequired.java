@Test public void shouldInvokePreProcessorWhenMinimizeIsNotRequired() throws Exception {
  victim=new MinimizeAwareProcessorDecorator(mockPreProcessor,false);
  initVictim();
  victim.process(mockReader,mockWriter);
  Mockito.verify(mockPreProcessor,Mockito.atLeastOnce()).process(Mockito.any(Resource.class),Mockito.any(Reader.class),Mockito.any(Writer.class));
}
