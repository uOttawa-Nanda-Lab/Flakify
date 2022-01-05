@Test public void shouldSetCallback() throws Exception {
  final LifecycleCallback callback=Mockito.spy(new LifecycleCallbackSupport());
  victim=new BaseWroManagerFactory().setModelFactory(WroUtil.factoryFor(new WroModel()));
  final WroManager manager=victim.create();
  InjectorBuilder.create(victim).build().inject(manager);
  manager.registerCallback(new ObjectFactory<LifecycleCallback>(){
    public LifecycleCallback create(){
      return callback;
    }
  }
);
  manager.getModelFactory().create();
  Mockito.verify(callback).onBeforeModelCreated();
  Mockito.verify(callback).onAfterModelCreated();
}
