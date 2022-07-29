/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * These authors would like to acknowledge the Spanish Ministry of Industry,
 * Tourism and Trade, for the support in the project TSI020301-2008-2
 * "PIRAmIDE: Personalizable Interactions with Resources on AmI-enabled
 * Mobile Dynamic Environments", led by Treelogic
 * ( http://www.treelogic.com/ ):
 *
 *   http://www.piramidepse.com/
 */

package com.google.zxing.oned.rss.expanded;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.BufferedImageLuminanceSource;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.oned.rss.DataCharacter;
import com.google.zxing.oned.rss.FinderPattern;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Pablo Orduña, University of Deusto (pablo.orduna@deusto.es)
 * @author Eduardo Castillejo, University of Deusto (eduardo.castillejo@deusto.es)
 */
public final class RSSExpandedInternalTestCase extends Assert {

  @Test public void testDecodeCheckCharacter() throws Exception{String path="test/data/blackbox/rssexpanded-1/3.png";File file=new File(path);if (!file.exists()){file=new File("core",path);}BufferedImage image=ImageIO.read(file);BinaryBitmap binaryMap=new BinaryBitmap(new GlobalHistogramBinarizer(new BufferedImageLuminanceSource(image)));BitArray row=binaryMap.getBlackRow(binaryMap.getHeight() / 2,null);int[] startEnd={145,243};int value=0;FinderPattern finderPatternA1=new FinderPattern(value,startEnd,startEnd[0],startEnd[1],image.getHeight() / 2);RSSExpandedReader rssExpandedReader=new RSSExpandedReader();DataCharacter dataCharacter=rssExpandedReader.decodeDataCharacter(row,finderPatternA1,true,true);assertEquals(98,dataCharacter.getValue());}
}
