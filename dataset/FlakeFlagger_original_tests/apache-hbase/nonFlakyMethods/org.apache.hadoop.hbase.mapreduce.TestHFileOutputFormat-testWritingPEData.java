/** 
 * Run small MR job.
 */
public void testWritingPEData() throws Exception {
  this.conf.setInt("io.sort.mb",20);
  this.conf.setLong("hbase.hregion.max.filesize",64 * 1024);
  Job job=new Job(this.conf,getName());
  job.setInputFormatClass(TestHFileOutputFormat.PEInputFormat.class);
  job.setMapperClass(TestHFileOutputFormat.PEtoKVMapper.class);
  job.setMapOutputKeyClass(ImmutableBytesWritable.class);
  job.setMapOutputValueClass(KeyValue.class);
  job.setPartitionerClass(SimpleTotalOrderPartitioner.class);
  job.getConfiguration().set(SimpleTotalOrderPartitioner.START,Bytes.toString(PerformanceEvaluation.format(0)));
  int rows=this.conf.getInt("mapred.map.tasks",1) * ROWSPERSPLIT;
  job.getConfiguration().set(SimpleTotalOrderPartitioner.END,Bytes.toString(PerformanceEvaluation.format(rows)));
  job.setReducerClass(KeyValueSortReducer.class);
  job.setOutputFormatClass(HFileOutputFormat.class);
  FileOutputFormat.setOutputPath(job,this.testDir);
  assertTrue(job.waitForCompletion(false));
  FileStatus[] files=this.fs.listStatus(this.testDir);
  assertTrue(files.length > 0);
}
