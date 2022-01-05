@Test public void test(){
  Map<String,HelloService> helloServicesMap=beanFactory.getBeansOfType(HelloService.class);
  Assert.assertEquals(2,helloServicesMap.size());
  Map<String,ServiceBean> serviceBeansMap=beanFactory.getBeansOfType(ServiceBean.class);
  Assert.assertEquals(3,serviceBeansMap.size());
  Map<String,ServiceAnnotationBeanPostProcessor> beanPostProcessorsMap=beanFactory.getBeansOfType(ServiceAnnotationBeanPostProcessor.class);
  Assert.assertEquals(4,beanPostProcessorsMap.size());
  Assert.assertTrue(beanPostProcessorsMap.containsKey("doubleServiceAnnotationBeanPostProcessor"));
  Assert.assertTrue(beanPostProcessorsMap.containsKey("emptyServiceAnnotationBeanPostProcessor"));
  Assert.assertTrue(beanPostProcessorsMap.containsKey("serviceAnnotationBeanPostProcessor"));
  Assert.assertTrue(beanPostProcessorsMap.containsKey("serviceAnnotationBeanPostProcessor2"));
}
