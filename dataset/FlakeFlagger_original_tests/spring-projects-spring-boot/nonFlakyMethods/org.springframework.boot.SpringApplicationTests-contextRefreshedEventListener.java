@Test public void contextRefreshedEventListener() throws Exception {
  SpringApplication application=new SpringApplication(ExampleConfig.class);
  application.setWebEnvironment(false);
  final AtomicReference<ApplicationContext> reference=new AtomicReference<ApplicationContext>();
class InitalizerListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override public void onApplicationEvent(    ContextRefreshedEvent event){
      reference.set(event.getApplicationContext());
    }
  }
  application.setListeners(Arrays.asList(new InitalizerListener()));
  this.context=application.run("--foo=bar");
  assertThat(this.context,sameInstance(reference.get()));
  assertThat(getEnvironment().getProperty("foo"),equalTo("bar"));
}
