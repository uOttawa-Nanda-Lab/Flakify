@Test public void testDecode_WithExitByte() throws IOException {
  HashMap<byte[],Boolean> exitbytes=new HashMap<byte[],Boolean>();
  exitbytes.put(new byte[]{3},true);
  exitbytes.put(new byte[]{1,3},false);
  exitbytes.put(new byte[]{-1,-12,-1,-3,6},true);
  exitbytes.put(new byte[]{1,-1,-12,-1,-3,6},false);
  exitbytes.put(new byte[]{-1,-19,-1,-3,6},true);
  for (  byte[] exit : exitbytes.keySet()) {
    testDecode_WithExitByte(exit,exitbytes.get(exit));
  }
}
