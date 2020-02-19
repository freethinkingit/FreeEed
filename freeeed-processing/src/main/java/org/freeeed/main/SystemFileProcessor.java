package org.freeeed.main;

import org.apache.hadoop.io.MD5Hash;
import org.freeeed.mr.MetadataWriter;
import org.freeeed.util.Util;

import java.io.IOException;

public class SystemFileProcessor extends FileProcessor {

    public SystemFileProcessor(DiscoveryFile discoveryFile) {
        this.discoveryFile = discoveryFile;
    }

    @Override
    public void run() {
        DocumentMetadata metadata = new DocumentMetadata();
        discoveryFile.setMetadata(metadata);
        System.out.println(discoveryFile.getPath());
        metadata.setOriginalPath(getOriginalDocumentPath(discoveryFile));
        metadata.setHasAttachments(discoveryFile.isHasAttachments());
        metadata.setHasParent(discoveryFile.isHasParent());
        // MD5Hash hash = Util.createKeyHash(discoveryFile.getPath(), metadata);
        // metadata.setHash(hash.toString());
        metadata.acquireUniqueId();
        metadata.set(DocumentMetadataKeys.PROCESSING_EXCEPTION, "System File");
        writeMetadata();
    }
}
