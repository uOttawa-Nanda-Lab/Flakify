@Test public void testModuleInfo(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TestBean.class);
  ReferenceAnnotationBeanPostProcessor beanPostProcessor=context.getBean(BEAN_NAME,ReferenceAnnotationBeanPostProcessor.class);
  Map<InjectionMetadata.InjectedElement,ReferenceBean<?>> referenceBeanMap=beanPostProcessor.getInjectedMethodReferenceBeanMap();
  for (  Map.Entry<InjectionMetadata.InjectedElement,ReferenceBean<?>> entry : referenceBeanMap.entrySet()) {
    ReferenceBean<?> referenceBean=entry.getValue();
    assertThat(referenceBean.getModule().getName(),is("defaultModule"));
    assertThat(referenceBean.getMonitor(),not(nullValue()));
  }
}
