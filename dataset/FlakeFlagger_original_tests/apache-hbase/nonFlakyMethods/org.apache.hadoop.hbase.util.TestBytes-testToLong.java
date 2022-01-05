public void testToLong() throws Exception {
  long[] longs={-1l,123l,122232323232l};
  for (int i=0; i < longs.length; i++) {
    byte[] b=Bytes.toBytes(longs[i]);
    assertEquals(longs[i],Bytes.toLong(b));
  }
}
