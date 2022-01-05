@Test public void testDecodeRow2result_2() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/2.png";
  ExpandedProductParsedResult expected=new ExpandedProductParsedResult("(01)90012345678908(3103)001750","90012345678908",null,null,null,null,null,null,"001750",ExpandedProductParsedResult.KILOGRAM,"3",null,null,null,new HashMap<String,String>());
  assertCorrectImage2result(path,expected);
}
