package org.docksidestage.javatry.basic.st6.os;

public class OsMac extends St6OperationSystem{

    private final String loginId;
    private static final String osType = "Mac";

    public OsMac(String loginId) {
        super(loginId);
        this.loginId = loginId;

    }

    public String buildUserResourcePath(String relativePath) {
        String fileSeparator = getFileSeparator();
        String userDirectory = getUserDirectory();
        String resourcePath = userDirectory + fileSeparator + relativePath;
        return resourcePath.replace("/", fileSeparator);
    }

    protected String getFileSeparator() {
        if ("Mac".equalsIgnoreCase(osType)) {
            return "/";
        } else {
            throw new IllegalStateException("Unknown osType: " + osType);
        }
    }

    protected String getUserDirectory() {
        if ("Mac".equalsIgnoreCase(osType)) {
            return "/Users/" + loginId;
        } else {
            throw new IllegalStateException("Unknown osType: " + osType);
        }
    }

}
