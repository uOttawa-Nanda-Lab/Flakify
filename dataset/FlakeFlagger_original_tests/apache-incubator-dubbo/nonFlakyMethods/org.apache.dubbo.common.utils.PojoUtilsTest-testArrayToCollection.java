@Test public void testArrayToCollection() throws Exception {
  Person[] array=new Person[2];
  Person person1=new Person();
  person1.setName("person1");
  Person person2=new Person();
  person2.setName("person2");
  array[0]=person1;
  array[1]=person2;
  Object o=PojoUtils.realize(PojoUtils.generalize(array),LinkedList.class);
  assertTrue(o instanceof LinkedList);
  assertEquals(((List)o).get(0),person1);
  assertEquals(((List)o).get(1),person2);
}
