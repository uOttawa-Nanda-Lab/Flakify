@Test public void test_Decode_Error_Response_Object() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,0x02,20,0,0,0,0,0,0,0,0,0,0,0,0};
  Person person=new Person();
  byte[] request=getRequestBytes(person,header);
  byte[] badbytes=new byte[]{-1,-2,-3,-4,-3,-4,-3,-4,-3,-4,-3,-4};
  System.arraycopy(badbytes,0,request,21,badbytes.length);
  Response obj=(Response)decode(request);
  Assert.assertEquals(90,obj.getStatus());
}
