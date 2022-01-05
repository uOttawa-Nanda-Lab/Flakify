@Test public void testGetNonExistentDoesNotNPE(){
  final SecureHashMap<String,String> map=new SecureHashMap<String,String>();
  map.get("nothing");
}
