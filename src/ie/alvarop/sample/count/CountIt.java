package ie.alvarop.sample.count;

public class CountIt {

	public String resolve(String val) {
		int comma = getCommaPos(val, 0);
		return "YES";
	}

	public int getCommaPos(String val, int start) {
		return val.indexOf(",", start);
	}

	public String reduce(String val) {
		String currVal = val.substring(1, val.length()-1);
		String splitted [] = currVal.split(",");
		if (splitted[0].startsWith("?"))
			if (splitted[1].startsWith("?")) {
				int left = splitted[0].length();
				int right = splitted[1].length();
				if (left >= right)
					return splitted[0] + splitted[0];
				return splitted[1]+ splitted[1];
			} else
				return String.valueOf(Integer.parseInt(splitted[1]) * 2);
		else
			return String.valueOf(Integer.parseInt(splitted[0]) * 2);
	}

	public String range(String val, int commaPos) {
		char prev = val.charAt(commaPos - 1);
		char next = val.charAt(commaPos + 1);
		
		if (prev == ']' || next == '[') return null;
		return val.substring(commaPos - 2, commaPos + 3);
	}

}
