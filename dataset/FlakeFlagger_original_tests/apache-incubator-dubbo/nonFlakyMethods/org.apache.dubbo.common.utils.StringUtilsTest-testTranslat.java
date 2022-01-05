@Test public void testTranslat() throws Exception {
  String s="16314";
  assertEquals(StringUtils.translat(s,"123456","abcdef"),"afcad");
  assertEquals(StringUtils.translat(s,"123456","abcd"),"acad");
}
