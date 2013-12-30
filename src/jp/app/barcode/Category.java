package jp.app.barcode;



public class Category {
	private static Category instance = null;

	public Category() {
	}

	// Get singleton instance
	public static synchronized Category getInstance(){
		if(instance == null){
			instance = new Category();
		}
		return instance;
	}
	
	public String getMarketFromJanStep2(String jan){
		String market = jan.substring(3, 4);

		int marketCode = Integer.valueOf(market);

		return Market.getMarketName(marketCode);
	}
	
	public String getSellputFromJanStep2(String jan){
		String sellput = jan.substring(4, 5);

		int sellputCode = Integer.valueOf(sellput);
		
		return Sellput.getSellputName(sellputCode);
	}
	
	public String getContentFromJanStep2(String jan){
		String content = jan.substring(5, 7);
		
		int contentCode = Integer.valueOf(content);
		
		return Contents.getContentName(contentCode);
	}
	
	public int getCostFromJanStep2(String jan){
		String cost = jan.substring(7, 12);
		
		return Integer.valueOf(cost);
	}
	
	public static String makeJanStep2FromBookInfo(String market, String sellput, String contents, String amount){
		final String STEP = "192"; // Shows tax 5% included. When the tax was 3%, this value equaled 191.
		String contents2Digit = (contents.length()==1 ? "0"+contents : contents.substring(0, 2));
		String category = market.substring(0, 1) + sellput.substring(0, 1) + contents2Digit;
		String cost = amount.substring(0, Math.min(amount.length(), 5));
		for(int i = 0; i < 5 - amount.length(); i++){ cost = "0" + cost; } // Adjust length to 5
		
		String step2 = addCheckDigit(STEP + category + cost);
		return step2.substring(0, 13);
	}
	
	public static String addCheckDigit(String code12){
		try {
			String oddStr = new String(), evenStr = new String();
			for(int i = 0; i < code12.length(); i++){
				if(i >= 12) break;
				if(i % 2 == 0){
					oddStr += code12.substring(i, i+1);
				} else {
					evenStr += code12.substring(i, i+1);
				}
			}
			int digit1 = Integer.valueOf(evenStr) * 33;
			int digit2 = Integer.valueOf(oddStr);
			int digit3 = digit1 + digit2;
			int digit4 = digit3 - ((int)(digit3/10))*10; // Get the number of place 1
			int digit5 = 10 - digit4;
			
			return code12 + String.valueOf(digit5);
			
		} catch(Exception e){
			return code12 + "0";
		}
	}
}
