@Test public void test(){
  KeyMapperTester tester=new KeyMapperTester(new KeyMapper());
  String id="ABC123";
  tester.test(new SessionCreationMetaDataKey(id));
  tester.test(new SessionAccessMetaDataKey(id));
  tester.test(new SessionAttributesKey(id));
  tester.test(new SessionAttributeNamesKey(id));
  IntStream.range(0,10).mapToObj(i -> new SessionAttributeKey(id,i)).forEach(key -> tester.test(key));
  tester.test(new AuthenticationKey(id));
  tester.test(new CoarseSessionsKey(id));
}
