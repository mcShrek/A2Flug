package parser;

import air.AirInterface;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractParser <T extends AirInterface> implements AirParser<T> {
    protected Path iataPagePath;
    protected String resourceRoot;
    protected String iataCodesDir;

    public AbstractParser(String resourceRoot, String iataPagePath){
        this.resourceRoot = resourceRoot;
        this.iataPagePath = Paths.get(resourceRoot, iataPagePath);
    }
    public AbstractParser(String resourceRoot, String iataPagePath, String iataCodesDir){
        this.resourceRoot = resourceRoot;
        this.iataPagePath = Paths.get(resourceRoot, iataPagePath);
        this.iataCodesDir = iataCodesDir;
    }
}
