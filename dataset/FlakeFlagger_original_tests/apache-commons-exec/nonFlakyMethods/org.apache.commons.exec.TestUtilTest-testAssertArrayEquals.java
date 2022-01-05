public void testAssertArrayEquals(){
  String[] expected=new String[]{"aaa","bbb","ccc"};
  String[] actual=new String[]{"aaa","bbb","ccc"};
  TestUtil.assertEquals(expected,actual,true);
}
