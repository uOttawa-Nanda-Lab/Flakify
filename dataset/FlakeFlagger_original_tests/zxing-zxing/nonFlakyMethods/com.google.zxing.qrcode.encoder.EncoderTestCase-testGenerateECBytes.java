@Test public void testGenerateECBytes(){
  byte[] dataBytes={32,65,(byte)205,69,41,(byte)220,46,(byte)128,(byte)236};
  byte[] ecBytes=Encoder.generateECBytes(dataBytes,17);
  int[] expected={42,159,74,221,244,169,239,150,138,70,237,85,224,96,74,219,61};
  assertEquals(expected.length,ecBytes.length);
  for (int x=0; x < expected.length; x++) {
    assertEquals(expected[x],ecBytes[x] & 0xFF);
  }
  dataBytes=new byte[]{67,70,22,38,54,70,86,102,118,(byte)134,(byte)150,(byte)166,(byte)182,(byte)198,(byte)214};
  ecBytes=Encoder.generateECBytes(dataBytes,18);
  expected=new int[]{175,80,155,64,178,45,214,233,65,209,12,155,117,31,140,214,27,187};
  assertEquals(expected.length,ecBytes.length);
  for (int x=0; x < expected.length; x++) {
    assertEquals(expected[x],ecBytes[x] & 0xFF);
  }
  dataBytes=new byte[]{32,49,(byte)205,69,42,20,0,(byte)236,17};
  ecBytes=Encoder.generateECBytes(dataBytes,17);
  expected=new int[]{0,3,130,179,194,0,55,211,110,79,98,72,170,96,211,137,213};
  assertEquals(expected.length,ecBytes.length);
  for (int x=0; x < expected.length; x++) {
    assertEquals(expected[x],ecBytes[x] & 0xFF);
  }
}
