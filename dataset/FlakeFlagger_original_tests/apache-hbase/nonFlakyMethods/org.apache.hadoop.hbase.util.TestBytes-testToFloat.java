public void testToFloat() throws Exception {
  float[] floats={-1f,123.123f,Float.MAX_VALUE};
  for (int i=0; i < floats.length; i++) {
    byte[] b=Bytes.toBytes(floats[i]);
    assertEquals(floats[i],Bytes.toFloat(b));
  }
}
