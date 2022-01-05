@Test public void testIllegalGenerateArg() throws Exception {
  EntityDeserializer entitygen=new EntityDeserializer(new LaxContentLengthStrategy());
  try {
    entitygen.deserialize(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    entitygen.deserialize(new SessionInputBufferMock(new byte[]{}),null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
