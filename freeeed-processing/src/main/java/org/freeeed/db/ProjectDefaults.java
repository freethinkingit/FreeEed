/*
 * Copyright SHMsoft, Inc. 
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
package org.freeeed.db;

import org.freeeed.main.ParameterProcessing;
import org.freeeed.services.Project;

/**
 *
 * @author mark
 */
class ProjectDefaults {

    private final static String[][] INIT_PROPERTIES
            = {
                {"add_email_attach_to_pdf", "false"},
                {"staging-dir", "test-output/staging"},
                {"output-dir", "test-output/output"},
                {"file-system", "local"},
                {"es_endpoint", "http\\://localhost\\:9200"},
                {"files-per-zip-staging", "50"},
                // inputs are stored as one string. That is not a problem since 
                // SQLite imposes no limit on text other than the total blob size
                {"field-separator", "pipe"},
                {"metadata", "standard"},
                {"create-pdf", "false"},
                {"preview", "false"},
                {"lucene_fs_index_enabled", "false"},
                {"stage", "true"},
                {"gigs-per-zip-staging", ".1"},
                {"sample-percent", "0.0"},
                {"process-where", "local"},
                {"ocr_enabled", "true"},
                {"project-name", "FreeEed sample project"},
                {"data_source", "0"},
                {ParameterProcessing.LOAD_FILE_FORMAT, "CSV"},
                {Project.DELETED, "no"},
            };

    static String[][] getInitProperties() {
        return INIT_PROPERTIES;
    }
}
