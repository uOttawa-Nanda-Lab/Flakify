/** 
 * Test on  {@link ReferenceAnnotationBeanPostProcessor#getReferenceBeans()}
 */
@Test public void testGetReferenceBeans(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TestBean.class);
  ReferenceAnnotationBeanPostProcessor beanPostProcessor=context.getBean(BEAN_NAME,ReferenceAnnotationBeanPostProcessor.class);
  Collection<ReferenceBean<?>> referenceBeans=beanPostProcessor.getReferenceBeans();
  Assert.assertEquals(1,referenceBeans.size());
  ReferenceBean<?> referenceBean=referenceBeans.iterator().next();
  TestBean testBean=context.getBean(TestBean.class);
  Assert.assertEquals(referenceBean.get(),testBean.getDemoServiceFromAncestor());
  Assert.assertEquals(referenceBean.get(),testBean.getDemoServiceFromParent());
  Assert.assertEquals(referenceBean.get(),testBean.getDemoService());
}
