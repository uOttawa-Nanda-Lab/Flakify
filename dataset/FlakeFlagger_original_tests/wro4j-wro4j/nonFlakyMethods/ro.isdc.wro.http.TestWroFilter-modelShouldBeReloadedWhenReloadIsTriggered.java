/** 
 * Proves that the model reload has effect.
 */
@Test public void modelShouldBeReloadedWhenReloadIsTriggered() throws Exception {
  final WroManagerFactory wroManagerFactory=new BaseWroManagerFactory().setModelFactory(new WroModelFactory(){
    private boolean wasCreated=false;
    public WroModel create(){
      if (!wasCreated) {
        wasCreated=true;
        return new WroModel();
      }
      return new WroModel().addGroup(new Group("g1"));
    }
    public void destroy(){
    }
  }
);
  Context.set(Context.standaloneContext());
  final WroFilter filter=new WroFilter(){
    @Override protected WroManagerFactory newWroManagerFactory(){
      return wroManagerFactory;
    }
    @Override protected ObjectFactory<WroConfiguration> newWroConfigurationFactory(    final FilterConfig filterConfig){
      return new ObjectFactory<WroConfiguration>(){
        public WroConfiguration create(){
          return Context.get().getConfig();
        }
      }
;
    }
  }
;
  filter.init(mockFilterConfig);
  final WroModelFactory modelFactory=wroManagerFactory.create().getModelFactory();
  assertTrue(modelFactory.create().getGroups().isEmpty());
  Context.get().getConfig().reloadModel();
  assertEquals(1,modelFactory.create().getGroups().size());
}
