import java.util.regex.Pattern;

class CaseInsensitivePattern {
	public static void main(String[] args) {
		System.out.println("Case insensitive regex: " + Pattern.compile("select",Pattern.CASE_INSENSITIVE).matcher("SELECT").matches());
		System.out.println("Case sensitive regex: " + Pattern.compile("select").matcher("SELECT").matches());

		System.out.println("Case insensitive match through String: " + "SELECT".matches("(?i)select"));
		System.out.println("Case sensitive match through String: " + "SELECT".matches("select"));
	}
}