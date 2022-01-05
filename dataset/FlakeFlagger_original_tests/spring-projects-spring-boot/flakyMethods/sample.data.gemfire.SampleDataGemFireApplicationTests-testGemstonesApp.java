@Test public void testGemstonesApp(){
  assertEquals(0,this.gemstoneService.count());
  assertTrue(asList(this.gemstoneService.list()).isEmpty());
  this.gemstoneService.save(createGemstone("Diamond"));
  this.gemstoneService.save(createGemstone("Ruby"));
  assertEquals(2,this.gemstoneService.count());
  assertTrue(asList(this.gemstoneService.list()).containsAll(getGemstones("Diamond","Ruby")));
  try {
    this.gemstoneService.save(createGemstone("Coal"));
  }
 catch (  IllegalGemstoneException expected) {
  }
  assertEquals(2,this.gemstoneService.count());
  assertTrue(asList(this.gemstoneService.list()).containsAll(getGemstones("Diamond","Ruby")));
  this.gemstoneService.save(createGemstone("Pearl"));
  this.gemstoneService.save(createGemstone("Sapphire"));
  assertEquals(4,this.gemstoneService.count());
  assertTrue(asList(this.gemstoneService.list()).containsAll(getGemstones("Diamond","Ruby","Pearl","Sapphire")));
  try {
    this.gemstoneService.save(createGemstone("Quartz"));
  }
 catch (  IllegalGemstoneException expected) {
  }
  assertEquals(4,this.gemstoneService.count());
  assertTrue(asList(this.gemstoneService.list()).containsAll(getGemstones("Diamond","Ruby","Pearl","Sapphire")));
  assertEquals(createGemstone("Diamond"),this.gemstoneService.get("Diamond"));
  assertEquals(createGemstone("Pearl"),this.gemstoneService.get("Pearl"));
}
