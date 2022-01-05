@Test public void testGetInjectedFieldReferenceBeanMap(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TestBean.class);
  ReferenceAnnotationBeanPostProcessor beanPostProcessor=context.getBean(BEAN_NAME,ReferenceAnnotationBeanPostProcessor.class);
  Map<InjectionMetadata.InjectedElement,ReferenceBean<?>> referenceBeanMap=beanPostProcessor.getInjectedFieldReferenceBeanMap();
  Assert.assertEquals(1,referenceBeanMap.size());
  for (  Map.Entry<InjectionMetadata.InjectedElement,ReferenceBean<?>> entry : referenceBeanMap.entrySet()) {
    InjectionMetadata.InjectedElement injectedElement=entry.getKey();
    Assert.assertEquals("org.apache.dubbo.config.spring.beans.factory.annotation.ReferenceAnnotationBeanPostProcessor$ReferenceFieldElement",injectedElement.getClass().getName());
    ReferenceBean<?> referenceBean=entry.getValue();
    Assert.assertEquals("1.2",referenceBean.getVersion());
    Assert.assertEquals("dubbo://127.0.0.1:12345",referenceBean.getUrl());
  }
}
