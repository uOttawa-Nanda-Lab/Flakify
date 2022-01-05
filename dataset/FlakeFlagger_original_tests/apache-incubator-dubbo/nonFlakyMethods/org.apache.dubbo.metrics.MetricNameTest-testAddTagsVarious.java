@Test public void testAddTagsVarious(){
  final Map<String,String> refTags=new HashMap<String,String>();
  refTags.put("foo","bar");
  final MetricName test=MetricName.EMPTY.tag("foo","bar");
  final MetricName test2=MetricName.EMPTY.tag(refTags);
  Assert.assertEquals(test,new MetricName(null,refTags));
  Assert.assertEquals(test.getTags(),refTags);
  Assert.assertEquals(test2,new MetricName(null,refTags));
  Assert.assertEquals(test2.getTags(),refTags);
}
