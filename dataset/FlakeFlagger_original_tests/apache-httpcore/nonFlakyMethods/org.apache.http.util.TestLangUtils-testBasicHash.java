@Test public void testBasicHash(){
  Integer i=new Integer(1234);
  int h1=LangUtils.hashCode(LangUtils.HASH_SEED,i.hashCode());
  int h2=LangUtils.hashCode(LangUtils.HASH_SEED,i);
  Assert.assertTrue(h1 == h2);
}
