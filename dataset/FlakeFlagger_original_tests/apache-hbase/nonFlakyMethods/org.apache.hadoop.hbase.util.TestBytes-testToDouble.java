public void testToDouble() throws Exception {
  double[] doubles={Double.MIN_VALUE,Double.MAX_VALUE};
  for (int i=0; i < doubles.length; i++) {
    byte[] b=Bytes.toBytes(doubles[i]);
    assertEquals(doubles[i],Bytes.toDouble(b));
  }
}
