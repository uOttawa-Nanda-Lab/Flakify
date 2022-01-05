@Test public void testIsAccessByMethod(){
  Assert.assertTrue(JavaBeanAccessor.isAccessByMethod(JavaBeanAccessor.METHOD));
  Assert.assertTrue(JavaBeanAccessor.isAccessByMethod(JavaBeanAccessor.ALL));
  Assert.assertFalse(JavaBeanAccessor.isAccessByMethod(JavaBeanAccessor.FIELD));
}
