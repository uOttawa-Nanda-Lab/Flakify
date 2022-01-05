@Test public void test_Person() throws Exception {
  try {
    ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
    objectOutput.writeObject(new Person());
    fail();
  }
 catch (  NotSerializableException expected) {
  }
catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Serialized class org.apache.dubbo.common.model.Person must implement java.io.Serializable"));
  }
}
