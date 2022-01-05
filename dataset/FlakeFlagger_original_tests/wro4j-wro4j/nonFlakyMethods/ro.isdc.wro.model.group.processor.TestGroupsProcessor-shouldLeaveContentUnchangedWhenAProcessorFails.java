@Test public void shouldLeaveContentUnchangedWhenAProcessorFails(){
  final CacheKey key=new CacheKey(groupName,ResourceType.JS,true);
  final Group group=new Group(groupName).addResource(Resource.create("1.js")).addResource(Resource.create("2.js"));
  final WroModelFactory modelFactory=WroTestUtils.simpleModelFactory(new WroModel().addGroup(group));
  final UriLocatorFactory locatorFactory=new SimpleUriLocatorFactory().addLocator(new UriLocator(){
    public boolean accept(    final String uri){
      return true;
    }
    public InputStream locate(    final String uri) throws IOException {
      return new ByteArrayInputStream(uri.getBytes());
    }
  }
);
  final ResourcePreProcessor failingPreProcessor=new ResourcePreProcessor(){
    public void process(    final Resource resource,    final Reader reader,    final Writer writer) throws IOException {
      throw new IOException("BOOM!");
    }
  }
;
  final ProcessorsFactory processorsFactory=new SimpleProcessorsFactory().addPreProcessor(failingPreProcessor).addPostProcessor(new ProcessorDecorator(failingPreProcessor));
  final BaseWroManagerFactory managerFactory=new BaseWroManagerFactory().setModelFactory(modelFactory).setUriLocatorFactory(locatorFactory);
  managerFactory.setProcessorsFactory(processorsFactory);
  final WroConfiguration config=new WroConfiguration();
  config.setIgnoreFailingProcessor(true);
  initVictim(config,managerFactory);
  final String actual=victim.process(key);
  Assert.assertEquals("1.js2.js",actual);
}
