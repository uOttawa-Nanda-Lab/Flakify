@Test(expected=BeanCreationException.class) public void templateLocationDoesNotExist() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.thymeleaf.prefix:classpath:/no-such-directory/");
  this.context.register(ThymeleafAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
}
