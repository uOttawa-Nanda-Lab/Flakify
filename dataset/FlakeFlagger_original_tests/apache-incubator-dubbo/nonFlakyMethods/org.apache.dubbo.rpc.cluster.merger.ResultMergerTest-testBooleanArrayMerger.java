/** 
 * BooleanArrayMerger test
 * @throws Exception
 */
@Test public void testBooleanArrayMerger() throws Exception {
  boolean[] arrayOne={true,false};
  boolean[] arrayTwo={false};
  boolean[] result=MergerFactory.getMerger(boolean[].class).merge(arrayOne,arrayTwo);
  Assert.assertEquals(3,result.length);
  boolean[] mergedResult={true,false,false};
  for (int i=0; i < mergedResult.length; i++) {
    Assert.assertEquals(mergedResult[i],result[i]);
  }
}
