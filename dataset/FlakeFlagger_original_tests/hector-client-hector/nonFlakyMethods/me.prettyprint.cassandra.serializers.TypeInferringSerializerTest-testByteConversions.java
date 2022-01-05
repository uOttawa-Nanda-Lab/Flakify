@Test public void testByteConversions(){
  TypeInferringSerializer ext=TypeInferringSerializer.get();
  ByteBuffer byteBuffer=ext.toByteBuffer("bytes".getBytes());
  Assert.assertTrue(byteBuffer != null);
}
