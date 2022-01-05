public void testAssertArrayEqualsActualNull(){
  String[] expected=new String[]{"aaa","ccc","bbb"};
  String[] actual=null;
  try {
    TestUtil.assertEquals(expected,actual,true);
    fail("Must throw AssertionFailedError");
  }
 catch (  AssertionFailedError e) {
  }
}
