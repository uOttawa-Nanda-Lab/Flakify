@Test public void testGetOptionalBeanIfAbsent(){
  applicationContext.refresh();
  TestBean testBean=BeanFactoryUtils.getOptionalBean(applicationContext,"testBean",TestBean.class);
  Assert.assertNull(testBean);
}
