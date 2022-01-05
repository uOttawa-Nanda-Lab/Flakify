/** 
 * CharArrayMerger test
 * @throws Exception
 */
@Test public void testCharArrayMerger() throws Exception {
  char[] arrayOne="hello".toCharArray();
  char[] arrayTwo="world".toCharArray();
  char[] result=MergerFactory.getMerger(char[].class).merge(arrayOne,arrayTwo);
  Assert.assertEquals(10,result.length);
  char[] mergedResult="helloworld".toCharArray();
  for (int i=0; i < mergedResult.length; i++) {
    Assert.assertEquals(mergedResult[i],result[i]);
  }
}
