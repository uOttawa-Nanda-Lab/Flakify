@Test public void overrideViewNames() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"spring.thymeleaf.viewNames:foo,bar");
  this.context.register(ThymeleafAutoConfiguration.class,PropertyPlaceholderAutoConfiguration.class);
  this.context.refresh();
  ThymeleafViewResolver views=this.context.getBean(ThymeleafViewResolver.class);
  assertArrayEquals(new String[]{"foo","bar"},views.getViewNames());
}
