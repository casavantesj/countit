package ie.alvarop.sample.count;

public class CountIt {

	public String resolve(String val) {
		String curr;
		if (val.startsWith("[") && getCommaPos(val, 0) > 1 && val.length() >=5)
			for (curr = oneRun(val); curr.startsWith("["); curr = oneRun(curr));
		else 
			curr = "NO";
		return curr;
	}

	public int getCommaPos(String val, int start) {
		return val.indexOf(",", start + 1);
	}

	public String reduce(String val) {
		String currVal = val.substring(1, val.length()-1);
		String splitted [] = currVal.split(",");
		if (splitted[0].startsWith("?")) {
			int left = splitted[0].length();
			if (splitted[1].startsWith("?")) {
				int right = splitted[1].length();
				if (left >= right)
					return splitted[0] + splitted[0];
				return splitted[1]+ splitted[1];
			} else {
				int right = Integer.parseInt(splitted[1]);
				return right%left == 0?String.valueOf(right * 2):"NO";
			}
		} else {
			int left = Integer.parseInt(splitted[0]);
			if (splitted[1].startsWith("?")) {
				int right = splitted[1].length();
				return left%right == 0?String.valueOf(left * 2):"NO";
			} else {
				int right = Integer.parseInt(splitted[1]);
				return left == right?String.valueOf(left + right):"NO";
			}
		}
	}

	public String range(String val, int commaPos) {
		char prev = val.charAt(commaPos - 1);
		char next = val.charAt(commaPos + 1);
		
		if (prev == ']' || next == '[') return null;
		String tmp = val.substring(0, val.indexOf(']') + 1);
		return tmp.substring(tmp.lastIndexOf('['));
	}

	public String nextRange(String val, int startPos) {
		for (int pos = getCommaPos(val, startPos); pos != -1; pos = getCommaPos(val, pos)) {
			String range = range(val, pos);
			if (range != null) return range;
		}
		return null;
	}

	public String replace(String orig, String pattern, String value) {
		int pos = orig.indexOf(pattern);
		return orig.substring(0, pos) + value + orig.substring(pos + pattern.length());
	}

	public String oneRun(String orig) {
		String range = nextRange(orig, 0);
		if (range == null) return orig;
		String reduced = reduce(range);
		if (reduced.equalsIgnoreCase("NO")) return "NO";
		String replaced = replace(orig, range, reduced);
		if (replaced.startsWith("[")) return replaced;
		if (replaced.startsWith("?")) return "NO";
		return "YES";
	}

}
