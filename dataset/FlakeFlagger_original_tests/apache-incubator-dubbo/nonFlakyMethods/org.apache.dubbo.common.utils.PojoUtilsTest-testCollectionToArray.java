@Test public void testCollectionToArray() throws Exception {
  Person person1=new Person();
  person1.setName("person1");
  Person person2=new Person();
  person2.setName("person2");
  List<Person> list=new LinkedList<Person>();
  list.add(person1);
  list.add(person2);
  Object o=PojoUtils.realize(PojoUtils.generalize(list),Person[].class);
  assertTrue(o instanceof Person[]);
  assertEquals(((Person[])o)[0],person1);
  assertEquals(((Person[])o)[1],person2);
}
