@Test public void test_PrimitiveArray() throws Exception {
  assertObject(new boolean[]{true,false});
  assertObject(new Boolean[]{true,false,true});
  assertObject(new byte[]{1,12,28,78});
  assertObject(new Byte[]{1,12,28,78});
  assertObject(new char[]{'a','中','无'});
  assertObject(new Character[]{'a','中','无'});
  assertObject(new short[]{37,39,12});
  assertObject(new Short[]{37,39,12});
  assertObject(new int[]{37,-39,12456});
  assertObject(new Integer[]{37,-39,12456});
  assertObject(new long[]{37L,-39L,123456789L});
  assertObject(new Long[]{37L,-39L,123456789L});
  assertObject(new float[]{37F,-3.14F,123456.7F});
  assertObject(new Float[]{37F,-39F,123456.7F});
  assertObject(new double[]{37D,-3.14D,123456.7D});
  assertObject(new Double[]{37D,-39D,123456.7D});
  assertArrayObject(new Boolean[]{true,false,true});
  assertArrayObject(new Byte[]{1,12,28,78});
  assertArrayObject(new Character[]{'a','中','无'});
  assertArrayObject(new Short[]{37,39,12});
  assertArrayObject(new Integer[]{37,-39,12456});
  assertArrayObject(new Long[]{37L,-39L,123456789L});
  assertArrayObject(new Float[]{37F,-39F,123456.7F});
  assertArrayObject(new Double[]{37D,-39D,123456.7D});
}
