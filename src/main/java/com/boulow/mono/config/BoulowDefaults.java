package com.boulow.mono.config;

public interface BoulowDefaults {

	public interface AuditEvents {
        int retentionPeriod = 30;
    }

    public interface ClientApp {
        String name = "BoulowMono";
    }

    public interface Logging {
        boolean useJsonFormat = false;

        public interface Logstash {
            boolean enabled = false;
            String host = "localhost";
            int port = 5000;
            int queueSize = 512;
        }
    }

    public interface ApiDocs {
        String title = "Application API";
        String description = "API documentation";
        String version = "0.0.1";
        String termsOfServiceUrl = null;
        String contactName = null;
        String contactUrl = null;
        String contactEmail = null;
        String license = null;
        String licenseUrl = null;
        String defaultIncludePattern = "/api/.*";
        String managementIncludePattern = "/management/.*";
        String host = null;
        String[] protocols = new String[0];
        boolean useDefaultResponseMessages = true;
    }

    public interface Security {
        String contentSecurityPolicy = "default-src 'self'; frame-src 'self' data:; script-src 'self' 'unsafe-inline' 'unsafe-eval' https://storage.googleapis.com; style-src 'self' 'unsafe-inline'; img-src 'self' data:; font-src 'self' data:";

        public interface RememberMe {
            String key = null;
        }

        public interface Authentication {
            public interface Jwt {
                String secret = null;
                String base64Secret = null;
                long tokenValidityInSeconds = 1800L;
                long tokenValidityInSecondsForRememberMe = 2592000L;
            }
        }

        public interface ClientAuthorization {
            String accessTokenUri = null;
            String tokenServiceId = null;
            String clientId = null;
            String clientSecret = null;
        }
    }

    public interface Mail {
        boolean enabled = false;
        String from = "";
        String baseUrl = "";
    }

    public interface Cache {
        public interface Redis {
            String[] server = new String[]{"redis://localhost:6379"};
            int expiration = 300;
            boolean cluster = false;
            int connectionPoolSize = 64;
            int connectionMinimumIdleSize = 24;
            int subscriptionConnectionPoolSize = 50;
            int subscriptionConnectionMinimumIdleSize = 1;
        }

        public interface Memcached {
            boolean enabled = false;
            String servers = "localhost:11211";
            int expiration = 300;
            boolean useBinaryProtocol = true;

            public interface Authentication {
                boolean enabled = false;
            }
        }

        public interface Infinispan {
            String configFile = "default-configs/default-jgroups-tcp.xml";
            boolean statsEnabled = false;

            public interface Replicated {
                long timeToLiveSeconds = 60L;
                long maxEntries = 100L;
            }

            public interface Distributed {
                long timeToLiveSeconds = 60L;
                long maxEntries = 100L;
                int instanceCount = 1;
            }

            public interface Local {
                long timeToLiveSeconds = 60L;
                long maxEntries = 100L;
            }
        }

        public interface Ehcache {
            int timeToLiveSeconds = 3600;
            long maxEntries = 100L;
        }

        public interface Caffeine {
            int timeToLiveSeconds = 3600;
            long maxEntries = 100L;
        }

        public interface Hazelcast {
            int timeToLiveSeconds = 3600;
            int backupCount = 1;

            public interface ManagementCenter {
                boolean enabled = false;
                int updateInterval = 3;
                String url = "";
            }
        }
    }

    public interface Http {
        public interface Cache {
            int timeToLiveInDays = 1461;
        }
    }

    public interface Async {
        int corePoolSize = 2;
        int maxPoolSize = 50;
        int queueCapacity = 10000;
    }
    
    public interface Profiles {
        String SPRING_PROFILE_DEVELOPMENT = "dev";
        String SPRING_PROFILE_TEST = "test";
        String SPRING_PROFILE_PRODUCTION = "prod";
        String SPRING_PROFILE_CLOUD = "cloud";
        String SPRING_PROFILE_HEROKU = "heroku";
        String SPRING_PROFILE_AWS_ECS = "aws-ecs";
        String SPRING_PROFILE_AZURE = "azure";
        String SPRING_PROFILE_API_DOCS = "api-docs";
        String SPRING_PROFILE_NO_LIQUIBASE = "no-liquibase";
        String SPRING_PROFILE_K8S = "k8s";
    }
}
