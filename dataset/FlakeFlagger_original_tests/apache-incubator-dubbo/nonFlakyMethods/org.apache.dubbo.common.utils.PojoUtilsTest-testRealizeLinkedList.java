@Test public void testRealizeLinkedList() throws Exception {
  LinkedList<Person> input=new LinkedList<Person>();
  Person person=new Person();
  person.setAge(37);
  input.add(person);
  Object obj=PojoUtils.generalize(input);
  assertTrue(obj instanceof List);
  assertTrue(input.get(0) instanceof Person);
  Object output=PojoUtils.realize(obj,LinkedList.class);
  assertTrue(output instanceof LinkedList);
}
