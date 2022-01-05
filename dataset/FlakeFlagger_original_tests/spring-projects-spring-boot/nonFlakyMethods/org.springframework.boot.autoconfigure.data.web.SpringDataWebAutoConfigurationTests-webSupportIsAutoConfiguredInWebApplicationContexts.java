@Test public void webSupportIsAutoConfiguredInWebApplicationContexts(){
  this.context=new AnnotationConfigWebApplicationContext();
  ((AnnotationConfigWebApplicationContext)this.context).register(SpringDataWebAutoConfiguration.class);
  this.context.refresh();
  ((AnnotationConfigWebApplicationContext)this.context).setServletContext(new MockServletContext());
  Map<String,PageableHandlerMethodArgumentResolver> beans=this.context.getBeansOfType(PageableHandlerMethodArgumentResolver.class);
  assertThat(beans.size(),is(equalTo(1)));
}
