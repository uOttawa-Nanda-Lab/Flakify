@Test public void testBindNestedMapOfEnum() throws Exception {
  this.conversionService=new DefaultConversionService();
  TargetWithNestedMapOfEnum target=new TargetWithNestedMapOfEnum();
  bind(target,"nested.this: bar\n" + "nested.ThAt: 123");
  assertEquals("bar",target.getNested().get(Bingo.THIS));
  assertEquals("123",target.getNested().get(Bingo.THAT));
}
