@Test public void testSerialize_Array() throws Exception {
  int[] array={1,2,3,4,5,6,7,8,9};
  JavaBeanDescriptor descriptor=JavaBeanSerializeUtil.serialize(array,JavaBeanAccessor.METHOD);
  Assert.assertTrue(descriptor.isArrayType());
  Assert.assertEquals(int.class.getName(),descriptor.getClassName());
  for (int i=0; i < array.length; i++) {
    Assert.assertEquals(array[i],((JavaBeanDescriptor)descriptor.getProperty(i)).getPrimitiveProperty());
  }
  Integer[] integers=new Integer[]{1,2,3,4,null,null,null};
  descriptor=JavaBeanSerializeUtil.serialize(integers,JavaBeanAccessor.METHOD);
  Assert.assertTrue(descriptor.isArrayType());
  Assert.assertEquals(Integer.class.getName(),descriptor.getClassName());
  Assert.assertEquals(integers.length,descriptor.propertySize());
  for (int i=0; i < integers.length; i++) {
    if (integers[i] == null) {
      Assert.assertTrue(integers[i] == descriptor.getProperty(i));
    }
 else {
      Assert.assertEquals(integers[i],((JavaBeanDescriptor)descriptor.getProperty(i)).getPrimitiveProperty());
    }
  }
  int[][] second={{1,2},{3,4}};
  descriptor=JavaBeanSerializeUtil.serialize(second,JavaBeanAccessor.METHOD);
  Assert.assertTrue(descriptor.isArrayType());
  Assert.assertEquals(int[].class.getName(),descriptor.getClassName());
  for (int i=0; i < second.length; i++) {
    for (int j=0; j < second[i].length; j++) {
      JavaBeanDescriptor item=(((JavaBeanDescriptor)descriptor.getProperty(i)));
      Assert.assertTrue(item.isArrayType());
      Assert.assertEquals(int.class.getName(),item.getClassName());
      Assert.assertEquals(second[i][j],((JavaBeanDescriptor)item.getProperty(j)).getPrimitiveProperty());
    }
  }
  BigPerson[] persons=new BigPerson[]{createBigPerson(),createBigPerson()};
  descriptor=JavaBeanSerializeUtil.serialize(persons);
  Assert.assertTrue(descriptor.isArrayType());
  Assert.assertEquals(BigPerson.class.getName(),descriptor.getClassName());
  for (int i=0; i < persons.length; i++) {
    assertEqualsBigPerson(persons[i],descriptor.getProperty(i));
  }
}
