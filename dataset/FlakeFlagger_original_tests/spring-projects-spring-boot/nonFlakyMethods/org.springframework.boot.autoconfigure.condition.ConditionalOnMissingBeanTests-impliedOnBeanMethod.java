@Test public void impliedOnBeanMethod() throws Exception {
  this.context.register(ExampleBeanConfiguration.class,ImpliedOnBeanMethod.class);
  this.context.refresh();
  assertThat(this.context.getBeansOfType(ExampleBean.class).size(),equalTo(1));
}
