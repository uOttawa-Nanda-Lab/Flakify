public void testAssertArrayNotOrderEquals(){
  String[] expected=new String[]{"aaa","ccc","bbb"};
  String[] actual=new String[]{"aaa","ddd","ccc"};
  try {
    TestUtil.assertEquals(expected,actual,true);
    fail("Must throw AssertionFailedError");
  }
 catch (  AssertionFailedError e) {
  }
}
