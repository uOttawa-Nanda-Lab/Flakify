@Test public void exceptionTranslationPostProcessorBeanIsCreated(){
  this.context=new AnnotationConfigApplicationContext(PersistenceExceptionTranslationAutoConfiguration.class);
  Map<String,PersistenceExceptionTranslationPostProcessor> beans=this.context.getBeansOfType(PersistenceExceptionTranslationPostProcessor.class);
  assertThat(beans.size(),is(equalTo(1)));
}
