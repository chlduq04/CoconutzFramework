package com.coconutz.Service;

import com.coconutz.Coconut;
import com.coconutz.CoconutParameter;
import com.coconutz.HelperRef.CoconutzCreateTable;
import com.coconutz.HelperRef.CoconutzHelper;
import com.coconutz.HelperRef.CoconutzPagination;

public class TestCoconut extends CoconutzHelper{
	public Coconut _TestXML(CoconutParameter parameter){
		return super.GET_XML(parameter);
	}
}
