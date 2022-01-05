@Test public void testGetGenericClass(){
  Assert.assertTrue(TypeVariable.class.isAssignableFrom(ClassUtils.getGenericClass(GenericClass.class)));
  Assert.assertTrue(String.class.isAssignableFrom(ClassUtils.getGenericClass(GenericClass0.class)));
  Assert.assertTrue(Collection.class.isAssignableFrom(ClassUtils.getGenericClass(GenericClass1.class)));
  Assert.assertTrue(TypeVariable.class.isAssignableFrom(ClassUtils.getGenericClass(GenericClass2.class)));
  Assert.assertTrue(GenericArrayType.class.isAssignableFrom(ClassUtils.getGenericClass(GenericClass3.class)));
}
