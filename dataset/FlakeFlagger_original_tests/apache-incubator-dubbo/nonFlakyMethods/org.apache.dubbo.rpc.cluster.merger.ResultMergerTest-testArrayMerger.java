/** 
 * ArrayMerger test
 * @throws Exception
 */
@Test public void testArrayMerger() throws Exception {
  String[] stringArray1={"1","2","3"};
  String[] stringArray2={"4","5","6"};
  String[] stringArray3={};
  Object result=ArrayMerger.INSTANCE.merge(stringArray1,stringArray2,stringArray3);
  Assert.assertTrue(result.getClass().isArray());
  Assert.assertEquals(6,Array.getLength(result));
  Assert.assertTrue(String.class.isInstance(Array.get(result,0)));
  for (int i=0; i < 6; i++) {
    Assert.assertEquals(String.valueOf(i + 1),Array.get(result,i));
  }
  int[] intArray1={1,2,3};
  int[] intArray2={4,5,6};
  int[] intArray3={7};
  result=MergerFactory.getMerger(int[].class).merge(intArray1,intArray2,intArray3);
  Assert.assertTrue(result.getClass().isArray());
  Assert.assertEquals(7,Array.getLength(result));
  Assert.assertTrue(int.class == result.getClass().getComponentType());
  for (int i=0; i < 7; i++) {
    Assert.assertEquals(i + 1,Array.get(result,i));
  }
}
