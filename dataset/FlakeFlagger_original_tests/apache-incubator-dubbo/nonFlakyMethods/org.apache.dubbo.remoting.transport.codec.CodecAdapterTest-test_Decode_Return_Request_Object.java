@Test public void test_Decode_Return_Request_Object() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,(byte)0xe2,20,0,0,0,0,0,0,0,0,0,0,0,0};
  Person person=new Person();
  byte[] request=getRequestBytes(person,header);
  Request obj=(Request)decode(request);
  Assert.assertEquals(person,obj.getData());
  Assert.assertEquals(true,obj.isTwoWay());
  Assert.assertEquals(false,obj.isHeartbeat());
  Assert.assertEquals(Version.getProtocolVersion(),obj.getVersion());
  System.out.println(obj);
}
