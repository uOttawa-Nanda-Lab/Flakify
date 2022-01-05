@Test public void testShortShiftJIS_1(){
  doTest(new byte[]{(byte)0x8b,(byte)0xe0,(byte)0x8b,(byte)0x9b},"SJIS");
}
