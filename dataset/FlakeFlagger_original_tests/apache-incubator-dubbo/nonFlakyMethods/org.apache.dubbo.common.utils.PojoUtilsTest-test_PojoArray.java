@Test public void test_PojoArray() throws Exception {
  Person[] array=new Person[2];
  array[0]=new Person();
{
    Person person=new Person();
    person.setName("xxxx");
    array[1]=person;
  }
  assertArrayObject(array);
}
