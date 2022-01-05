@Test public void testIsAccessByField(){
  Assert.assertTrue(JavaBeanAccessor.isAccessByField(JavaBeanAccessor.FIELD));
  Assert.assertTrue(JavaBeanAccessor.isAccessByField(JavaBeanAccessor.ALL));
  Assert.assertFalse(JavaBeanAccessor.isAccessByField(JavaBeanAccessor.METHOD));
}
