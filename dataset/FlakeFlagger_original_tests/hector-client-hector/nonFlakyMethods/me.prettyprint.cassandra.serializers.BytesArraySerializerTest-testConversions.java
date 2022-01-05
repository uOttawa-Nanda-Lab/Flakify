@Test public void testConversions(){
  test(null);
  test(new byte[]{});
  test(new byte[]{1});
  test(new byte[]{1,2,3,4,5});
  test(bytes(""));
  test(bytes("123"));
}
