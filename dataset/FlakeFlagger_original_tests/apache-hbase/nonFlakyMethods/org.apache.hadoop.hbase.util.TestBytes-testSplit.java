public void testSplit() throws Exception {
  byte[] lowest=Bytes.toBytes("AAA");
  byte[] middle=Bytes.toBytes("CCC");
  byte[] highest=Bytes.toBytes("EEE");
  byte[][] parts=Bytes.split(lowest,highest,1);
  for (int i=0; i < parts.length; i++) {
    System.out.println(Bytes.toString(parts[i]));
  }
  assertEquals(3,parts.length);
  assertTrue(Bytes.equals(parts[1],middle));
  highest=Bytes.toBytes("DDD");
  parts=Bytes.split(lowest,highest,2);
  for (int i=0; i < parts.length; i++) {
    System.out.println(Bytes.toString(parts[i]));
  }
  assertEquals(4,parts.length);
  assertTrue(Bytes.equals(parts[2],middle));
}
