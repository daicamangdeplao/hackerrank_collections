package problems;

import java.util.regex.Pattern;

public class IpAddressValidation {
    private static final String IP_V4_REGEX = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";

    public static void run(String ipAddress) {
        Pattern compiledPattern = Pattern.compile(IP_V4_REGEX);
        boolean matches = compiledPattern.matcher(ipAddress).matches();
        System.out.println(matches);
    }
}
