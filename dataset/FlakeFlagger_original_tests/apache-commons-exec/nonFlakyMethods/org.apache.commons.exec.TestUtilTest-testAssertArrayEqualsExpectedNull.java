public void testAssertArrayEqualsExpectedNull(){
  String[] expected=null;
  String[] actual=new String[]{"aaa","ddd","ccc"};
  try {
    TestUtil.assertEquals(expected,actual,true);
    fail("Must throw AssertionFailedError");
  }
 catch (  AssertionFailedError e) {
  }
}
