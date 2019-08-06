/*
 *
 * Copyright SHMsoft, Inc. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.freeeed.util;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author mark
 */
public class TrueZipUtilTest {

    /**
     * Test of countFiles method, of class TrueZipUtil.
     */
    @Test
    public void testCountFiles() {
        System.out.println("countFiles");
        String zipFilePath = "../test-data/staged/input00002_c2.zip";
        TrueZipUtil instance = new TrueZipUtil();
        int expResult = 57;
        int result = instance.countFiles(zipFilePath);
        assertEquals(expResult, result);
    }

//    @Test
    public void testZipMerge() throws IOException {
        String zipFile1 = "../test-data/multizip/one.zip";
        String zipFile2 = "../test-data/multizip/two.zip";
        // TrueZipUtil instance = new TrueZipUtil();

        // int zip1Count = instance.countFiles(zipFile1);
        // int zip2Count = instance.countFiles(zipFile2);

        TrueZipUtil.mergeTwoZips(zipFile1, zipFile2);
    }
}
