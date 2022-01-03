package com.boulow.mono.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import lombok.Data;
import lombok.Getter;

@Configuration
@ConfigurationProperties(
	    prefix = "boulow",
	    ignoreUnknownFields = false
	)
@Data
public class BoulowProperties {

	private final BoulowProperties.ClientApp clientApp = new BoulowProperties.ClientApp();
	private final BoulowProperties.Cache cache = new BoulowProperties.Cache();
    private final BoulowProperties.Mail mail = new BoulowProperties.Mail();
    private final BoulowProperties.Http http = new BoulowProperties.Http();
    private final BoulowProperties.ApiDocs apiDocs = new BoulowProperties.ApiDocs();
    private final BoulowProperties.Logging logging = new BoulowProperties.Logging();
    private final CorsConfiguration cors = new CorsConfiguration();
    private final BoulowProperties.AuditEvents auditEvents = new BoulowProperties.AuditEvents();
    private final BoulowProperties.Security security = new BoulowProperties.Security();
    private final BoulowProperties.Firebase firebaseProps = new BoulowProperties.Firebase();
    private final BoulowProperties.Amazon amazonS3Props = new BoulowProperties.Amazon();
    private final BoulowProperties.CloudFolders cloudFolders = new BoulowProperties.CloudFolders();
    
    @Data
    public static class ClientApp {
        private String name = "Boulow-mono";

        public ClientApp() {
        }

    }
    
    @Data
    public static class Firebase {
    	private int sessionExpiryInDays;
    	private String databaseUrl;
    	private boolean enableStrictServerSession;
    	private boolean enableCheckSessionRevoked;
    	private boolean enableLogoutEverywhere;

        public Firebase() {
        }

    }
    
    @Data
    public static class CloudFolders {
    	
    	private String avatars;
    	private String contracts;
    	private String ads;
    	private String hints;
    	private String reviews;
    	private String ids;
    	private String others;
    	
    	public CloudFolders() {
    		
    	}
    }
    
    @Data
    public static class Amazon {
    	private String endpointUrl;
    	private String accessKey;
    	private String secretKey;
    	private String region;
    	private String bucketName;

        public Amazon() {
        }

    }
    
    @Data
    public static class Security {
    	private BoulowProperties.Security.CookieProperties cookieProps = new BoulowProperties.Security.CookieProperties();
    	private List<String> allowedPublicApis;

    	public Security() {
        }
    	
    	@Data
        public static class CookieProperties {
        	private String domain;
        	private String path;
        	private boolean httpOnly;
        	private boolean secure;
        	private int maxAgeInMinutes;

            public CookieProperties() {
            }

        }
    }
    
    @Data
    public static class AuditEvents {
        private int retentionPeriod = 30;

        public AuditEvents() {
        }

    }
    
    @Getter
    public static class Http {
        private final BoulowProperties.Http.Cache cache = new BoulowProperties.Http.Cache();

        public Http() {
        }
        
        @Data
        public static class Cache {
            private int timeToLiveInDays = 1461;

            public Cache() {
            }

        }
    }
    
    @Getter
    public static class Logging {
        private boolean useJsonFormat = false;
        private final BoulowProperties.Logging.Logstash logstash = new BoulowProperties.Logging.Logstash();

        public Logging() {
        }

        @Data
        public static class Logstash {
            private boolean enabled = false;
            private String host = "localhost";
            private int port = 5000;
            private int queueSize = 512;

            public Logstash() {
            }

        }
    }
    
    @Data
    public static class ApiDocs {
        private String title = "Application API";
        private String description = "API documentation";
        private String version = "0.0.1";
        private String termsOfServiceUrl;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private String license;
        private String licenseUrl;
        private String defaultIncludePattern;
        private String managementIncludePattern;
        private String host;
        private String[] protocols;
        private BoulowProperties.ApiDocs.Server[] servers;
        private boolean useDefaultResponseMessages;

        public ApiDocs() {
            this.termsOfServiceUrl = com.boulow.mono.config.BoulowDefaults.ApiDocs.termsOfServiceUrl;
            this.contactName = com.boulow.mono.config.BoulowDefaults.ApiDocs.contactName;
            this.contactUrl = com.boulow.mono.config.BoulowDefaults.ApiDocs.contactUrl;
            this.contactEmail = com.boulow.mono.config.BoulowDefaults.ApiDocs.contactEmail;
            this.license = com.boulow.mono.config.BoulowDefaults.ApiDocs.license;
            this.licenseUrl = com.boulow.mono.config.BoulowDefaults.ApiDocs.licenseUrl;
            this.defaultIncludePattern = "/api/.*";
            this.managementIncludePattern = "/management/.*";
            this.host = com.boulow.mono.config.BoulowDefaults.ApiDocs.host;
            this.protocols = com.boulow.mono.config.BoulowDefaults.ApiDocs.protocols;
            this.servers = new BoulowProperties.ApiDocs.Server[0];
            this.useDefaultResponseMessages = true;
        }

        @Data
        public static class Server {
            private String name;
            private String url;
            private String description;

            public Server() {
            }

        }
    }
    
    @Data
    public static class Mail {
        private boolean enabled = false;
        private String from = "";
        private String baseUrl = "";

        public Mail() {
        }

    }

    @Getter
    public static class Cache {
        private final BoulowProperties.Cache.Hazelcast hazelcast = new BoulowProperties.Cache.Hazelcast();
        private final BoulowProperties.Cache.Caffeine caffeine = new BoulowProperties.Cache.Caffeine();
        private final BoulowProperties.Cache.Ehcache ehcache = new BoulowProperties.Cache.Ehcache();
        private final BoulowProperties.Cache.Infinispan infinispan = new BoulowProperties.Cache.Infinispan();
        private final BoulowProperties.Cache.Memcached memcached = new BoulowProperties.Cache.Memcached();

        public Cache() {
        }


        public static class Memcached {
            private boolean enabled = false;
            private String servers = "localhost:11211";
            private int expiration = 300;
            private boolean useBinaryProtocol = true;
            private BoulowProperties.Cache.Memcached.Authentication authentication = new BoulowProperties.Cache.Memcached.Authentication();

            public Memcached() {
            }

            public boolean isEnabled() {
                return this.enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getServers() {
                return this.servers;
            }

            public void setServers(String servers) {
                this.servers = servers;
            }

            public int getExpiration() {
                return this.expiration;
            }

            public void setExpiration(int expiration) {
                this.expiration = expiration;
            }

            public boolean isUseBinaryProtocol() {
                return this.useBinaryProtocol;
            }

            public void setUseBinaryProtocol(boolean useBinaryProtocol) {
                this.useBinaryProtocol = useBinaryProtocol;
            }

            public BoulowProperties.Cache.Memcached.Authentication getAuthentication() {
                return this.authentication;
            }

            public static class Authentication {
                private boolean enabled = false;
                private String username;
                private String password;

                public Authentication() {
                }

                public boolean isEnabled() {
                    return this.enabled;
                }

                public BoulowProperties.Cache.Memcached.Authentication setEnabled(boolean enabled) {
                    this.enabled = enabled;
                    return this;
                }

                public String getUsername() {
                    return this.username;
                }

                public BoulowProperties.Cache.Memcached.Authentication setUsername(String username) {
                    this.username = username;
                    return this;
                }

                public String getPassword() {
                    return this.password;
                }

                public BoulowProperties.Cache.Memcached.Authentication setPassword(String password) {
                    this.password = password;
                    return this;
                }
            }
        }

        public static class Infinispan {
            private String configFile = "default-configs/default-jgroups-tcp.xml";
            private boolean statsEnabled = false;
            private final BoulowProperties.Cache.Infinispan.Local local = new BoulowProperties.Cache.Infinispan.Local();
            private final BoulowProperties.Cache.Infinispan.Distributed distributed = new BoulowProperties.Cache.Infinispan.Distributed();
            private final BoulowProperties.Cache.Infinispan.Replicated replicated = new BoulowProperties.Cache.Infinispan.Replicated();

            public Infinispan() {
            }

            public String getConfigFile() {
                return this.configFile;
            }

            public void setConfigFile(String configFile) {
                this.configFile = configFile;
            }

            public boolean isStatsEnabled() {
                return this.statsEnabled;
            }

            public void setStatsEnabled(boolean statsEnabled) {
                this.statsEnabled = statsEnabled;
            }

            public BoulowProperties.Cache.Infinispan.Local getLocal() {
                return this.local;
            }

            public BoulowProperties.Cache.Infinispan.Distributed getDistributed() {
                return this.distributed;
            }

            public BoulowProperties.Cache.Infinispan.Replicated getReplicated() {
                return this.replicated;
            }

            public static class Replicated {
                private long timeToLiveSeconds = 60L;
                private long maxEntries = 100L;

                public Replicated() {
                }

                public long getTimeToLiveSeconds() {
                    return this.timeToLiveSeconds;
                }

                public void setTimeToLiveSeconds(long timeToLiveSeconds) {
                    this.timeToLiveSeconds = timeToLiveSeconds;
                }

                public long getMaxEntries() {
                    return this.maxEntries;
                }

                public void setMaxEntries(long maxEntries) {
                    this.maxEntries = maxEntries;
                }
            }

            public static class Distributed {
                private long timeToLiveSeconds = 60L;
                private long maxEntries = 100L;
                private int instanceCount = 1;

                public Distributed() {
                }

                public long getTimeToLiveSeconds() {
                    return this.timeToLiveSeconds;
                }

                public void setTimeToLiveSeconds(long timeToLiveSeconds) {
                    this.timeToLiveSeconds = timeToLiveSeconds;
                }

                public long getMaxEntries() {
                    return this.maxEntries;
                }

                public void setMaxEntries(long maxEntries) {
                    this.maxEntries = maxEntries;
                }

                public int getInstanceCount() {
                    return this.instanceCount;
                }

                public void setInstanceCount(int instanceCount) {
                    this.instanceCount = instanceCount;
                }
            }

            public static class Local {
                private long timeToLiveSeconds = 60L;
                private long maxEntries = 100L;

                public Local() {
                }

                public long getTimeToLiveSeconds() {
                    return this.timeToLiveSeconds;
                }

                public void setTimeToLiveSeconds(long timeToLiveSeconds) {
                    this.timeToLiveSeconds = timeToLiveSeconds;
                }

                public long getMaxEntries() {
                    return this.maxEntries;
                }

                public void setMaxEntries(long maxEntries) {
                    this.maxEntries = maxEntries;
                }
            }
        }

        public static class Ehcache {
            private int timeToLiveSeconds = 3600;
            private long maxEntries = 100L;

            public Ehcache() {
            }

            public int getTimeToLiveSeconds() {
                return this.timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds) {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public long getMaxEntries() {
                return this.maxEntries;
            }

            public void setMaxEntries(long maxEntries) {
                this.maxEntries = maxEntries;
            }
        }

        public static class Caffeine {
            private int timeToLiveSeconds = 3600;
            private long maxEntries = 100L;

            public Caffeine() {
            }

            public int getTimeToLiveSeconds() {
                return this.timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds) {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public long getMaxEntries() {
                return this.maxEntries;
            }

            public void setMaxEntries(long maxEntries) {
                this.maxEntries = maxEntries;
            }
        }

        public static class Hazelcast {
            private int timeToLiveSeconds = 3600;
            private int backupCount = 1;

            public Hazelcast() {
            }

            public int getTimeToLiveSeconds() {
                return this.timeToLiveSeconds;
            }

            public void setTimeToLiveSeconds(int timeToLiveSeconds) {
                this.timeToLiveSeconds = timeToLiveSeconds;
            }

            public int getBackupCount() {
                return this.backupCount;
            }

            public void setBackupCount(int backupCount) {
                this.backupCount = backupCount;
            }
        }
    }
}
