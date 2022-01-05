@Test public void test_Decode_Return_Response_Person() throws IOException {
  byte[] header=new byte[]{MAGIC_HIGH,MAGIC_LOW,2,20,0,0,0,0,0,0,0,0,0,0,0,0};
  Person person=new Person();
  byte[] request=getRequestBytes(person,header);
  Response obj=(Response)decode(request);
  Assert.assertEquals(20,obj.getStatus());
  Assert.assertEquals(person,obj.getResult());
  System.out.println(obj);
}
