public void testSplit3() throws Exception {
  byte[] low={1,1,1};
  byte[] high={1,1,3};
  try {
    Bytes.split(high,low,1);
    assertTrue("Should not be able to split if low > high",false);
  }
 catch (  IllegalArgumentException iae) {
  }
  byte[][] parts=Bytes.split(low,high,1);
  for (int i=0; i < parts.length; i++) {
    System.out.println("" + i + " -> "+ Bytes.toStringBinary(parts[i]));
  }
  assertTrue("Returned split should have 3 parts but has " + parts.length,parts.length == 3);
  parts=Bytes.split(low,high,2);
  assertTrue("Returned split but should have failed",parts == null);
}
