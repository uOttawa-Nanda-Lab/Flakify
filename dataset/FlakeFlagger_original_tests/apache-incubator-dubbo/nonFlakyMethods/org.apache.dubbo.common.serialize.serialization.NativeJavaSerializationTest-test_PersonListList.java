@Test public void test_PersonListList() throws Exception {
  List<List<Person>> args=new ArrayList<List<Person>>();
  List<Person> sublist=new ArrayList<Person>();
  sublist.add(new Person());
  args.add(sublist);
  try {
    ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
    objectOutput.writeObject(args);
    fail();
  }
 catch (  NotSerializableException expected) {
  }
catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Serialized class org.apache.dubbo.common.model.Person must implement java.io.Serializable"));
  }
}
