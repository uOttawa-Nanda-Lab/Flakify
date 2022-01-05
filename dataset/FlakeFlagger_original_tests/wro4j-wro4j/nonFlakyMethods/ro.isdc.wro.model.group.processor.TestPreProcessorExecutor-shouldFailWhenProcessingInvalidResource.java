@Test(expected=IOException.class) public void shouldFailWhenProcessingInvalidResource() throws Exception {
  when(mockLocatorFactory.locate(Mockito.anyString())).thenThrow(IOException.class);
  Context.get().getConfig().setIgnoreMissingResources(false);
  shouldNotFailWhenProcessingInvalidResource();
}
