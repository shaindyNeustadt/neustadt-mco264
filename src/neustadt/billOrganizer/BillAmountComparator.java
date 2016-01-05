package neustadt.billOrganizer;

import java.util.Comparator;

public class BillAmountComparator implements Comparator<Bill>{

	@Override
	public int compare(Bill billA, Bill billB) {
		return billB.getAmountDue().compareTo(billA.getAmountDue());
	}
}
