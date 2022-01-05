@Test public void test_simpleCollection() throws Exception {
  Type gtype=getType("returnListPersonMethod");
  List<Person> list=new ArrayList<Person>();
  list.add(new Person());
{
    Person person=new Person();
    person.setName("xxxx");
    list.add(person);
  }
  assertObject(list,gtype);
}
