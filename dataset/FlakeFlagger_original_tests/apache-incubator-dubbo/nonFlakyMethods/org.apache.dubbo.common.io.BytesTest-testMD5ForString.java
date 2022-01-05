@Test public void testMD5ForString(){
  byte[] md5=Bytes.getMD5("dubbo");
  assertThat(md5,is(Bytes.base642bytes("qk4bjCzJ3u2W/gEu8uB1Kg==")));
}
