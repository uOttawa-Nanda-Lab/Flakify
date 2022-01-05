@Test public void testBooleanHash(){
  int h1=LangUtils.hashCode(LangUtils.HASH_SEED,true);
  int h2=LangUtils.hashCode(LangUtils.HASH_SEED,false);
  int h3=LangUtils.hashCode(LangUtils.HASH_SEED,true);
  int h4=LangUtils.hashCode(LangUtils.HASH_SEED,false);
  Assert.assertTrue(h1 != h2);
  Assert.assertTrue(h1 == h3);
  Assert.assertTrue(h2 == h4);
}
