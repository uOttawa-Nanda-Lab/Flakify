@Test public void customOrderWithCustomStatus(){
  this.healthAggregator.setStatusOrder(Arrays.asList("DOWN","OUT_OF_SERVICE","UP","UNKNOWN","CUSTOM"));
  Map<String,Health> healths=new HashMap<String,Health>();
  healths.put("h1",new Health.Builder().status(Status.DOWN).build());
  healths.put("h2",new Health.Builder().status(Status.UP).build());
  healths.put("h3",new Health.Builder().status(Status.UNKNOWN).build());
  healths.put("h4",new Health.Builder().status(Status.OUT_OF_SERVICE).build());
  healths.put("h5",new Health.Builder().status(new Status("CUSTOM")).build());
  assertEquals(Status.DOWN,this.healthAggregator.aggregate(healths).getStatus());
}
