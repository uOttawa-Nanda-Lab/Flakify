@Test public void write() throws Exception {
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  ByteString.decodeHex("616263").write(out);
  assertByteArraysEquals(new byte[]{0x61,0x62,0x63},out.toByteArray());
}
