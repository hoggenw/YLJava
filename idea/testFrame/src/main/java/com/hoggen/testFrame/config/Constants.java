package com.kykj.internethospital.express.config;

/**
 * Application constants.
 *
 * @author baogang
 */
public final class Constants {

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String AUTH_HEADER = "Authorization";
    public static final String ADMIN_ROLE = "admin";
    public static final String PATIENT_ROLE = "patient";
    public static final String DOCTOR_ROLE = "doctor";
    public static final String PHARMACIST_CODE = "pharmacist";
    public static final String NURSE_ROLE = "nurse";
    public static final String CHANNEL_ROLE = "channel";


    public static final String REALM_CODE = "internet-hospital";
    public static final int MAX_BYTE = 10 * 1024 * 1024;

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";

    public static final String SPRING_PROFILE_PRODUCTION = "prod";

    public static final String CREATE_SCOPE = "create";

    public static final String UPDATE_SCOPE = "updateOne";

    public static final String READ_SCOPE = "read";

    public static final String DELETE_SCOPE = "delete";

    public static final String EXPORT_SCOPE = "export";

    public static final String IMPORT_SCOPE = "import";
    public static final int THOUSAND = 1000;

    private Constants() {
    }
}
