@Test public void testBasicsBuff() throws Exception {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  ObjectOutputStream out=new ObjectOutputStream(baos);
  Serializable serializableObj=new SerializableObject();
  out.writeObject(serializableObj);
  SerializableEntity httpentity=new SerializableEntity(serializableObj,true);
  Assert.assertEquals(baos.toByteArray().length,httpentity.getContentLength());
  Assert.assertNotNull(httpentity.getContent());
  Assert.assertTrue(httpentity.isRepeatable());
  Assert.assertFalse(httpentity.isStreaming());
}
