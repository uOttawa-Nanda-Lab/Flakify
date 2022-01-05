@Test public void templateLocationEmpty() throws Exception {
  new File("target/test-classes/templates/empty-directory").mkdir();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.thymeleaf.prefix:classpath:/templates/empty-directory/");
  this.context.register(ThymeleafAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
}
