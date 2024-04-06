package com.app.service;

import com.app.common.AppConfig;
import com.app.common.SingletonHolder;
import com.app.common.TcpServerConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author duyenthai
 */
public class ApplicationServiceConfig implements ApplicationService {

    private static final Logger LOGGER = LogManager.getLogger("ApplicationServiceConfig");

    private final String configFolder = System.getProperty("user.dir") + File.separator + "config";

    @Override
    public void process() {
        String configFile = configFolder + File.separator + "app-config.yml";
        File file = new File(configFile);
        AppConfig appConfig = new AppConfig();
        TcpServerConfig tcpServerConfig = new TcpServerConfig();
        tcpServerConfig.setListenAddress("0.0.0.0");
        tcpServerConfig.setPort(8899);
        appConfig.setTcpServer(tcpServerConfig);

        if (file.exists()) {
            LoaderOptions loaderOptions = new LoaderOptions();
            loaderOptions.setAllowDuplicateKeys(false);
            Constructor constructor = new Constructor(AppConfig.class, loaderOptions);
            try (InputStream stream = new FileInputStream(file)) {
                Yaml yaml = new Yaml(constructor);
                appConfig = yaml.load(stream);
            } catch (Exception ex) {
                appConfig = new AppConfig();
                LOGGER.error("Load config file error", ex);
            }
        } else {
            // set default configuration
            appConfig = new AppConfig();
        }
        SingletonHolder.getInstance().setConfig(appConfig);
        LOGGER.info("ApplicationServiceConfig.process() config loaded {}", appConfig.printConfig());
    }
}
