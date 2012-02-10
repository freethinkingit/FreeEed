package org.freeeed.main;

import java.io.File;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.freeeed.services.History;

/**
 * Default application parameters
 *
 * @author mark
 */
public class ParameterProcessing {

    private static final String defaultParameterFile = "config/default.freeeed.properties";
    public static final String FILES_PER_ZIP_STAGING = "files-per-zip-staging";
    public static final String PROJECT_CODE = "project-code";
    public static final String PROJECT_NAME = "project-name";
    public static final String PROJECT_FILE_NAME = "project-file-name";
    public static final String PROJECT_INPUTS = "input";
    public static final String PROJECT_CUSTODIANS = "custodian";
    public static final String PROCESS_WHERE = "process-where";
    public static final String PROCESS_WHERE_HADOOP = "hadoop";
    public static final String PROCESS_WHERE_LOCAL = "local";    
    //public static final String LOCAL = "local";
    public static final String STAGE = "stage";
    public static final String CULLING = "culling";
    public static final String CONTENT = "content";
    public static final String TITLE = "title";
    public static final String NATIVE = "native";
    public static final String PDF = "pdf";
    public static final String NATIVE_AS_PDF = "native-as-pdf";
    public static final String TEXT = "text";
    public static final String OUTPUT_DIR = "freeeed_output";
    public static final String OUTPUT_DIR_HADOOP = "output-dir-hadoop";
    public static final String TMP_DIR = "tmp" + File.separator;
    // tmp dir for Hadoop environment - which means Unix, will also work on EC2
    public static final String TMP_DIR_HADOOP = "/mnt/tmp";
    public static final String DOWNLOAD_DIR = "freeeed_download";
    public static final String PST_OUTPUT_DIR = "pst_output";
    public static final String stagingDir = OUTPUT_DIR + File.separator + "staging";
    public static final String resultsDir = OUTPUT_DIR + File.separator + "output" + File.separator;
    public static final String inventoryFileName = stagingDir + File.separator + "inventory";
    public static final String USE_JPST = "use_jpst";
    public static final String CREATE_PDF = "create-pdf";
    public static final String PROJECT = "project";
    public static final String WORK_AREA = "/freeeed_work_area";
    public static final String LOAD_FORMAT = "load-format";
    public static final String METADATA_FILE = "metadata-file";

    /**
     * Custom configuration / processing parameters
     *
     * @param customParametersFile file path of properties file
     * @return
     */
    public static Configuration collectProcessingParameters(String customParametersFile) {

        // apache.commons configuration class
        CompositeConfiguration cc = new CompositeConfiguration();

        try {
            // custom parameter file is first priority
            if (customParametersFile != null) {
                // read file
                Configuration customProperties = new FreeEedConfiguration(customParametersFile);
                // add to configuration
                cc.addConfiguration(customProperties);
            }

            // default parameter file is last priority

            // read file
            Configuration defaults = new FreeEedConfiguration(defaultParameterFile);
            // add to configuration
            cc.addConfiguration(defaults);

            // set project file name
            cc.setProperty(PROJECT_FILE_NAME, customParametersFile);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // follow the "fail-fast" design pattern
            System.exit(0);
        }
        return cc;
    }

    /**
     * Default configuration / processing parameters
     *
     * @return
     */
    public static Configuration setDefaultParameters() {
        CompositeConfiguration cc = new CompositeConfiguration();
        try {
            Configuration defaults = new FreeEedConfiguration(defaultParameterFile);
            cc.addConfiguration(defaults);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // follow the "fail-fast" design pattern
            System.exit(0);
        }
        return cc;
    }

    /**
     * Echo configuration, save configuration, and update application log
     *
     * @param configuration processing parameters
     * @throws ConfigurationException
     * @throws MalformedURLException
     */
    public static void echoProcessingParameters(Configuration configuration)
            throws ConfigurationException, MalformedURLException {
        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyMMdd_HHmmss");
        String runParameterFileName = "freeeed.parameters."
                + fileNameFormat.format(new Date()) + ".project";

        // save configuration
        FreeEedConfiguration configToSave = new FreeEedConfiguration();
        configToSave.cleanup();
        configToSave.append(configuration);
        configToSave.setProperty("processed_by ", Version.getVersionAndBuild());
        String paramPath = FreeEedLogging.logDir + "/" + runParameterFileName;
        configToSave.save(paramPath);
        configToSave.restore();

        // update application log
        History.appendToHistory("Processing parameters were saved to " + paramPath);
    }
}
