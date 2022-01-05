@Test public void testNullObjectHash(){
  int h1=LangUtils.hashCode(LangUtils.HASH_SEED,null);
  int h2=LangUtils.hashCode(LangUtils.HASH_SEED,0);
  Assert.assertTrue(h1 == h2);
}
