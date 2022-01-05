@Test public void testGetBeans(){
  applicationContext.register(TestBean.class,TestBean2.class);
  applicationContext.refresh();
  List<TestBean> testBeans=BeanFactoryUtils.getBeans(applicationContext,new String[]{"testBean"},TestBean.class);
  Assert.assertEquals(1,testBeans.size());
  Assert.assertEquals("Hello,World",testBeans.get(0).getName());
}
