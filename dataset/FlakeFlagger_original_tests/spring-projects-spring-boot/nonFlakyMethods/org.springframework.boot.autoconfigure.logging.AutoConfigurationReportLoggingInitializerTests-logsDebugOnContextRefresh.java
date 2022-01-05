@Test public void logsDebugOnContextRefresh(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
  this.initializer.initialize(context);
  context.register(Config.class);
  context.refresh();
  this.initializer.onApplicationEvent(new ContextRefreshedEvent(context));
  assertThat(this.debugLog.size(),not(equalTo(0)));
}
