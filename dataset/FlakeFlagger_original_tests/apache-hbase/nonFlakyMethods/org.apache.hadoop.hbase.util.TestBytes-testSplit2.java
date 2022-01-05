public void testSplit2() throws Exception {
  byte[] lowest=Bytes.toBytes("http://A");
  byte[] highest=Bytes.toBytes("http://z");
  byte[] middle=Bytes.toBytes("http://]");
  byte[][] parts=Bytes.split(lowest,highest,1);
  for (int i=0; i < parts.length; i++) {
    System.out.println(Bytes.toString(parts[i]));
  }
  assertEquals(3,parts.length);
  assertTrue(Bytes.equals(parts[1],middle));
}
