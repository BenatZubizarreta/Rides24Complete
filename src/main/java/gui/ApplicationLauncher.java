package gui;

import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import business.logic.BLFacade;
import business.logic.BLFacadeFactory;
import business.logic.BLFacadeImplementation;
import configuration.ConfigXML;
import data.access.DataAccess;

public class ApplicationLauncher {

	public static void main(String[] args) {
		
		BLFacadeFactory blff = new BLFacadeFactory();

		ConfigXML c = ConfigXML.getInstance();

		System.out.println(c.getLocale());

		Locale.setDefault(new Locale(c.getLocale()));

		System.out.println("Locale: " + Locale.getDefault());

		try {

			BLFacade appFacadeInterface = blff.BLFacadeCreator(c.isBusinessLogicLocal());
			
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			MainGUI.setBussinessLogic(appFacadeInterface);
			MainGUI a = new MainGUI();
			a.setVisible(true);

		} catch (Exception e) {
			System.out.println("Error in ApplicationLauncher: " + e.toString());
		}

	}

}
