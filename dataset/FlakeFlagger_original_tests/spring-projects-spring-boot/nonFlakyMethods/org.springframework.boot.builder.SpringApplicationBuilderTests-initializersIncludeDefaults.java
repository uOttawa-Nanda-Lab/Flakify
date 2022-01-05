@Test public void initializersIncludeDefaults() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder(ExampleConfig.class).web(false).initializers(new ApplicationContextInitializer<ConfigurableApplicationContext>(){
    @Override public void initialize(    ConfigurableApplicationContext applicationContext){
    }
  }
);
  this.context=application.run();
  assertEquals(3,application.application().getInitializers().size());
}
