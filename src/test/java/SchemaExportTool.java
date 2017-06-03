import org.hibernate.MappingException;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.cfg.MyConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.IOException;
import java.util.Properties;

public class SchemaExportTool extends MyConfiguration {

    private static final long serialVersionUID = 1L;

    private static final String RESOURCE_PATTERN = "/**/*.class";

    private static final String PACKAGE_INFO_SUFFIX = ".package-info";

    private static final TypeFilter[] ENTITY_TYPE_FILTERS = new TypeFilter[]{new AnnotationTypeFilter(Entity.class, false), new AnnotationTypeFilter(Embeddable.class, false), new AnnotationTypeFilter(MappedSuperclass.class, false)};
    private final ResourcePatternResolver resourcePatternResolver;

    public SchemaExportTool() {
        this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(new PathMatchingResourcePatternResolver());
    }

    public static void main(String[] args) {
        try {
            Properties p = new Properties();
            p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            SchemaExportTool cfg = new SchemaExportTool();
            cfg.addProperties(p);
            cfg.setNamingStrategy(new ImprovedNamingStrategy());
            cfg.scanPackage("com.sh.model");
            //cfg.scanPackage("com.share.passport.domain",
            //	"com.share.authority.domain", "com.share.utils.domain");

            SchemaExport se = new SchemaExport(cfg);
            if (null != args && args.length > 1) if ("-f".equals(args[0])) se.setOutputFile(args[1]);
            else se.setOutputFile("create_table.sql");
            else se.setOutputFile("create_table.sql");
            se.setDelimiter(";");
            // se.drop(false, false);
            se.create(true, false);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private SchemaExportTool scanPackage(String... packagesToScan) {
        try {
            for (String pkg : packagesToScan) {
                String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(pkg) + RESOURCE_PATTERN;
                Resource[] resources = this.resourcePatternResolver.getResources(pattern);
                MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
                for (Resource resource : resources) {
                    if (resource.isReadable()) {
                        MetadataReader reader = readerFactory.getMetadataReader(resource);
                        String className = reader.getClassMetadata().getClassName();
                        if (matchesEntityTypeFilter(reader, readerFactory)) {
                            addAnnotatedClass(this.resourcePatternResolver.getClassLoader().loadClass(className));
                        } else if (className.endsWith(PACKAGE_INFO_SUFFIX)) {
                            addPackage(className.substring(0, className.length() - PACKAGE_INFO_SUFFIX.length()));
                        }
                    }
                }
            }
            return this;
        } catch (IOException ex) {
            throw new MappingException("Failed to scan classpath for unlisted classes", ex);
        } catch (ClassNotFoundException ex) {
            throw new MappingException("Failed to load annotated classes from classpath", ex);
        }
    }

    /**
     * Check whether any of the configured entity type filters matches the
     * current class descriptor contained in the metadata reader.
     */
    private boolean matchesEntityTypeFilter(MetadataReader reader, MetadataReaderFactory readerFactory) throws IOException {
        for (TypeFilter filter : ENTITY_TYPE_FILTERS) {
            if (filter.match(reader, readerFactory)) {
                return true;
            }
        }
        return false;
    }

}