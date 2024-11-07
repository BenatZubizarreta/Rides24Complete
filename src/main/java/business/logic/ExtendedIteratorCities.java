package business.logic;

import java.util.ArrayList;
import java.util.List;

public class ExtendedIteratorCities implements ExtendedIterator<String> {
	
	private List<String> citiesList;
	private int position;
	
	public ExtendedIteratorCities(List<String> cities) {
		citiesList = cities;
		position = 0;
	}

	@Override
	public boolean hasNext() {
		if(position <= citiesList.size() - 1) return true;
		else return false;
	}

	@Override
	public String next() {
		String ret = citiesList.get(position);
		this.position++;
		return ret;
	}

	@Override
	public String previous() {
		String ret = citiesList.get(position - 1);
		this.position--;
		return ret;
	}

	@Override
	public boolean hasPrevious() {
		if(position > 0) return true;
		else return false;
	}

	@Override
	public void goFirst() {
		this.position = 0;
	}

	@Override
	public void goLast() {
		this.position = citiesList.size();
	}

}
