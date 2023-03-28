package kr.or.ddit.basic;

public class PlanetCalculator {
	
	enum Planet {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);
		
		private double r;
		
		Planet(double r) {
			this.r = r;
		}
		
		public double getData() {
			return r;
		}
}
	
	public static void main(String[] args) {
		Planet[] enumArr = Planet.values();
		for (int i = 0; i < enumArr.length; i++) {
			System.out.println("=====================================================================");
			System.out.println("||" + enumArr[i].name()
					+ "||표면적: " + 4 * Math.PI * Math.pow(enumArr[i].getData(), 2)
					+ "km^2||부피: " + 4.0 / 3 * Math.PI * Math.pow(enumArr[i].getData(), 3) + "km^3"
					+ "");
			
		}
		System.out.println("=====================================================================");
	}

}
