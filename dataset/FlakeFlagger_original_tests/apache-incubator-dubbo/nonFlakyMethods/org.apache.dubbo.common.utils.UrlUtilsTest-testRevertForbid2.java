@Test public void testRevertForbid2(){
  List<String> newForbid=UrlUtils.revertForbid(null,null);
  assertNull(newForbid);
}
