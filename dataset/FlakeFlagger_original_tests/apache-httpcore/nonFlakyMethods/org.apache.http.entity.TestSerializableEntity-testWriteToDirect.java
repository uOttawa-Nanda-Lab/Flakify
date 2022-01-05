@Test public void testWriteToDirect() throws Exception {
  Serializable serializableObj=new SerializableObject();
  SerializableEntity httpentity=new SerializableEntity(serializableObj,false);
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  httpentity.writeTo(out);
  byte[] bytes=out.toByteArray();
  Assert.assertNotNull(bytes);
  ObjectInputStream oin=new ObjectInputStream(new ByteArrayInputStream(bytes));
  SerializableObject serIn=(SerializableObject)oin.readObject();
  Assert.assertEquals(4,serIn.intValue);
  Assert.assertEquals("Hello",serIn.stringValue);
  try {
    httpentity.writeTo(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
