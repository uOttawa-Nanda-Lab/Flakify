@Test public void testMain() throws Exception {
  short s=(short)0xabcd;
  assertThat(s,is(Bytes.bytes2short(Bytes.short2bytes(s))));
  int i=198284;
  assertThat(i,is(Bytes.bytes2int(Bytes.int2bytes(i))));
  long l=13841747174L;
  assertThat(l,is(Bytes.bytes2long(Bytes.long2bytes(l))));
  float f=1.3f;
  assertThat(f,is(Bytes.bytes2float(Bytes.float2bytes(f))));
  double d=11213.3;
  assertThat(d,is(Bytes.bytes2double(Bytes.double2bytes(d))));
  assertThat(Bytes.int2bytes(i),is(int2bytes(i)));
  assertThat(Bytes.long2bytes(l),is(long2bytes(l)));
  String str=Bytes.bytes2base64("dubbo".getBytes());
  byte[] bytes=Bytes.base642bytes(str,0,str.length());
  assertThat(bytes,is("dubbo".getBytes()));
  byte[] bytesWithC64=Bytes.base642bytes(str,C64);
  assertThat(bytesWithC64,is(bytes));
}
