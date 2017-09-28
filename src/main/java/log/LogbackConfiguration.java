package log;

import java.io.FileNotFoundException;
import java.net.URL;

import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public class LogbackConfiguration {

    public static void initLogging(String location) throws FileNotFoundException {
        String resolvedLocation = SystemPropertyUtils
                .resolvePlaceholders(location);
        URL url = ResourceUtils.getURL(resolvedLocation);
        if (ResourceUtils.URL_PROTOCOL_FILE.equals(url.getProtocol())
                && !ResourceUtils.getFile(url).exists()) {
            throw new FileNotFoundException(
                    "Logback config file [" + resolvedLocation + "] not found");
        }

        LoggerContext context = (LoggerContext) LoggerFactory
                .getILoggerFactory();
        
        try {
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            // Call context.reset() to clear any previous configuration, e.g.
            // default
            // configuration. For multi-step configuration, omit calling
            // context.reset().
            context.reset();
            configurator.doConfigure(url);
        } catch (JoranException je) {
            // StatusPrinter will handle this
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
    }
}
