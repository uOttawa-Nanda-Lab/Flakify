@Test public void contextWithClassLoader() throws Exception {
  SpringApplicationBuilder application=new SpringApplicationBuilder(ExampleConfig.class).contextClass(SpyApplicationContext.class);
  ClassLoader classLoader=new URLClassLoader(new URL[0],getClass().getClassLoader());
  application.resourceLoader(new DefaultResourceLoader(classLoader));
  this.context=application.run();
  assertThat(((SpyApplicationContext)this.context).getClassLoader(),is(equalTo(classLoader)));
}
