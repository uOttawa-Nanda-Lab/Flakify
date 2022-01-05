@Test public void testIllegalGenerateArg() throws Exception {
  EntitySerializer entitywriter=new EntitySerializer(new StrictContentLengthStrategy());
  try {
    entitywriter.serialize(null,null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    entitywriter.serialize(new SessionOutputBufferMock(),null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    entitywriter.serialize(new SessionOutputBufferMock(),new DummyHttpMessage(),null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
