@Test public void autoConfigurationBacksOffInNonWebApplicationContexts(){
  this.context=new AnnotationConfigApplicationContext();
  ((AnnotationConfigApplicationContext)this.context).register(SpringDataWebAutoConfiguration.class);
  this.context.refresh();
  Map<String,PageableHandlerMethodArgumentResolver> beans=this.context.getBeansOfType(PageableHandlerMethodArgumentResolver.class);
  assertThat(beans.size(),is(equalTo(0)));
}
