/*
Copyright [2013-2014] eBay Software Foundation

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

package com.ccoe.build.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ccoe.build.core.utils.FileUtils;

public class FileUtilsTest {
	
	File resourceFolder = new File(this.getClass().getResource("/").getFile());
	
	@Test
	public void testLoadFiles() {
		
		File[] files = FileUtils.loadFiles(resourceFolder, ".xml");
		assertNotNull(files);
		assertEquals(2, files.length);
		assertEquals("a.xml", files[0].getName());
		
		files = FileUtils.loadFiles(resourceFolder, ".DONE");
		assertNotNull(files);
		assertEquals(0, files.length);
	}
	
	@Test
	public void testReadFile() {
		File[] files = FileUtils.loadFiles(resourceFolder, ".xml");
		String contents = FileUtils.readFile(files[0]);
		assertEquals("<a>test</a>", contents.trim());
	}
	
	@Test
	public void testDiskClean() {
		File dc = new File(resourceFolder, "diskclean");
		
		assertTrue(dc.exists());
		
		FileUtils.renameDoneFile(new File(dc, "filestodelete.txt"));
		
		File[] files = FileUtils.loadDoneFiles(dc);
		assertTrue(files.length > 0);
		FileUtils.diskClean(dc, 0);
		files = FileUtils.loadDoneFiles(dc);
		assertEquals(0, files.length);
	}
	
	
	@Test
	public void testWrite() {
		File dc = new File(resourceFolder, "diskclean");
		File file = new File(dc, "test.done");
		assertFalse(file.exists());
		FileUtils.writeToFile(file, "some contents");
		assertTrue(file.exists());
	}
	
	@Test
	public void testModifyProperty() {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("scheduler.email.from", "xiaobao@from.com");
		map.put("scheduler.reliability.email.to", "xiaobao@to.com");
		map.put("scheduler.pfdash.time", "0 24/2 11 * * ?");
		map.put("newkey", "newvalue");
		System.out.println(resourceFolder);
		File file = new File(resourceFolder,"application.properties");
		String unmodified = FileUtils.readFile(file);
		FileUtils.modifyPropertyFile(file, map);
		String modified = FileUtils.readFile(file);
		assertFalse(unmodified.equals(modified));		
	}
}
