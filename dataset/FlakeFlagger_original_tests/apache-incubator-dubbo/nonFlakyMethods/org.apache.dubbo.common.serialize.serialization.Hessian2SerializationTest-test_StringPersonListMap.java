@Test public void test_StringPersonListMap() throws Exception {
  Map<String,List<Person>> args=new HashMap<String,List<Person>>();
  List<Person> sublist=new ArrayList<Person>();
  sublist.add(new Person());
  args.put("1",sublist);
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
