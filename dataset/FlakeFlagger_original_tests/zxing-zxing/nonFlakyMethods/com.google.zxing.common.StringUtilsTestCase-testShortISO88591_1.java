@Test public void testShortISO88591_1(){
  doTest(new byte[]{(byte)0x62,(byte)0xe5,(byte)0x64},"ISO-8859-1");
}
