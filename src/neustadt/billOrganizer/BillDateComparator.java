package neustadt.billOrganizer;

import java.util.Comparator;

public class BillDateComparator implements Comparator<Bill>{

	@Override
	public int compare(Bill billA, Bill billB){
		return billA.getDateDue().compareTo(billB.getDateDue());
	}

	}
