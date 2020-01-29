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
package org.freeeed.print;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.tika.io.IOUtils;
import org.freeeed.data.index.ComponentLifecycle;
import org.freeeed.mail.EmailDataProvider;
import org.freeeed.mail.EmailUtil;
import org.freeeed.mail.EmlParser;
import org.freeeed.main.ParameterProcessing;
import org.freeeed.util.Util;
import org.freeeed.util.OsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.Files;

/**
 * Document conversions LibreOffice documentation pointers
 * https://ask.libreoffice.org/en/question/2641/convert-to-command-line-parameter/
 * <p>
 * For reference, commands like
 * soffice --convert-to html:"HTML" *.eml
 * should work, but they don't work well enough
 *
 * @author mark
 */
public class OfficePrint implements ComponentLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfficePrint.class);

    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList(new String[]{"htm", "html", "txt", "csv", "odt", "ppt", "xls", "xlsx", "doc", "docx", "eml"});

    private static OfficePrint instance;

    private OfficePrint() {
    }

    public static synchronized OfficePrint getInstance() {
        if (instance == null) {
            instance = new OfficePrint();
        }
        return instance;
    }

    public void createPdf(File officeDocFile, String originalFileName) {
        String extension = Util.getExtension(officeDocFile.getPath());
        File outputFile = new File(officeDocFile.getPath() + ".pdf");
        if (extension == null || extension.isEmpty()) {
            extension = Util.getExtension(originalFileName);
        }

        try {
            if ("html".equalsIgnoreCase(extension) || "htm".equalsIgnoreCase(extension)) {
                try {
                    Html2Pdf.html2pdf(officeDocFile.getPath(), outputFile);
                } catch (Exception e) {
                    LOGGER.info("htmltopdf imaging not able to process file, trying OpenOffice imaging instead", e.getMessage());
                    convertToPdfWithSOffice(officeDocFile, outputFile);
                }
                return;
            } else if ("pdf".equalsIgnoreCase(extension)) {
                Files.copy(officeDocFile, outputFile);
                return;
            } else if ("eml".equalsIgnoreCase(extension)) {
                EmlParser emlParser = new EmlParser(officeDocFile);
                convertToPDFUsingHtml(officeDocFile, outputFile, emlParser);
                return;
            } else if (extension.toLowerCase().equals("csv")) {
                convertCSVToPdf(officeDocFile, originalFileName, outputFile);
                return;
            } else {
                if (OsUtil.hasSOffice()) {
                    convertToPdfWithSOffice(officeDocFile, outputFile);
                }
                return;
            }
        } catch (Exception e) {
            LOGGER.error("Problem creating PDF file for: {}", officeDocFile, e);
        }

        try {
            IOUtils.copy(getClass().getClassLoader().getResourceAsStream(ParameterProcessing.NO_PDF_IMAGE_FILE),
                    new FileOutputStream(outputFile));
        } catch (IOException e) {
            LOGGER.error("Problem with default imaging", e);
        }
    }

    private void convertCSVToPdf(File officeDocFile, String originalFileName, File outputFile) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(officeDocFile));
            Document document = new Document(PageSize.LETTER);
            PdfWriter.getInstance(document, new FileOutputStream(outputFile));
            document.open();
            document.addSubject(originalFileName);
            document.addTitle(originalFileName);

            String line = "";
            while(null != (line = input.readLine())) {
                Paragraph p = new Paragraph(line);
                p.setAlignment(Element.ALIGN_JUSTIFIED);
                document.add(p);
            }
            document.close();
            input.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String emlToHtml(File file) {
        String html = "";
        try {
            EmlParser emlParser = new EmlParser(file);
            html = EmailUtil.createHtmlFromEmlFile(file.getPath(), emlParser);
        } catch (Exception e) {
            LOGGER.error("Error converting to html", e);
        }
        return html;
    }

    private void convertToPDFUsingHtml(File file, File outputPdf, EmailDataProvider emlParser) {
        try {
            String emlHtmlContent = EmailUtil.createHtmlFromEmlFile(file.getPath(), emlParser);
            Html2Pdf.htmlContent2Pdf(emlHtmlContent, outputPdf);
        } catch (Exception e) {
            LOGGER.error("Cannot convert eml file: {}", e.getMessage());
            convertToPdfWithSOffice(file, outputPdf);
        }
    }

    /**
     * soffice commandline exampple soffice --headless --convert-to
     * pdf:writer_pdf_Export --outdir . AdminContracts.doc
     *
     * @param inputFile
     * @param outputFile
     * @return
     */
    public boolean convertToPdfWithSOffice(File inputFile, File outputFile) {
        /*
        String extension = Files.getFileExtension(inputFile.getAbsolutePath()).toLowerCase();

        // passing unsupported extension causes soffice to freeze, need to review which extensions are supported.
        if (SUPPORTED_EXTENSIONS.indexOf(extension) >= 0) {
            String fullCommand = OsUtil.getSOfficeExecutableLocation() + " --headless "
                    + " --convert-to pdf:writer_pdf_Export " + inputFile.getAbsolutePath()
                    + " --outdir " + outputFile.getParentFile().getAbsolutePath();
            try {
                OsUtil.runCommand(fullCommand);
                File sofficeOutputFile = new File(FilenameUtils.removeExtension(inputFile.getAbsolutePath()) + ".pdf");
                if (sofficeOutputFile.exists()) {
                    LOGGER.info("Created PDF at: " + sofficeOutputFile);
                    outputFile.delete();
                    FileUtils.moveFile(sofficeOutputFile, outputFile);
                    return true;
                } else {
                    LOGGER.warn("soffice did not produce PDF");
                    return false;
                }
            } catch (IOException e) {
                LOGGER.error("Could not convert to PDF", e);
            }
        }
*/
        return false;
    }
}
