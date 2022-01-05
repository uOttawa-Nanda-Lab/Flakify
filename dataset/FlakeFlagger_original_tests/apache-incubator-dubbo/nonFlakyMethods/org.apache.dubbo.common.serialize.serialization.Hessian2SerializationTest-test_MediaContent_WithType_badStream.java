@Test public void test_MediaContent_WithType_badStream() throws Exception {
  ObjectOutput objectOutput=serialization.serialize(url,byteArrayOutputStream);
  objectOutput.writeObject(mediaContent);
  objectOutput.flushBuffer();
  byte[] byteArray=byteArrayOutputStream.toByteArray();
  for (int i=0; i < byteArray.length; i++) {
    if (i % 3 == 0) {
      byteArray[i]=(byte)~byteArray[i];
    }
  }
  ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
  try {
    ObjectInput deserialize=serialization.deserialize(url,byteArrayInputStream);
    @SuppressWarnings("unused") Object read=deserialize.readObject(MediaContent.class);
    fail();
  }
 catch (  IOException expected) {
    System.out.println(expected);
  }
}
