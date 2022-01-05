@Test public void testCache() throws IOException {
  final HashStrategy builder=new CRC32HashStrategy();
  final CacheKey key=new CacheKey("testGroup",ResourceType.JS,false);
  final String content="var foo = 'Hello World';";
  final String hash=builder.getHash(new ByteArrayInputStream(content.getBytes()));
  Assert.assertNull(cache.get(key));
  cache.put(key,CacheValue.valueOf(content,hash));
  final CacheValue entry=cache.get(key);
  Assert.assertNotNull(entry);
  Assert.assertEquals(hash,entry.getHash());
  Assert.assertEquals(content,entry.getRawContent());
  cache.clear();
  Assert.assertNull(cache.get(key));
  cache.put(key,CacheValue.valueOf(content,hash));
  Assert.assertNotNull(cache.get(key));
  cache.destroy();
  Assert.assertNull(cache.get(key));
}
