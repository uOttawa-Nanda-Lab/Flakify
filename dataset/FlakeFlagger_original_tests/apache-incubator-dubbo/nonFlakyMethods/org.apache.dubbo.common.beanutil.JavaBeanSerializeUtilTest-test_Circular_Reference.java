@Test public void test_Circular_Reference() throws Exception {
  PojoUtilsTest.Parent parent=new PojoUtilsTest.Parent();
  parent.setAge(Integer.MAX_VALUE);
  parent.setEmail("a@b");
  parent.setName("zhangsan");
  PojoUtilsTest.Child child=new PojoUtilsTest.Child();
  child.setAge(100);
  child.setName("lisi");
  child.setParent(parent);
  parent.setChild(child);
  JavaBeanDescriptor descriptor=JavaBeanSerializeUtil.serialize(parent,JavaBeanAccessor.METHOD);
  Assert.assertTrue(descriptor.isBeanType());
  assertEqualsPrimitive(parent.getAge(),descriptor.getProperty("age"));
  assertEqualsPrimitive(parent.getName(),descriptor.getProperty("name"));
  assertEqualsPrimitive(parent.getEmail(),descriptor.getProperty("email"));
  JavaBeanDescriptor childDescriptor=(JavaBeanDescriptor)descriptor.getProperty("child");
  Assert.assertTrue(descriptor == childDescriptor.getProperty("parent"));
  assertEqualsPrimitive(child.getName(),childDescriptor.getProperty("name"));
  assertEqualsPrimitive(child.getAge(),childDescriptor.getProperty("age"));
}
