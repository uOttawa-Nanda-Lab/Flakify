@Test public void testGetBeansIfAbsent(){
  applicationContext.refresh();
  List<TestBean> testBeans=BeanFactoryUtils.getBeans(applicationContext,new String[]{"testBean"},TestBean.class);
  Assert.assertTrue(testBeans.isEmpty());
}
