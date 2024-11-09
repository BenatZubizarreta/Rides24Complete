package business.logic;

import domain.Driver;
import gui.DriverTable;

public class AdapterProba {
	public static void main(String[]	args)	{
//		the	BL	is	local
	boolean isLocal =	true;
	BLFacade	blFacade =	new BLFacadeFactory().BLFacadeCreator(isLocal);
	Driver	d= blFacade. getDriver("Urtzi");
	DriverTable	dt=new	DriverTable(d);
	dt.setVisible(true);
	}
}
