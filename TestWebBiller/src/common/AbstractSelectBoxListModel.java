package common;

import java.util.List;

import javax.swing.AbstractListModel;

public abstract class AbstractSelectBoxListModel<T> extends AbstractListModel<T> {
	protected List<T> data;

	public AbstractSelectBoxListModel(List<T> data) {
		this.data = data;
	}

	@Override
	public T getElementAt(int index) {
		return data.get(index);
	}

	@Override
	public int getSize() {
		return data.size();
	}

	public void addData(T element) {
		if (data != null)
			data.add(element);
	}

	public void addData(int index, T element) {
		if (data != null)
			data.add(index, element);
	}

	public abstract void addItemAll() throws Exception;

	public abstract void addItemSelect() throws Exception;

	public int findIndexOfItem(T element) {
		if (data == null) {
			return -1;
		} else {
			return data.indexOf(element);
		}
	}

	public void addDataToSelection(int index) throws Exception {
		if (data.size() > index) {
			T obj = data.get(index);
			addToSelection(obj);
		}
	}

}
