@Test public void shouldNotFailWhenProcessingInvalidResource() throws IOException {
  initExecutor(createProcessorUsingMissingResource());
  final List<Resource> resources=createResources(Resource.create("/uri",ResourceType.JS));
  final String result=victim.processAndMerge(resources,true);
  Assert.assertEquals("",result);
}
