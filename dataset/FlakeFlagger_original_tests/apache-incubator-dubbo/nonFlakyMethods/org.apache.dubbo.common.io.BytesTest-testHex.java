@Test public void testHex(){
  String str=Bytes.bytes2hex(b1);
  assertThat(b1,is(Bytes.hex2bytes(str)));
}
