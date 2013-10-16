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
			if (splitted[0].startsWith("?"))
				return splitted[0] + splitted[1];
			else
				return String.valueOf(Integer.parseInt(splitted[1]) * 2);
		else
			return String.valueOf(Integer.parseInt(splitted[0]) * 2);
		
		/*if (splitted[0].startsWith("?")) {
			if (splitted[1].startsWith("?")) {
				
			}
		}*/
	}

}
