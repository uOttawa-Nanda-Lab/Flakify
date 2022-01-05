@Test @SuppressWarnings("unchecked") public void testSerializeJavaBeanDescriptor() throws Exception {
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor();
  JavaBeanDescriptor result=JavaBeanSerializeUtil.serialize(descriptor);
  Assert.assertTrue(descriptor == result);
  Map map=new HashMap();
  map.put("first",descriptor);
  result=JavaBeanSerializeUtil.serialize(map);
  Assert.assertTrue(result.isMapType());
  Assert.assertEquals(HashMap.class.getName(),result.getClassName());
  Assert.assertEquals(map.size(),result.propertySize());
  Object object=result.iterator().next().getValue();
  Assert.assertTrue(object instanceof JavaBeanDescriptor);
  JavaBeanDescriptor actual=(JavaBeanDescriptor)object;
  Assert.assertEquals(map.get("first"),actual);
}
