@Test public void testDeserialize_Array() throws Exception {
  final int len=10;
  JavaBeanDescriptor descriptor=new JavaBeanDescriptor(int.class.getName(),JavaBeanDescriptor.TYPE_ARRAY);
  for (int i=0; i < len; i++) {
    descriptor.setProperty(i,i);
  }
  Object obj=JavaBeanSerializeUtil.deserialize(descriptor);
  Assert.assertTrue(obj.getClass().isArray());
  Assert.assertTrue(int.class == obj.getClass().getComponentType());
  for (int i=0; i < len; i++) {
    Assert.assertEquals(i,Array.get(obj,i));
  }
  descriptor=new JavaBeanDescriptor(int[].class.getName(),JavaBeanDescriptor.TYPE_ARRAY);
  for (int i=0; i < len; i++) {
    JavaBeanDescriptor innerItem=new JavaBeanDescriptor(int.class.getName(),JavaBeanDescriptor.TYPE_ARRAY);
    for (int j=0; j < len; j++) {
      innerItem.setProperty(j,j);
    }
    descriptor.setProperty(i,innerItem);
  }
  obj=JavaBeanSerializeUtil.deserialize(descriptor);
  Assert.assertTrue(obj.getClass().isArray());
  Assert.assertEquals(int[].class,obj.getClass().getComponentType());
  for (int i=0; i < len; i++) {
    Object innerItem=Array.get(obj,i);
    Assert.assertTrue(innerItem.getClass().isArray());
    Assert.assertEquals(int.class,innerItem.getClass().getComponentType());
    for (int j=0; j < len; j++) {
      Assert.assertEquals(j,Array.get(innerItem,j));
    }
  }
  descriptor=new JavaBeanDescriptor(BigPerson[].class.getName(),JavaBeanDescriptor.TYPE_ARRAY);
  JavaBeanDescriptor innerDescriptor=new JavaBeanDescriptor(BigPerson.class.getName(),JavaBeanDescriptor.TYPE_ARRAY);
  innerDescriptor.setProperty(0,JavaBeanSerializeUtil.serialize(createBigPerson(),JavaBeanAccessor.METHOD));
  descriptor.setProperty(0,innerDescriptor);
  obj=JavaBeanSerializeUtil.deserialize(descriptor);
  Assert.assertTrue(obj.getClass().isArray());
  Assert.assertEquals(BigPerson[].class,obj.getClass().getComponentType());
  Assert.assertEquals(1,Array.getLength(obj));
  obj=Array.get(obj,0);
  Assert.assertTrue(obj.getClass().isArray());
  Assert.assertEquals(BigPerson.class,obj.getClass().getComponentType());
  Assert.assertEquals(1,Array.getLength(obj));
  Assert.assertEquals(createBigPerson(),Array.get(obj,0));
}
