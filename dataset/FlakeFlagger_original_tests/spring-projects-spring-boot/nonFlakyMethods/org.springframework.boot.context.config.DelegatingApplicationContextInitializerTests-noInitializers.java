@Test public void noInitializers() throws Exception {
  StaticApplicationContext context=new StaticApplicationContext();
  this.initializer.initialize(context);
}
