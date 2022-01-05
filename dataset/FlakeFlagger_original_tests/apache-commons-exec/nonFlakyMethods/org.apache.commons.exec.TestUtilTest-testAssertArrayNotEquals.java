public void testAssertArrayNotEquals(){
  String[] expected=new String[]{"aaa","bbb","ccc"};
  String[] actual=new String[]{"aaa","ddd","ccc"};
  try {
    TestUtil.assertEquals(expected,actual,true);
    fail("Must throw AssertionFailedError");
  }
 catch (  AssertionFailedError e) {
  }
}
