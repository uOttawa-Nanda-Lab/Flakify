@Test public void testTaggedMoreArguments(){
  final Map<String,String> refTags=new HashMap<String,String>();
  refTags.put("foo","bar");
  refTags.put("baz","biz");
  Assert.assertEquals(MetricName.EMPTY.tag("foo","bar","baz","biz").getTags(),refTags);
}
