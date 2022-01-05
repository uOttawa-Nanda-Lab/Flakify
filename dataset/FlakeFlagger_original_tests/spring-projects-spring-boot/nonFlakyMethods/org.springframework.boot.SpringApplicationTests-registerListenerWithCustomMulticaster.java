@Test public void registerListenerWithCustomMulticaster() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class,Multicaster.class);
  application.setApplicationContextClass(SpyApplicationContext.class);
  final LinkedHashSet<ApplicationEvent> events=new LinkedHashSet<ApplicationEvent>();
  application.addListeners(new ApplicationListener<ApplicationEvent>(){
    @Override public void onApplicationEvent(    ApplicationEvent event){
      events.add(event);
    }
  }
);
  this.context=application.run();
  assertThat(events,hasItem(isA(ApplicationPreparedEvent.class)));
  assertThat(events,hasItem(isA(ContextRefreshedEvent.class)));
}
