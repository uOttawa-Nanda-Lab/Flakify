@Test public void specificApplicationContextInitializer() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  final AtomicReference<ApplicationContext> reference=new AtomicReference<ApplicationContext>();
  application.setInitializers(Arrays.asList(new ApplicationContextInitializer<ConfigurableApplicationContext>(){
    @Override public void initialize(    ConfigurableApplicationContext context){
      reference.set(context);
    }
  }
));
  this.context=application.run("--foo=bar");
  assertThat(this.context,sameInstance(reference.get()));
  assertThat(getEnvironment().getProperty("foo"),equalTo("bar"));
}
