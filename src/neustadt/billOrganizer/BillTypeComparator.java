package neustadt.billOrganizer;

import java.util.Comparator;

public class BillTypeComparator implements Comparator<Bill>{

	@Override
	public int compare(Bill billA, Bill billB){
		return billA.getBillType().compareTo(billB.getBillType());
	}
}
