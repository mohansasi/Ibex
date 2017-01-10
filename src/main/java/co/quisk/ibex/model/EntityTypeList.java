package co.quisk.ibex.model;

import java.util.ArrayList;
import java.util.List;

import co.quisk.ap.support.valueobject.EntityInfo;

public class EntityTypeList extends ArrayList<EntityInfo>{

	private List<EntityInfo> list = this;

	public List<EntityInfo> getList() {
		return list;
	}

	public void setList(List<EntityInfo> list) {
		this.list = list;
	}
	
	
}
