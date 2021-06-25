package lightbiny;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {
	public static void main(String[] arg) {
		String v0 = "v6.0";
		String v1 = "1.10.0";
		String v2 = "1.9.345";
		String v3 = "1.0.";
		
		
		versionToInt(v0);
		versionToInt(v1);
		versionToInt(v2);
		versionToInt(v3);
	}

	private static void versionToInt(String v1) {
		final Pattern SDK = Pattern.compile("(\\d+)[\\._](\\d+)[\\._]?(\\d*)");
		Matcher m = SDK.matcher(v1);
		
		if (m.find()) {
			String major = m.group(1);
			String minor = m.group(2);
			String hotfix = "".equals(m.group(3))?"0":m.group(3);
			StringBuilder s = new StringBuilder()
					.append(major)
					.append(String.format("%03d", Long.parseLong(minor)))
					.append(String.format("%03d", Long.parseLong(hotfix)))
					;
			System.out.println(s);
			System.out.println(Long.MAX_VALUE);
		}
	}
}
