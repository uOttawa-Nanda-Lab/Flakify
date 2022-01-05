@Test public void testMethodsSticky(){
  for (int i=0; i < 100; i++) {
    int count1=testSticky("method1",true);
    int count2=testSticky("method2",true);
    Assert.assertTrue(count1 == count2);
  }
}
