public void testComparison() throws Exception {
  runTests("aa","b",-1);
  runTests("aa","aa",0);
  runTests("aa","ab",-1);
  runTests("aa","aaa",-1);
  runTests("","",0);
  runTests("","a",-1);
}
