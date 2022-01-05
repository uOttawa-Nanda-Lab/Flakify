@Test public void testBasicsDirect() throws Exception {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  ObjectOutputStream out=new ObjectOutputStream(baos);
  Serializable serializableObj=new SerializableObject();
  out.writeObject(serializableObj);
  SerializableEntity httpentity=new SerializableEntity(serializableObj,false);
  Assert.assertEquals(-1,httpentity.getContentLength());
  Assert.assertNotNull(httpentity.getContent());
  Assert.assertTrue(httpentity.isRepeatable());
  Assert.assertFalse(httpentity.isStreaming());
}
