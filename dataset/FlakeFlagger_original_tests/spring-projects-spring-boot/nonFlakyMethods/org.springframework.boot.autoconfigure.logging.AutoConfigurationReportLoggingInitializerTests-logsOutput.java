@Test public void logsOutput() throws Exception {
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
  this.initializer.initialize(context);
  context.register(Config.class);
  context.refresh();
  this.initializer.onApplicationEvent(new ContextRefreshedEvent(context));
  for (  String message : this.debugLog) {
    System.out.println(message);
  }
  String l=this.debugLog.get(0);
  assertThat(l,containsString("not a web application (OnWebApplicationCondition)"));
}
