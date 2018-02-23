package it.com.sunrayapps.jira.plugin.ir.chrome;

import org.rauschig.jarchivelib.ArchiveFormat;
import org.rauschig.jarchivelib.ArchiverFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.apache.commons.io.FileUtils.copyURLToFile;

/**
 * Downloads chrome driver and sets up Chrome driver.
 * Currently, only 64 bit Linux is supported.
 */
public class ChromeDriverInstaller {
    private final String chromeDriverZip = "chromedriver_linux64.zip";
    private final URI uri = URI.create("https://chromedriver.storage.googleapis.com/2.35/" + chromeDriverZip);
    private final Path installDirectory;

    public ChromeDriverInstaller() {
        this(Paths.get("target"));
    }

    public ChromeDriverInstaller(Path installDirectory) {
        this.installDirectory = installDirectory;
    }

    public void setup() {
        download();
        unzip();
        System.setProperty(
            "webdriver.chrome.driver",
            installDirectory.resolve("chromedriver").toString()
        );
    }

    private void download() {
        try {
            copyURLToFile(
                uri.toURL(),
                getChromeDriverZip()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File getChromeDriverZip() {
        return new File(installDirectory
            .resolve(chromeDriverZip)
            .toUri());
    }

    private void unzip() {
        try {
            ArchiverFactory
                .createArchiver(ArchiveFormat.ZIP)
                .extract(getChromeDriverZip(), installDirectory.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
