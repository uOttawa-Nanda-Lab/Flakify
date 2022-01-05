@Test public void testCamelToSplitName() throws Exception {
  assertEquals("ab-cd-ef",StringUtils.camelToSplitName("abCdEf","-"));
  assertEquals("ab-cd-ef",StringUtils.camelToSplitName("AbCdEf","-"));
  assertEquals("ab-cd-ef",StringUtils.camelToSplitName("ab-cd-ef","-"));
  assertEquals("abcdef",StringUtils.camelToSplitName("abcdef","-"));
}
